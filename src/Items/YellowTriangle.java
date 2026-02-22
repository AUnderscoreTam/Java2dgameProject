package Items;

import gamelogic.Player;
import java.awt.*;

public class YellowTriangle extends Item {
    int x;
    int y;
    Player player;
    public YellowTriangle(int x, int y,Player player){
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x,y,10,10);
        this.player = player;
    }

    @Override
    public void increaseStat() {
        player.setPierce(player.getPierce()+1);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.YELLOW);
        g.fillPolygon(new int[]{x - 18, x ,x + 18}, new int[]{y + 18, y - 18, y + 18}, 3);
    }
}
