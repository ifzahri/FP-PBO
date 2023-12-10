package its.pbo.fp;

import java.awt.BorderLayout;
import java.io.Serial;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class Game extends JFrame {
    @Serial
    private static final long serialVersionUID = 1L;
    JLabel statusBar;
    private static final String TITLE = "2048";
    public static final String WIN_MSG = "You win!";
    public static final String LOSE_MSG = "You lose, press 'r' to try again!";

    public static void main(String[] args) {
        Game game = new Game();
        GamePanel gamePanel = new GamePanel(game);
        if (args.length != 0 && args[0].matches("[0-9]*")) {
            GamePanel.GOAL = GameValues.of(Integer.parseInt(args[0]));
        }
        GameInputs kb = GameInputs.getkeySetting(gamePanel);
        gamePanel.addKeyListener(kb);
        game.add(gamePanel);

        game.setLocationRelativeTo(null);
        game.setVisible(true);
    }

    public Game() {
        setTitle(TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(340, 400);
        setResizable(false);

        statusBar = new JLabel("");
        add(statusBar, BorderLayout.SOUTH);
    }

    void win() {
        statusBar.setText(WIN_MSG);
    }

    void lose() {
        statusBar.setText(LOSE_MSG);
    }
}
