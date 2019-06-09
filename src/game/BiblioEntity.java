package game;

import java.awt.Graphics2D;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class BiblioEntity implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private Hashtable<String, ArrayList<Entity>> HT;
	
	public BiblioEntity() {
		this.HT = new Hashtable<String, ArrayList<Entity>>();
	}
	
	/*public void setHT(Hashtable<String, ArrayList<Entity>> HT) {
		this.HT = HT;
	}*/
	
	public Hashtable<String, ArrayList<Entity>> getHT(){
		return this.HT;
	}
	
	public void put(String s, ArrayList<Entity> list) {
    	HT.put(s, list);
    }
	
	public ArrayList<Entity> get(String s){
		return HT.get(s);
	}
	
    public int size(){
    	return HT.size();
    }
    
    public Set<String> keySet() {
    	return HT.keySet();
    }
    
	public void put(String s, Floatable f) {
		if(HT.get(s) == null) {
			ArrayList<Entity> entityList = new ArrayList<Entity>();
			entityList.add(f);
			this.HT.put(s, entityList);
		} else {
			HT.get(s).add(f);
		}
	}
	
	public void put(String s, Car c) {
		if(HT.get(s) == null) {
			ArrayList<Entity> entityList = new ArrayList<Entity>();
			entityList.add(c);
			this.HT.put(s, entityList);
		} else {
			HT.get(s).add(c);
		}
	}
	
	public void put(String s, Enemy c) {
		if(HT.get(s) == null) {
			ArrayList<Entity> entityList = new ArrayList<Entity>();
			entityList.add(c);
			this.HT.put(s, entityList);
		} else {
			HT.get(s).add(c);
		}
	}
	
	public void initFloatable(){
        initFloatable(1);
        initFloatable(2);
        initFloatable(3);
        initFloatable(4);
        initFloatable(5);
    }
	
	public void initFloatable(int i) {
    	
		switch(i) {
    		case 1:
    			this.put("firstRow", new Floatable(-130, 150, 1));
    			break;
    		case 2:
    			this.put("secondRow", new Floatable(670, 205, 0, true, 2, false, 0));
                break;
    		case 3:
    			this.put("thirdRow", new Floatable(-145, 260, 2));
    			this.put("thirdRow", new Floatable(-225, 260, 2));
    			break;
    		case 4:
    			this.put("fourthRow", new Floatable(-150, 315, 0));
                break;
    		case 5:
    			this.put("fifthRow", new Floatable(670, 370, 3, true, 3, false, 0));
    			break;
    			
    	}
    }
	
	public void initCar() {
		initCar(1);
		initCar(2);
		initCar(3);
		initCar(4);
		initCar(5);
	}
	
	public void initCar(int i ) {
		switch(i) {
			case 1:
				this.put("carOne", new Car(720, 480, 1, false));
				//this.putCar("carOne", new Car(840, 480, 1, false));
				break;
			case 2:
				this.put("carTwo", new Car(-120, 535, 2, true));
				break;
			case 3:
				this.put("carThree", new Car(720, 590, 3, false));
				break;
			case 4:
				this.put("carFour", new Car(-270, 645, 4, true));
				break;
			case 5:
				this.put("carFive", new Car(720, 700, 5, false));
				break;
		}
	}
	
	public void initSnake() {
		this.put("Snake", new Enemy(-130, 430, true));
	}
	
	public void initCroco() {
		this.put("firstRow", new Enemy(-130, 150, false));
	}
	
	public void draw(Graphics2D g2d) {
		String key ="";
    	Set<String> keys = this.keySet();
    	Iterator<String> itr = keys.iterator();
    	
    	while(itr.hasNext()) {
    		key = itr.next();
    		for(int i = 0; i < this.get(key).size(); i++) {
    			this.get(key).get(i).draw(g2d);
    			}
    		}
    }
	
	public void update() {
		String key ="";
    	Set<String> keys = this.keySet();
    	Iterator<String> itr = keys.iterator();
//    	System.out.println(keys);
    	
    	while(itr.hasNext()) {
    		key = itr.next();
    		for(int i = 0; i < this.get(key).size(); i++) {
    			this.get(key).get(i).update();
    			}
    		}
    }
	
	private void readObject(ObjectInputStream aInputStream)
			throws ClassNotFoundException, IOException {
		// perform the default de-serialization first
        aInputStream.defaultReadObject();
 
        // ensure that object state has not been corrupted or tampered with malicious code
        //validateUserInfo();
	}

	private void writeObject(ObjectOutputStream aOutputStream) 
			throws IOException {
        
        //ensure that object is in desired state. Possibly run any business rules if applicable.
        //checkUserInfo();
         
        // perform the default serialization for all non-transient, non-static fields
        aOutputStream.defaultWriteObject();
    }
	
	public String toString(){
		return this.HT.toString();
	}
	
}
