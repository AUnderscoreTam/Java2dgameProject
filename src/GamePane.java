import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GamePane extends JPanel implements KeyListener, ActionListener, MouseListener {
    Timer timer;
    Graphics g;
    Player player;
    ArrayList<Enemy> enemy= new ArrayList<Enemy>();

    boolean kUP=false;
    boolean kLEFT=false;
    boolean kRIGHT=false;
    boolean kDOWN=false;




    public GamePane(JFrame g,Graphics gg){
        setBackground(Color.BLUE);
        player= new Player(this);
        setSize(1440,1080);
        setVisible(true);
        this.g = gg;
        timer = new Timer(30,this);
        timer.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP) {
            kUP=true;
//            player.y-= player.speed;
//            if(player.y < 0) player.y=0;
        }
        if (key == KeyEvent.VK_RIGHT) {
            kRIGHT=true;
//            player.x+=player.speed;
//            if(player.x > 1870) player.x=1870;
        }
        if (key == KeyEvent.VK_DOWN) {
            kDOWN=true;
//            player.y+=player.speed;
//            if(player.y > 1080) player.y=1030;
        }
        if (key == KeyEvent.VK_LEFT) {
            kLEFT=true;
//            player.x-=player.speed;
//            if(player.x < 0) player.x=0;
        }
        enemy.forEach(Enemy::getcloser);

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        enemy.forEach(enemy1 -> enemy1.draw(g));
        player.draw(g);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if(kUP) {
            player.y -= player.speed;
            if (player.y < 0) player.y = 0;
        }

        if(kRIGHT){
            player.x+=player.speed;
            if(player.x > 1870) player.x=1870;
        }

        if(kDOWN){
            player.y+=player.speed;
            if(player.y > 1080) player.y=1030;
        }

        if (kLEFT){
            player.x-=player.speed;
            if(player.x < 0) player.x=0;
        }


        enemy.forEach(Enemy::getcloser);
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input
        if (key == KeyEvent.VK_UP) {
            kUP=false;
        }
        if (key == KeyEvent.VK_RIGHT) {
            kRIGHT=false;

        }
        if (key == KeyEvent.VK_DOWN) {
            kDOWN=false;

        }
        if (key == KeyEvent.VK_LEFT) {
            kLEFT=false;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
         Enemy enemy1 = new Enemy(mouseEvent.getX(), mouseEvent.getY(),player);
        enemy.add(enemy1);
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
