package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.frame.GameFrameMaster;

public class Car extends Entity {
	private static final long serialVersionUID = 1L;
	
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
			x += GameFrameMaster.getLevel();
		} else {
			x -= GameFrameMaster.getLevel();
		}
	}
	
	public String isWhat() {
		return "Car";
	}
	
	private void checkIfNotOut(){
    	ArrayList<Entity> tempList;
    	
    	//carOne
    	tempList = GameFrameMaster.getBiblioEntity().get("carOne");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 350){
        	GameFrameMaster.getBiblioEntity().initCar(1);
        }
    	
    	//carTwo
    	tempList = GameFrameMaster.getBiblioEntity().get("carTwo");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x >= 700){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x >= 180){
        	GameFrameMaster.getBiblioEntity().initCar(2);
        }
        
        //carThree
    	tempList = GameFrameMaster.getBiblioEntity().get("carThree");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 400){
        	GameFrameMaster.getBiblioEntity().initCar(3);
        }
        
        //carFour
		tempList = GameFrameMaster.getBiblioEntity().get("carFour");
		for(int i = 0;i < tempList.size(); i++){
			if(tempList.get(i).x >= 700){
				tempList.remove(tempList.get(i));
		    	}
		}
		
		if(tempList.get(tempList.size()-1).x >= 90){
			GameFrameMaster.getBiblioEntity().initCar(4);
		}
		
		//carFive
    	tempList = GameFrameMaster.getBiblioEntity().get("carFive");
        for(int i = 0;i < tempList.size(); i++){
        	if(tempList.get(i).x <= -100){
        		tempList.remove(tempList.get(i));
            	}
        }

        if(tempList.get(tempList.size()-1).x <= 400){
        	GameFrameMaster.getBiblioEntity().initCar(5);
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
			return new ImageIcon(getClass().getResource("/car_1.png")).getImage();
		case 2:
			return new ImageIcon(getClass().getResource("/car_2.png")).getImage();
		case 3:
			return new ImageIcon(getClass().getResource("/car_3.png")).getImage();
		case 4:
			return new ImageIcon(getClass().getResource("/car_4.png")).getImage();
		case 5:
			return new ImageIcon(getClass().getResource("/car_5.png")).getImage();
		}
		return null;
	}
	
	public String toString() {
		return "hello i'm a car";
	}
}
