package its.pbo.fp.main;

import its.pbo.fp.inputs.KeyboardInputs;
import its.pbo.fp.inputs.MouseInputs;

import java.awt.*;
import javax.swing.JPanel;
import java.util.Random;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xChanges = 200;
    private int yChanges = 100;
    private int xDir = 1, yDir = 1;
    private Color color = new Color(150, 20, 90);
    private Random random;
    public GamePanel() {
        random = new Random();
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void changeXMovement(int value) {
        this.xChanges += value;
    }
    public void changeYMovement(int value) {
        this.yChanges += value;
    }
    public void setRectPosition(int x, int y) {
        this.xChanges = x;
        this.yChanges = y;
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        updateRectangle();

        g.setColor(color);
        g.fillRect(xChanges, yChanges, 200, 50);
    }

    private void updateRectangle() {
        xChanges += xDir;
        if (xChanges > 400 || xChanges < 0) {
            xDir *= -1;
            color = getRndColor();
        }
        yChanges += yDir;
        if (yChanges > 400 || yChanges < 0) {
            yDir *= -1;
            color = getRndColor();
        }
    }

    private Color getRndColor() {
        int r = random.nextInt(255);
        int g = random.nextInt(255);
        int b = random.nextInt(255);

        return new Color(r, g, b);
    }
}
