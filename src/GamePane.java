import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Comparator;

public class GamePane extends JPanel implements KeyListener, ActionListener, MouseListener {
    Timer timer;
    Graphics g;
    Player player;
    ArrayList<Enemy> enemy= new ArrayList<>();

    boolean kUP=false;
    boolean kLEFT=false;
    boolean kRIGHT=false;
    boolean kDOWN=false;




    public GamePane(Graphics gg){
        setBackground(Color.BLUE);
        player= new Player();
        setSize(1440,1080);
        setVisible(true);
        this.g = gg;
        timer = new Timer(25,this);
        timer.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        // depending on which arrow key was pressed, we're going to move the player by
        // one whole tile for this input

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

        enemy.forEach(enemy1 ->{
            for (int i = 0; i < enemy.size(); i++){
                if ( !(enemy.get(i).y == enemy1.y && enemy.get(i).x == enemy1.x) && enemy1.hitbox.intersects(enemy.get(i).hitbox)){
                    if (enemy.indexOf(enemy1) < i) enemy.get(i).isTouching = true;
                    else enemy1.isTouching = true;
                }
            }
            if (!(enemy1.isTouching)) enemy1.getCloser();
            else enemy1.getCLoserSlow();
        });

        enemy.sort(Comparator.comparingInt(enemy -> enemy.distanceToPlayer));

        enemy.forEach(enemy1 -> enemy1.isTouching = false);


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
