import java.awt.*;

public class Enemy {
    Boolean isTouching = false;
    Player player;
    int speed = 5;
    int x;
    int y;
    int dx;
    int dy;
    int distanceToPlayer=99999;
    Rectangle hitbox;

    public Enemy(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
        hitbox = new Rectangle(x,y,20,20);
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 30, 30);
    }

    public void getCloser() {
        dx = player.x - this.x;

        if (dx > 0) x=x+speed;
        else x = x-speed;

        dy = player.y - this.y;

        if (dy > 0) y=y+speed;
        else y = y - speed;

        hitbox.x=x+10;
        hitbox.y=y+10;

        distanceToPlayer = Math.abs(dx)+Math.abs(dy);
    }

    public void getCLoserSlow(){
        dx = player.x - this.x;

        if (dx > 0) x=x+speed/5;
        else x = x-speed/5;

        dy = player.y - this.y;

        if (dy > 0) y=y+speed/5;
        else y = y - speed/5;

        hitbox.x=x+10;
        hitbox.y=y+10;

        distanceToPlayer = Math.abs(dx)+Math.abs(dy);

    }


}
