package gamelogic;

import Items.GreenSquare;
import Items.Item;
import Items.YellowTriangle;
import Items.WhiteCircle;

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
    Random rand = new Random();
    ArrayList<Projectile> projectile = new ArrayList<>();
    ArrayList<Item> items= new ArrayList<>();
    int enemySpawnEscalation = 1;


    boolean kUP=false;
    boolean kLEFT=false;
    boolean kRIGHT=false;
    boolean kDOWN=false;


    public GamePane() {
        setBackground(Color.BLUE);
        player= new Player();
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setVisible(true);
        timer = new Timer(25,this);
        timer.start();
    }


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        switch (key) {
            case KeyEvent.VK_W:
                kUP = true;
                break;

            case KeyEvent.VK_D:
                    kRIGHT = true;
                    break;
            case KeyEvent.VK_S:
                    kDOWN = true;
                    break;

            case KeyEvent.VK_A:
                    kLEFT = true;
                    break;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        enemy.forEach(enemy1 -> enemy1.draw(g));
        g.setColor(Color.RED);
        projectile.forEach(orb1->orb1.draw(g));

        items.forEach(item1 -> {
            item1.draw(g);
        });

        g.setColor(Color.green);
        player.draw(g);
    }



    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        now = Instant.now();

        if (Duration.between(start, now).toSeconds() >= 1) {
            for (int i = 0; i < enemySpawnEscalation; i++) {
                switch (rand.nextInt(1, 5)) {
                    case 1:
                        Enemy enemy1 = new Enemy(20, rand.nextInt(1060), player);
                        enemy.add(enemy1);
                        break;
                    case 2:

                        Enemy enemy2 = new Enemy(rand.nextInt(1900), 1060, player);
                        enemy.add(enemy2);
                        break;
                    case 3:
                        Enemy enemy3 = new Enemy(1900, rand.nextInt(1060), player);
                        enemy.add(enemy3);
                        break;
                    case 4:
                        Enemy enemy4 = new Enemy(rand.nextInt(1900), 20, player);
                        enemy.add(enemy4);
                        break;

                }
                start = Instant.now();
            }
            enemySpawnEscalation++;
        }

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

        if (!enemy.isEmpty() && player.hitbox.intersects(enemy.getFirst().hitbox)){
            enemy.clear();
            projectile.clear();
            items.clear();
            player.resetStats();
            enemySpawnEscalation=1;
        }

        for (int i=0; i<items.size();i++){
            if(player.hitbox.intersects(items.get(i).hitbox)){
                items.get(i).increaseStat();
                items.remove(i);
            }
        }

        for (int j = 0; j < enemy.size(); j++) {
            enemy.get(j).getCloser();
            for (int i = 0; i < j; i++) {
                if (enemy.get(j).hitbox.intersects(enemy.get(i).hitbox)){
                    enemy.get(j).revertPosition();
                    break;
                }
            }
        }




        if (!enemy.isEmpty() && !projectile.isEmpty()) {
            for (int i = 0; i < enemy.size(); i++) {
                for (int j = 0; j < projectile.size(); j++) {
                    if (enemy.get(i).hitbox.intersects(projectile.get(j).hitbox)) {
                        if (rand.nextInt(100)<=20){
                            switch (rand.nextInt(4)){
                                case 1:
                                     items.add(new YellowTriangle(enemy.get(i).x,enemy.get(i).y,player));
                                     break;
                                case 2:
                                    items.add(new GreenSquare(enemy.get(i).x,enemy.get(i).y,player));
                                    break;
                                case 3:
                                    items.add(new WhiteCircle(enemy.get(i).x,enemy.get(i).y,player));
                                    break;
                            }
                        }
                        enemy.remove(i);
                        try {
                        projectile.get(i).pierce--;
                        }catch (IndexOutOfBoundsException e){
                            break;
                        }
                        if (projectile.get(i).pierce<1){
                        projectile.remove(j);
                        }
                        break;
                    }

                }
            }
        }

        for(int i=0;i<projectile.size();i++) {
            if (Duration.between(projectile.get(i).birth, now).toSeconds() > 2) {
                projectile.remove(i);
            }
        }


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
            case KeyEvent.VK_W:
                kUP = false;
                break;

            case KeyEvent.VK_D:
                kRIGHT = false;
                break;
            case KeyEvent.VK_S:
                kDOWN = false;
                break;

            case KeyEvent.VK_A:
                kLEFT = false;
                break;
        }
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Instant birth= now;
        projectile.add(player.shoot(new Point(mouseEvent.getX(),mouseEvent.getY()),birth));
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
