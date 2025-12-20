import java.awt.*;

public class Player{
    int x;
    int y;

    int hp;
    int level;
    int xp;
    int speed = 10;

    public Player(GamePane g){
        hp = 100;
        level =1;
        xp = 0;
        x = 500;
        y = 500;
    }
    public void draw(Graphics g){
        g.fillOval(x,y,50,50);
    }
}
