package game.solo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Enemy extends Entity {
	
	private boolean isSnake;
	private int timerCpt;
	private int timer;

	public Enemy(int x, int y, boolean isSnake) {
		super(x,y);
		this.isSnake = isSnake;
		this.timerCpt = 0;
		this.timer = 0;
//		System.out.println("Constructor Called");
	}

	public void update() {
		updatePosition();
		checkIfNotOut();
		respawnTimer(15000); 
//		System.out.println("update Called");
	}
	
	private void updatePosition() {
		timer += 1;
		if(isSnake) {
			x += 1;
		} else {
			if((timer%3 == 0) || (timer%3 == 1))
        		x += GameFrameSolo.level;
		}
	}
	
	private void respawnTimer(int i) {
		timerCpt+=10;
//		System.out.println(timerCpt);
		
		if(timerCpt++/i == 1) {
			timerCpt -= i;
			GameFrameSolo.biblioEntity.initSnake();
		}
	}
	
	public String isWhat() {
		return "Enemy";
	}

	private void checkIfNotOut() {
		ArrayList<Entity> tempList;
		if(isSnake) {
			tempList = GameFrameSolo.biblioEntity.get("Snake");
//			System.out.println(tempList.size());
			if(tempList.size()>1) {
				for(int i=0; i<tempList.size(); i++) {
					if(tempList.get(i).x > 850) {
						tempList.remove(i);
					}
				}
			}
		} 
	}

	public Image getSnakeImg() {
		if(isSnake) {
			return new ImageIcon("./res/snake.png").getImage();
		} else {
			return new ImageIcon("./res/crocodile.png").getImage();
		}
	}
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getSnakeImg(), x, y, null);
//		System.out.println("draw Called");
	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, getSnakeImg().getWidth(null),
        		getSnakeImg().getHeight(null));
    }
}
