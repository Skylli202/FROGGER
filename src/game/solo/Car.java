package game.solo;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Car extends Entity {
	private int carType;
	private boolean moveLeftToRight;
	
	public Car (int x, int y, int carType, boolean moveLeftToRight) {
		super(x,y);
		this.carType = carType;
		this.moveLeftToRight = moveLeftToRight;
	}
	
	public void update() {
		updatePosition();
		checkIfNotOut();
		
		//System.out.println(GameFrame.biblioEntity.get("carFour").size());
	}
	
	private void updatePosition() {
		if(moveLeftToRight) {
			x += GameFrameSolo.level;
		} else {
			x -= GameFrameSolo.level;
		}
	}
	
	public String isWhat() {
		return "Car";
	}
	
	private void checkIfNotOut(){
    	ArrayList<Entity> tempList;
    	
    	//carOne
    	tempList = GameFrameSolo.biblioEntity.get("carOne");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 350){
        	GameFrameSolo.biblioEntity.initCar(1);
        }
    	
    	//carTwo
    	tempList = GameFrameSolo.biblioEntity.get("carTwo");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x >= 700){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x >= 180){
        	GameFrameSolo.biblioEntity.initCar(2);
        }
        
        //carThree
    	tempList = GameFrameSolo.biblioEntity.get("carThree");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 400){
        	GameFrameSolo.biblioEntity.initCar(3);
        }
        
        //carFour
		tempList = GameFrameSolo.biblioEntity.get("carFour");
		for(int i = 0;i < tempList.size(); i++){
			if(tempList.get(i).x >= 700){
				tempList.remove(tempList.get(i));
		    	}
		}
		
		if(tempList.get(tempList.size()-1).x >= 90){
			GameFrameSolo.biblioEntity.initCar(4);
		}
		
		//carFive
    	tempList = GameFrameSolo.biblioEntity.get("carFive");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 400){
        	GameFrameSolo.biblioEntity.initCar(5);
        }
        
    }
	
	public void draw(Graphics2D g2d) {
		g2d.drawImage(getCarImg(), x, y, null);
	}

	public Rectangle getBounds() {
        return new Rectangle(x, y, getCarImg().getWidth(null),
        		getCarImg().getHeight(null));
    }

	private Image getCarImg() {
		switch(carType) {
		case 1:
			return new ImageIcon("./res/car_1.png").getImage();
		case 2:
			return new ImageIcon("./res/car_2.png").getImage();
		case 3:
			return new ImageIcon("./res/car_3.png").getImage();
		case 4:
			return new ImageIcon("./res/car_4.png").getImage();
		case 5:
			return new ImageIcon("./res/car_5.png").getImage();
		}
		return null;
	}
	
	public String toString() {
		return "hello i'm a car";
	}
}
