package gamelogic;

import java.awt.*;
import java.time.Instant;


public class Projectile {
    Point destination;
    int speed;
    Instant birth;
    int pierce;
    int projectileSize;

    Projectile(Point destination, int speed, Instant birth,int pierce,int projectileSize){
        this.destination = destination;
        this.birth = birth;
        this.speed = speed;
        this.pierce = pierce;
        this.projectileSize =projectileSize;
        hitbox = new Rectangle(destination.x,destination.y,20+projectileSize,20+projectileSize);
    }

    Rectangle hitbox;

    public void draw(Graphics g){
        g.fillOval(destination.x,destination.y,20+projectileSize,20+projectileSize);
    }


}
