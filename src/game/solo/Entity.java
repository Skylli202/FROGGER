package game.solo;

import java.awt.*;

public abstract class Entity {

    int x, y, initialX, initialY;

    public Entity(int x, int y){
        this.x = x;
        this.y = y;
        initialX = x;
        initialY = y;
    }
    
    public abstract String isWhat();

    public abstract void update();
    
    public abstract Rectangle getBounds();

    public abstract void draw(Graphics2D g2d);
    
}
