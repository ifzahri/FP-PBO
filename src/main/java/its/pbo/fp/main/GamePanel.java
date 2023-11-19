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
    private BufferedImage imageBg, imageUI, subImage;
    public GamePanel() {
        mouseInputs = new MouseInputs(this);
        importImg();
        setPanelSize();
        addKeyListener(new KeyboardInputs(this));
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    private void importImg() {
        InputStream isBg = getClass().getResourceAsStream("/Backgrounds/mountains_a.png");
        InputStream isUI = getClass().getResourceAsStream("/UI/text_fx.png");

        try {
            imageBg = ImageIO.read(isBg);
            imageUI = ImageIO.read(isUI);
        } catch (IOException e) {
            e.printStackTrace();
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

        subImage = imageUI.getSubimage(6*10, 0*16, 10, 16);
        g.drawImage(imageBg, (int) xChanges, (int) yChanges, null);
        g.drawImage(subImage, 500, 0, 40, 64, null);
    }

}
