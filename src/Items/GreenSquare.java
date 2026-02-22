package Items;

import gamelogic.Player;

import java.awt.*;

public class GreenSquare extends Item {
    int x;
    int y;
    Player player;
    public GreenSquare(int x, int y, Player player){
        this.x = x;
        this.y = y;
        hitbox = new Rectangle(x,y,10,10);
        this.player = player;
    }

    @Override
    public void increaseStat() {
        player.setProjectileSize(player.getProjectileSize()+10);
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.GREEN);
        g.fillRect(x,y,18,18);
    }
}

