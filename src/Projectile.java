import java.awt.*;
import java.time.Instant;


public class Projectile {
    Point destination;
    Point position;
    int speed;
    Instant birth;

    Projectile(Point startingPosition,Point destination, int speed, Instant birth){
        this.destination = destination;
        this.birth = birth;
        this.position = startingPosition;
        this.speed = speed;
        hitbox = new Rectangle(position.x,position.y,20,20);
    }

    Rectangle hitbox;

    public void draw(Graphics g){
        g.fillOval(position.x,position.y,20,20);
    }

    // projectiles move weird

    public void getCloser() {
        int dx = destination.x - position.x;
        if (Math.abs(dx)>=15) {
            if (dx > 0) {
                position.x = position.x + speed;
            } else {
                position.x = position.x - speed;
            }
        }
        int dy = destination.y - position.y;
        if (Math.abs(dy)>=15) {
            if (dy > 0) {
                position.y = position.y + speed;
            } else {
                position.y = position.y - speed;
            }
        }
        hitbox.x=position.x+10;
        hitbox.y=position.y+10;
    }

}
