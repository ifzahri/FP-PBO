package its.pbo.fp.main;
import javax.swing.*;
import java.awt.*;
public class GameWindow {
    private JFrame jframe;
    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame("Tes");
//        Toolkit tk = Toolkit.getDefaultToolkit();
//        Dimension screenSize = tk.getScreenSize();

        // Initialize Java Window
        jframe.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//      jframe.setSize(screenSize.width, screenSize.height);
//      jframe.setSize(400, 400);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.pack();
        jframe.setVisible(true);
    }
}