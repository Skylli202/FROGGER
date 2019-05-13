package game.coop;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

public abstract class Player extends Entity {

	public Player(int x, int y) {
		super(x, y);
	}

	public abstract void update();

	public void draw(Graphics2D g2d){
        g2d.drawImage(getPlayerImg(),x,y,null);
    }

    public abstract Image getPlayerImg();
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, getPlayerImg().getWidth(null),
                getPlayerImg().getHeight(null));
    }

	public abstract void keyPressed(KeyEvent e);

	//public abstract void keyReleased(KeyEvent e);
}
