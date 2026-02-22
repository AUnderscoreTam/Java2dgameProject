package gamelogic;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.time.Instant;

public class Player{
    int x;
    int y;
    Ellipse2D hitbox = new Ellipse2D() {
        @Override
        public double getX() {
            return x;
        }

        @Override
        public double getY() {
            return y;
        }

        @Override
        public double getWidth() {
            return 50;
        }

        @Override
        public double getHeight() {
            return 50;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public void setFrame(double x, double y, double w, double h) {

        }

        @Override
        public Rectangle2D getBounds2D() {
            return null;
        }
    };
    int speed;
    int pierce;
    int projectileSize;

    public int getProjectileSize() {
        return projectileSize;
    }

    public void setProjectileSize(int projectileSize) {
        this.projectileSize = projectileSize;
    }

    public int getPierce() {
        return pierce;
    }

    public void setPierce(int pierce) {
        this.pierce = pierce;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Player(){
        x = 500;
        y = 500;
        speed = 7;
        pierce = 1;
        projectileSize = 1;
    }
    public void resetStats(){
        x = 500;
        y = 500;
        speed = 7;
        pierce = 1;
        projectileSize = 1;
    }

    public void draw(Graphics g){
        g.fillOval(x,y,50,50);
    }

    public Projectile shoot(Point destination, Instant birth){
        return new Projectile(destination,50,birth,pierce,projectileSize);
    }
}
