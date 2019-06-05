package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import javax.swing.ImageIcon;

import game.frame.GameFrameMaster;

public class Player {
	
	boolean GOD_MODE = true;
	
    int x = 0, y = 0, velX = 0, velY = 0;
    int speed = 2;
    int timerCpt = 0;
    int yMin;
    
    
    ArrayList<Rectangle> endGameArea = GameFrameMaster.getEndGameAreaList();
    private boolean[] Arrive = new boolean[endGameArea.size()];
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        yMin = y;
        initArrive();
    }

    public void update(){
    	//System.out.println(x+" / "+y);
    	isPlouf();
    	checkCarCollisions();
    	checkCollisionsEndGame();
    	checkLife();
    	updatePosition();
    	checkEndedLevel();
    }
    
    public String isWhat() {
		return "PlayerOne";
	}
    
    public void initArrive() {
    	for (int i=0; i<getArrive().length; i++)
            getArrive()[i] = false;
    }
    
    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon(getClass().getResource("/frog.png"));
        return ic.getImage();
    }
    
    public void draw(Graphics2D g2d){
        g2d.drawImage(getPlayerImg(),x,y,null);
    }

    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_Z){
            y += -55;
            if(y<yMin) {
            	yMin = y;
            	GameFrameMaster.setScore(GameFrameMaster.getScore() + 10);
            }
        } else if(key == KeyEvent.VK_S){
            y += +55;
        } else if(key == KeyEvent.VK_Q){
            x += -55;
        } else if(key == KeyEvent.VK_D){
            x += 55;
        } else if(key == KeyEvent.VK_C) {
        	if(GOD_MODE) {
        		GOD_MODE = false;
        	} else {
        		GOD_MODE = true;
        	}
        } else if (key == KeyEvent.VK_V) {
        	GameFrameMaster.setLife(GameFrameMaster.getLife() + 1);
        }
    }

    //keyReleased really needed ?
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_Z){
            velY = 0;
        } else if(key == KeyEvent.VK_S){
            velY = 0;
        } else if(key == KeyEvent.VK_Q){
            velX = 0;
        } else if(key == KeyEvent.VK_D){
            velX = 0;
        }
    }
    
    public void checkEndedLevel() {
    	int cpt = 0;
    	for (int i=0; i<getArrive().length; i++) {
    		if(getArrive()[i]) {
    			cpt++;
    		}
    	}
    	
    	if(cpt==getArrive().length) {
    		initArrive();
    		//send score to server
    		GameFrameMaster.setGameLevel(GameFrameMaster.getGameLevel() + 1);
    	}
    }
    
    
	public void checkCollisionsEndGame() {
		boolean intersect = false;
		for(int i = 0; i < endGameArea.size(); i++) {
			if(getBounds().intersects(endGameArea.get(i).getBounds())){
			intersect = true;
			if(!getArrive()[i]) {	
				getArrive()[i] = true;
				GameFrameMaster.setScore(GameFrameMaster.getScore()+50);
				GameFrameMaster.setNbArrive(GameFrameMaster.getNbArrive() + 1);
				x = 270;
				y = 760;
			}
			else {
				x = 270;
				y = 760;
				GameFrameMaster.setLife(GameFrameMaster.getLife() - 1);
			}
			}
		}
		if (!intersect&&y==100){
			x = 270;
			y = 760;
			GameFrameMaster.setLife(GameFrameMaster.getLife() - 1);
		}
		                
	}
    
    private void checkCarCollisions() {
		if(checkIsOnEntity()) {
			if(y>420) {
				dead();
			}
		}
	}
    
    private boolean checkIsOnEntity() {
    	boolean res = false;
    	BiblioEntity  tempBiblioFloat = GameFrameMaster.getBiblioEntity();
    	String key ="";
    	Set<String> keys = tempBiblioFloat.keySet();
    	Iterator<String> itr = keys.iterator();
    	
    	while(itr.hasNext()) {
    		key = itr.next();
    		for(int i = 0; i < tempBiblioFloat.get(key).size(); i++) {
    			ArrayList<Entity> tmpList = tempBiblioFloat.get(key);
    			if(getBounds().intersects(tmpList.get(i).getBounds())){
    				res = true;
    				if(key.equals("firstRow")) {
    					String onWhatFrogIs = tmpList.get(i).isWhat();
    					if(onWhatFrogIs.equals("Enemy")) {
    						res = false;
    					}
    				}
    			}
    		}
    	}
    	return res;
    }

    private boolean checkIsInRiver() {
    	boolean res = false;
    	Rectangle river = new Rectangle(0,140,800,280);
    	if(getBounds().intersects(river.getBounds())) {
    		res = true;
    	}
		return res;
	}
    
    private void updatePosition() {
    	timerCpt += 1;
    	if(checkIsInRiver()) {
    		if(checkIsOnEntity()) {
    			if ((y >= 150 && y < 205)) { //firstLine
    				if((timerCpt%3 == 0) || (timerCpt%3 == 1))
    	        		x += GameFrameMaster.getLevel();
    			} else if((y >= 205 && y < 260)) { //secondLine
    				x -= GameFrameMaster.getLevel();
    			} else if((y >= 260 && y < 315)) { //thirdLine
    				x += GameFrameMaster.getLevel();
    			} else if((y >= 315 && y < 370)) { //fourthLine
    				if(timerCpt%2 == 0)
    	        		x += GameFrameMaster.getLevel();
    			} else if((y >= 370 && y < 425)) { //fifthLine
    				if((timerCpt%3 == 0) || (timerCpt%3 == 1))
                		x -= GameFrameMaster.getLevel();
    			}
    		}
    	}
    }
    
    private void isPlouf() {
    	if(checkIsInRiver()&& !checkIsOnEntity()) {
    		dead();
    	}
    }
    
    private void checkLife(){
        if (GameFrameMaster.getLife() == 0) {
            System.exit(0);
        }
    }
    
    public void dead() {
    	if(!GOD_MODE) {
    		x = 270;
    		y = 760;
    		GameFrameMaster.setLife(GameFrameMaster.getLife() - 1);
    		GameFrameMaster.setSec(30);
    	}
    }
    
    public void resetPos() {
    	if(!GOD_MODE) {
	    	x = 270;
	    	y = 760;
	    	GameFrameMaster.setSec(30);
    	}
    }
    
    public Rectangle getBounds() {
        return new Rectangle(x, y, getPlayerImg().getWidth(null),
                getPlayerImg().getHeight(null));
    }

	public boolean[] getArrive() {
		return Arrive;
	}

	public void setArrive(boolean[] arrive) {
		Arrive = arrive;
	}
}
