package its.pbo.fp;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.io.Serial;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
    @Serial
    private static final long serialVersionUID = -1790261785521495991L;
    public static final int ROW = 4;
    public static final int[] _0123 = {0, 1, 2, 3};
    final Game host;
    private GameTiles[] tiles;
    public static GameValues GOAL = GameValues._2048;

    public GamePanel(Game f) {
        host = f;
        setFocusable(true);
        initTiles();
    }

    public void initTiles() {
        tiles = new GameTiles[ROW * ROW];
        Arrays.fill(tiles, GameTiles.ZERO);
        addTile();
        addTile();
        host.statusBar.setText("");
    }

    public void left() {
        boolean needAddTile = false;
        for (int i : _0123) {
            GameTiles[] origin = getLine(i);
            GameTiles[] afterMove = moveLine(origin);
            GameTiles[] merged = mergeLine(afterMove);
            setLine(i, merged);
            if (!needAddTile && !Arrays.equals(origin, merged)) {
                needAddTile = true;
            }
        }

        if (needAddTile) {
            addTile();
        }
    }

    public void right() {
        tiles = rotate(180);
        left();
        tiles = rotate(180);
    }

    public void up() {
        tiles = rotate(270);
        left();
        tiles = rotate(90);
    }

    public void down() {
        tiles = rotate(90);
        left();
        tiles = rotate(270);
    }

    private GameTiles tileAt(int x, int y) {
        return tiles[x + y * ROW];
    }

    private void addTile() {
        List<Integer> list = availableIndex();
        int idx = list.get((int) (Math.random() * list.size()));
        tiles[idx] = GameTiles.randomTile();
    }

    private List<Integer> availableIndex() {
        List<Integer> list = new LinkedList<>();
        for (int i = 0; i < tiles.length; i++) {
            if (tiles[i].empty()) list.add(i);
        }
        return list;
    }

    private boolean isFull() {
        return availableIndex().size() == 0;
    }

    boolean canMove() {
        if (!isFull()) {
            return true;
        }
        for (int x : _0123) {
            for (int y : _0123) {
                GameTiles t = tileAt(x, y);
                if ((x < ROW - 1 && t.equals(tileAt(x + 1, y))) || (y < ROW - 1 && t.equals(tileAt(x, y + 1)))) {
                    return true;
                }
            }
        }
        return false;
    }

    private GameTiles[] rotate(int dgr) {
        GameTiles[] newTiles = new GameTiles[ROW * ROW];
        int offsetX = 3, offsetY = 3;
        if (dgr == 90) {
            offsetY = 0;
        } else if (dgr == 180) {
        } else if (dgr == 270) {
            offsetX = 0;
        } else {
            throw new IllegalArgumentException("Only can rotate 90, 180, 270 degree");
        }
        double radians = Math.toRadians(dgr);
        int cos = (int) Math.cos(radians);
        int sin = (int) Math.sin(radians);
        for (int x : _0123) {
            for (int y : _0123) {
                int newX = (x * cos) - (y * sin) + offsetX;
                int newY = (x * sin) + (y * cos) + offsetY;
                newTiles[(newX) + (newY) * ROW] = tileAt(x, y);
            }
        }
        return newTiles;
    }

    private GameTiles[] moveLine(GameTiles[] oldLine) {
        LinkedList<GameTiles> l = new LinkedList<>();
        for (int i : _0123) {
            if (!oldLine[i].empty()) l.addLast(oldLine[i]);
        }
        if (l.size() == 0) {
            return oldLine;
        } else {
            GameTiles[] newLine = new GameTiles[4];
            ensureSize(l, 4);
            for (int i : _0123) {
                newLine[i] = l.removeFirst();
            }
            return newLine;
        }
    }

    private GameTiles[] mergeLine(GameTiles[] oldLine) {
        LinkedList<GameTiles> list = new LinkedList<GameTiles>();
        for (int i = 0; i < ROW; i++) {
            if (i < ROW - 1 && !oldLine[i].empty() && oldLine[i].equals(oldLine[i + 1])) {
                GameTiles merged = oldLine[i].getDouble();
                i++;
                list.add(merged);
                if (merged.value() == GOAL) {
                    host.win();
                }
            } else {
                list.add(oldLine[i]);
            }
        }
        ensureSize(list, 4);
        return list.toArray(new GameTiles[4]);
    }

    private static void ensureSize(List<GameTiles> l, int s) {
        while (l.size() < s) {
            l.add(GameTiles.ZERO);
        }
    }

    private GameTiles[] getLine(int idx) {
        GameTiles[] result = new GameTiles[4];
        for (int i : _0123) {
            result[i] = tileAt(i, idx);
        }
        return result;
    }

    private void setLine(int idx, GameTiles[] re) {
        for (int i : _0123) {
            tiles[i + idx * ROW] = re[i];
        }
    }

    private static final Color BG_COLOR = new Color(0xbbada0);
    private static final Font STR_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 17);

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(BG_COLOR);
        g.setFont(STR_FONT);
        g.fillRect(0, 0, this.getSize().width, this.getSize().height);
        for (int y : _0123) {
            for (int x : _0123) {
                drawTile(g, tiles[x + y * ROW], x, y);
            }
        }
    }

    private static final int SIDE = 64;
    private static final int MARGIN = 16;

    private void drawTile(Graphics g, GameTiles tile, int x, int y) {
        GameValues val = tile.value();
        int xOffset = offsetCoors(x);
        int yOffset = offsetCoors(y);
        g.setColor(val.color());
        g.fillRect(xOffset, yOffset, SIDE, SIDE);
        g.setColor(val.fontColor());
        if (val.score() != 0) g.drawString(tile.toString(), xOffset + (SIDE >> 1) - MARGIN, yOffset + (SIDE >> 1));
    }

    private static int offsetCoors(int arg) {
        return arg * (MARGIN + SIDE) + MARGIN;
    }

}
