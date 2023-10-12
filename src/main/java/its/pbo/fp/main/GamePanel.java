package its.pbo.fp.main;

import its.pbo.fp.inputs.KeyboardInputs;
import its.pbo.fp.inputs.MouseInputs;

import java.awt.Graphics;
import javax.swing.JPanel;
public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xChanges = 200;
    private int yChanges = 100;
    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.fillRect(xChanges, yChanges, 100, 40);
    }

    public void changeXMovement(int value) {
        this.xChanges += value;
        repaint();
    }
    public void changeYMovement(int value) {
        this.yChanges += value;
        repaint();
    }

    public void setRectPosition(int x, int y) {
        this.xChanges = x;
        this.yChanges = y;
        repaint();
    }
}
