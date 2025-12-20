import java.awt.*;

public class Enemy {
    Player player;
    int x;
    int y;

    public Enemy(int x, int y, Player player) {
        this.x = x;
        this.y = y;
        this.player = player;
    }

    public void draw(Graphics g) {
        g.fillRect(x, y, 50, 50);
    }

    public void getcloser() {
        int dx = player.x - this.x;
        if (dx > 0) x=x+5;
        else x = x-5;


        int dy = player.y - this.y;
        if (dy > 0) y=y+5;
        else y = y - 5;
    }

}
