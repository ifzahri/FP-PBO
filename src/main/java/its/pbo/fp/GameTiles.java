package its.pbo.fp;

import java.util.HashMap;

import static its.pbo.fp.GameValues.*;

public class GameTiles {
    private final GameValues val;
    private final static HashMap<Integer, GameTiles> cache = new HashMap<>();
    public final static GameTiles ZERO = new GameTiles(_0);
    public final static GameTiles TWO = new GameTiles(_2);
    public final static GameTiles FOUR = new GameTiles(_4);

    static {
        for (GameValues v : GameValues.values()) {
            switch (v) {
                case _0:
                    cache.put(v.score(), ZERO);
                    break;
                case _2:
                    cache.put(v.score(), TWO);
                    break;
                case _4:
                    cache.put(v.score(), FOUR);
                    break;
                default:
                    cache.put(v.score(), new GameTiles(v));
                    break;
            }
        }
    }

    public GameTiles(GameValues v) {
        val = v;
    }

    public static GameTiles valueOf(int num) {
        return cache.get(num);
    }

    GameValues value() {
        return val;
    }

    public GameTiles getDouble() {
        return valueOf(val.score() << 1);
    }

    boolean empty() {
        return val == _0;
    }

    @Override
    public String toString() {
        return String.format("%1$4d", val.score());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((val == null) ? 0 : val.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof GameTiles))
            return false;
        GameTiles other = (GameTiles) obj;
        if (val != other.val)
            return false;
        return true;
    }

    static GameTiles randomTile() {
        return Math.random() < 0.15 ? FOUR : TWO;
    }
}
