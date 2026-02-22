package Items;
import gamelogic.Player;
import java.awt.*;

public class WhiteCircle extends Item {
    int x;
    int y;
    Player player;
    public WhiteCircle(int x, int y, Player player){
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x,y,10,10);
        this.player =player;
    }

    @Override
    public void increaseStat() {
        player.setSpeed(player.getSpeed()+3);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        g.fillOval(x,y, 18,18);
    }
}

