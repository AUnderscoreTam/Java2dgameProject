import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class Main{
    void main(){

        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                init();
            }
        });
    }

    public void init() {
        JFrame gameWindow = new JFrame("MossadSurvivors");
        gameWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameWindow.setSize(1440, 1080);
        gameWindow.setVisible(true);
        Graphics g = gameWindow.getGraphics();

        GamePane gamePlane = new GamePane(gameWindow,g);
        gameWindow.setContentPane(gamePlane);
        gameWindow.addKeyListener(gamePlane);
        gameWindow.addMouseListener(gamePlane);

    }


}