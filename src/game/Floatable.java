package game;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import game.frame.GameFrameSolo;

public class Floatable extends Entity {
	private static final long serialVersionUID = 1L;
	
    private int woodType;
	private boolean isTurtoise;
	private int turtoiseNb;
	private boolean isSubmersible;
	private int submersibleNb;
	private int timer = 0;
	
	public Floatable(int x, int y, int woodType,boolean turtoise, int turtoiseNb, boolean submersible, int submersibleNb) {
		super(x,y);
        this.woodType = woodType;
		this.isTurtoise = turtoise;
		this.turtoiseNb = turtoiseNb;
		this.isSubmersible = submersible;
		this.submersibleNb = submersibleNb;
	}
	
	public Floatable(int x, int y, int woodType) {
		super(x,y);
        this.woodType = woodType;
		this.isTurtoise = false;
		this.turtoiseNb = 0;
		this.isSubmersible = false;
		this.submersibleNb = 0;
	}
	
	public void update() {
		updatePosition();
        checkIfNotOut();
	}
	
	public void updatePosition() {
		timer += 1;
        if(isTurtoise){
        	if(turtoiseNb == 3) {
        		if((timer%3 == 0) || (timer%3 == 1))
            		x -= GameFrameSolo.getLevel();
        	} else {
        		x -= GameFrameSolo.getLevel();
        	}
            
        } else if(woodType == 1) {
        	if((timer%3 == 0) || (timer%3 == 1))
        		x += GameFrameSolo.getLevel();
        } else if(woodType == 2) {
            x += GameFrameSolo.getLevel();
        } else {
        	if(timer%2 == 0)
        		x += GameFrameSolo.getLevel();
        }
	}
	
	public String isWhat() {
		return "Floatable";
	}
	
	public void draw(Graphics2D g2d) {
            g2d.drawImage(getFloatableImg(),x,y,null);
	}
	
	public Rectangle getBounds() {
        return new Rectangle(x, y, getFloatableImg().getWidth(null),
        		getFloatableImg().getHeight(null));
    }
	
	private Image getFloatableImg() {
		if(isTurtoise) {
			if(turtoiseNb == 1) {
				return new ImageIcon("./res/simpleTurtoise.jpg").getImage();
			} else if(turtoiseNb == 2) {
				return new ImageIcon("./res/doubleTurtoise.jpg").getImage();
			} else if(turtoiseNb == 3) {
				return new ImageIcon("./res/tripleTurtoise.jpg").getImage();
			} else {
				System.out.println("turtoiseNb doesn't match with 1, 2 or 3");
				return null;
			}
		} else if(isSubmersible) {
			if(submersibleNb == 2) {
				return new ImageIcon("./res/doubleTurtoise.jpg").getImage();
			} else if(submersibleNb == 3) {
				return new ImageIcon("./res/tripleTurtoise.jpg").getImage();
			} else {
				System.out.println("submersibleNb doesn't match with 2 or 3");
				return null;
			}
		} else if ((woodType == 1) || (woodType == 2)) {
			return new ImageIcon("./res/log_00.png").getImage();
		} else {
			return new ImageIcon("./res/log_000.png").getImage();
        } 
	}
        
    private void checkIfNotOut(){
    	for(int j = 0; j < GameFrameSolo.getBiblioEntity().size(); j++){
//    		if(j==0){
//    			ArrayList<Entity> tempList = GameFrameCoop.biblioEntity.get("firstRow");
//                for(int i = 0;i < tempList.size(); i++){
//                	if(tempList.get(i).x >= 700){
//                		tempList.remove(tempList.get(i));
//                    	}
//                }
//
//                if(tempList.get(tempList.size()-1).x >= 180){
//                	GameFrameCoop.biblioEntity.initFloatable(1);
//                }
//    		}
    		
    		if(j==0){
//    			System.out.println(GameFrameCoop.intervalWoodCroco);
    			ArrayList<Entity> tempList = GameFrameSolo.getBiblioEntity().get("firstRow");
                for(int i = 0;i < tempList.size(); i++){
                	if(tempList.get(i).x >= 700){
                		tempList.remove(tempList.get(i));
                    	}
                }
                if(GameFrameSolo.getIntervalWoodCroco() / 4 != 1) {
                	if(tempList.get(tempList.size()-1).x >= 180){
                		GameFrameSolo.getBiblioEntity().initFloatable(1);
                		GameFrameSolo.setIntervalWoodCroco(GameFrameSolo.getIntervalWoodCroco() + 1);
                	}
                }
                
                if(GameFrameSolo.getIntervalWoodCroco() / 4 == 1) {
                	GameFrameSolo.setIntervalWoodCroco(GameFrameSolo.getIntervalWoodCroco() - 4);
                		GameFrameSolo.getBiblioEntity().initCroco();
                		tempList.remove(tempList.get(tempList.size()-2));
                }
    		}
    	
                
            if(j==1){
            	ArrayList<Entity> tempList = GameFrameSolo.getBiblioEntity().get("secondRow");
                for(int i = 0;i < tempList.size(); i++){
                	if(tempList.get(i).x <= -100){
                		tempList.remove(tempList.get(i));
                    }
                }

                if(tempList.get(tempList.size()-1).x <= 500){
                	GameFrameSolo.getBiblioEntity().initFloatable(2);
                }
            }
                
            if(j==2){
                ArrayList<Entity> tempList = GameFrameSolo.getBiblioEntity().get("thirdRow");
                for(int i = 0;i < tempList.size(); i++){
                    if(tempList.get(i).x >= 700){
                        tempList.remove(tempList.get(i));
                    }
                }

                if(tempList.get(tempList.size()-1).x >= 250){
                    GameFrameSolo.getBiblioEntity().initFloatable(3);
                }
            }
                
            if(j==3){
                ArrayList<Entity> tempList = GameFrameSolo.getBiblioEntity().get("fourthRow");
                for(int i = 0;i < tempList.size(); i++){
                    if(tempList.get(i).x >= 700){
                        tempList.remove(tempList.get(i));
                    }
                }

                if(tempList.get(tempList.size()-1).x >= 100){
                    GameFrameSolo.getBiblioEntity().initFloatable(4);
                }
            }
                
            if(j==4){
            	ArrayList<Entity> tempList = GameFrameSolo.getBiblioEntity().get("fifthRow");
                for(int i = 0;i < tempList.size(); i++){
                	if(tempList.get(i).x <= -100){
                		tempList.remove(tempList.get(i));
                    }
                }

                if(tempList.get(tempList.size()-1).x <= 450){
                	GameFrameSolo.getBiblioEntity().initFloatable(5);
                }
            }
        }
    }
}
