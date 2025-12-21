import javax.swing.*;
import java.awt.*;


void main() {

    SwingUtilities.invokeLater(this::init);
}

public void init() {
    JFrame gameWindow = new JFrame("JavaSurvivors");
    gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    gameWindow.setSize(1440, 1080);
    gameWindow.setVisible(true);
    Graphics g = gameWindow.getGraphics();

    GamePane gamePlane = new GamePane(g);
    gameWindow.setContentPane(gamePlane);
    gameWindow.addKeyListener(gamePlane);
    gameWindow.addMouseListener(gamePlane);

}