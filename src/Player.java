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
    int speed = 7;

    public Player(){
        x = 500;
        y = 500;
    }
    public void draw(Graphics g){
        g.fillOval(x,y,50,50);
    }
    public Projectile shoot(Point destination, Instant birth){
        Point playerPoint = new Point(x,y);
        return new Projectile(playerPoint,destination,10,birth);
    }
}
