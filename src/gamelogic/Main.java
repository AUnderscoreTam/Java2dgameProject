import gamelogic.GamePane;

import javax.swing.*;
import java.awt.*;


void main() {

    SwingUtilities.invokeLater(this::init);
}

public void init() {
    JFrame gameWindow = new JFrame("JavaSurvivors");
    gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    gameWindow.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    gameWindow.setUndecorated(true);
    gameWindow.setVisible(true);
    GamePane gamePlane = new GamePane();
    gameWindow.setContentPane(gamePlane);
    gameWindow.addKeyListener(gamePlane);
    gamePlane.addMouseListener(gamePlane);

}