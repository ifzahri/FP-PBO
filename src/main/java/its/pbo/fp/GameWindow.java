package its.pbo.fp;
import javax.swing.*;
import java.awt.*;
public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Tes");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // Initialize Java Window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jframe.setSize(screenSize.width, screenSize.height);
        jframe.add(gamePanel);
        jframe.setVisible(true);
    }
}