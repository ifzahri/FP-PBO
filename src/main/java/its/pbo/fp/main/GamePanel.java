package its.pbo.fp.main;

import its.pbo.fp.inputs.KeyboardInputs;
import its.pbo.fp.inputs.MouseInputs;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class GamePanel extends JPanel {
    private MouseInputs mouseInputs;
    private int xChanges = 200;
    private int yChanges = 100;
    private BufferedImage image;
    private BufferedImage[] idleAnimation;
    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImg();
        loadAnimations();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void loadAnimations() {
        idleAnimation = new BufferedImage[7];

        for (int i=0; i < idleAnimation.length; i++) {
            idleAnimation[i] = image.getSubimage(i*33, 0, 37, 50);
        }
    }

    private void importImg() {
        InputStream isBg = getClass().getResourceAsStream("/Adventurer/adventurer-result.png");

        try {
            image = ImageIO.read(isBg);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isBg.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void setPanelSize() {
        Dimension size = new Dimension(1280, 800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
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

        g.drawImage(idleAnimation[3], (int) xChanges, (int) yChanges, null);
    }

}
