package its.pbo.fp;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame f = new JFrame("Tes");
        Toolkit tk = Toolkit.getDefaultToolkit();
        Dimension screenSize = tk.getScreenSize();

        // Initialize Java Window
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(screenSize.width, screenSize.height);
        f.setVisible(true);
    }
}