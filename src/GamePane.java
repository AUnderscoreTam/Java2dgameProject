import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GamePane extends JPanel implements KeyListener, ActionListener, MouseListener {
    Timer timer;

    Player player;
    ArrayList<Enemy> enemy= new ArrayList<>();
    Instant start = Instant.now();
    Instant now;
    Random wooo = new Random();


    boolean kUP=false;
    boolean kLEFT=false;
    boolean kRIGHT=false;
    boolean kDOWN=false;


    public GamePane() {
        setBackground(Color.BLUE);
        player= new Player();
        setSize(1440,1080);
        setVisible(true);
        timer = new Timer(25,this);
        timer.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_UP:
                kUP = true;
                break;

            case KeyEvent.VK_RIGHT:
                    kRIGHT = true;
                    break;
            case KeyEvent.VK_DOWN:
                    kDOWN = true;
                    break;

            case KeyEvent.VK_LEFT:
                    kLEFT = true;
                    break;
        }
        now = Instant.now();
        if (Duration.between(start, now).toSeconds() >= 1) {
            switch (wooo.nextInt(1, 5)) {
                case 1:
                    Enemy enemy1 = new Enemy(20, wooo.nextInt(1060), player);
                    enemy.add(enemy1);
                    break;
                case 2:
                    Enemy enemy2 = new Enemy(wooo.nextInt(1900), 1060, player);
                    enemy.add(enemy2);
                    break;
                case 3:
                    Enemy enemy3 = new Enemy(1900, wooo.nextInt(1060), player);
                    enemy.add(enemy3);
                    break;
                case 4:
                    Enemy enemy4 = new Enemy(wooo.nextInt(1900), 20, player);
                    enemy.add(enemy4);
                    break;
            }
            start = Instant.now();
        }

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

        enemy.sort(Comparator.comparingInt(enemy -> enemy.distanceToPlayer));


        for (int j = 0; j < enemy.size(); j++) {
            enemy.get(j).getCloser();
            for (int i = 0; i < j; i++) {
                if (enemy.get(j).hitbox.intersects(enemy.get(i).hitbox)){
                    enemy.get(j).revertPosition();
                    break;
                }
            }
        }

        //enemy.forEach(enemy1 -> enemy1.isTouching = false);
        now = Instant.now();

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
        switch (key) {
            case KeyEvent.VK_UP:
                kUP = false;
                break;

            case KeyEvent.VK_RIGHT:
                kRIGHT = false;
                break;
            case KeyEvent.VK_DOWN:
                kDOWN = false;
                break;

            case KeyEvent.VK_LEFT:
                kLEFT = false;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
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
