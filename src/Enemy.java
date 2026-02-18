import java.awt.*;

public class Enemy {
    Player player;
    int speed = 5;
    int x;
    int lastx;
    int y;
    int lasty;
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
        lastx = x;
        lasty = y;
        dx = player.x - this.x;

        if (dx > 0){
            x=x+speed;
            if (x>player.x) x=player.x;
        }else {
            x = x - speed;
            if (x<player.x) x=player.x;
        }

        dy = player.y - this.y;

        if (dy > 0){ y=y+speed;
        if (y>player.y) y=player.y;
    }else {
        y = y - speed;
        if (y<player.y) y=player.y;
    }

        hitbox.x=x+10;
        hitbox.y=y+10;

        distanceToPlayer = Math.abs(dx)+Math.abs(dy);
    }

    public void revertPosition(){
        x = lastx;
        y = lasty;

        hitbox.x=x+10;
        hitbox.y=y+10;
    }


}
