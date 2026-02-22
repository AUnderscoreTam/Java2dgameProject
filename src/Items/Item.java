package Items;

import java.awt.*;

public abstract class Item {
    public Rectangle hitbox;
   public abstract void increaseStat();

   public abstract void draw(Graphics g);
}
