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
    private BufferedImage[][] animations;
    private int aniTick, aniIndex, aniSpeed = 15;
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
        animations = new BufferedImage[16][7];

        for (int j=0; j < animations.length; j++) {
            for (int i=0; i < animations[j].length; i++) {
                animations[j][i] = image.getSubimage(i * 14, j * 1, 120, 21);
            }
        }

    }

    private void importImg() {
        InputStream isBg = getClass().getResourceAsStream("/Adventurer/Sprites/adventurer-attack1.png");

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
    
    private void updateAnimationTick() {
        aniTick++;
        if (aniTick > aniSpeed) {
            aniTick = 0;
            aniIndex++;
            if (aniIndex >= 5) {
                aniIndex = 0;
            }
        }
    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        updateAnimationTick();

        g.drawImage(animations[1][aniIndex], (int) xChanges, (int) yChanges, null);
    }


}
