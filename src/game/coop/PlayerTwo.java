package game.coop;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;

public class PlayerTwo extends Player{
    int timerCpt = 0;
    

    public PlayerTwo(int x, int y){
        super(x,y);
    }

    public void update(){
    	isPlouf();
    	checkCarCollisions();
    	checkEndGameCollisions();
//    	checkLife();
    	checkPlayerCollisions();
    	updatePosition();
    }
    
    public String isWhat() {
		return "PlayerTwo";
	}

    public Image getPlayerImg(){
        ImageIcon ic = new ImageIcon("./res/frogCarnivor.png");
        return ic.getImage();
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_UP){
            y += -55;
        } else if(key == KeyEvent.VK_DOWN){
            y += +55;
        } else if(key == KeyEvent.VK_LEFT){
            x += -55;
        } else if(key == KeyEvent.VK_RIGHT){
            x += 55;
        }
    }

    //keyReleased really needed ?
//    public void keyReleased(KeyEvent e){
//        int key = e.getKeyCode();
//
//        if(key == KeyEvent.VK_Z){
//            velY = 0;
//        } else if(key == KeyEvent.VK_S){
//            velY = 0;
//        } else if(key == KeyEvent.VK_Q){
//            velX = 0;
//        } else if(key == KeyEvent.VK_D){
//            velX = 0;
//        }
//    }
    
    public void checkPlayerCollisions() {
    	if(getBounds().intersects(GameFrameCoop.playerOne.getBounds()))
//    		resetPos();
    		GameFrameCoop.playerOne.dead();
    }
    
    public void checkEndGameCollisions() {
    	ArrayList<Rectangle> endGameArea = GameFrameCoop.getEndGameAreaList();
    	
    	for(int i = 0; i < endGameArea.size(); i++) {
    		if(getBounds().intersects(endGameArea.get(i).getBounds())) {
    			resetPos();
    		}
    	}
    }
    
    private void checkCarCollisions() {
		if(checkIsOnEntity()) {
			if(y>420) {
				resetPos();
			}
		}
	}
    
    private boolean checkIsOnEntity() {
    	boolean res = false;
    	BiblioEntity  tempBiblioFloat = GameFrameCoop.biblioEntity;
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
    	        		x += GameFrameCoop.level;
    			} else if((y >= 205 && y < 260)) { //secondLine
    				x -= GameFrameCoop.level;
    			} else if((y >= 260 && y < 315)) { //thirdLine
    				x += GameFrameCoop.level;
    			} else if((y >= 315 && y < 370)) { //fourthLine
    				if(timerCpt%2 == 0)
    	        		x += GameFrameCoop.level;
    			} else if((y >= 370 && y < 425)) { //fifthLine
    				if((timerCpt%3 == 0) || (timerCpt%3 == 1))
                		x -= GameFrameCoop.level;
    			}
    		}
    	}
    }
    
    private void isPlouf() {
    	if(checkIsInRiver()&& !checkIsOnEntity()) {
    		resetPos();
    	}
    }
    
    public void resetPos() {
    	x = 270;
    	y = 45;
    }
}
