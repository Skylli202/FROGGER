package network;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ScoreManager {
	private Properties properties;
	public ScoreManager() {
		properties = new Properties();
//		System.out.println("scoreManager Init");
	}
	
	public void write(String key, String value) {
	
		try {
			FileInputStream fis = new FileInputStream("./score/score.properties");
			properties.load(fis);
			fis.close();

			setScore(key, value);
			
			FileOutputStream fos = new FileOutputStream("./score/score.properties");
			properties.store(fos, "STORED SCORE OF FROGGER GAME");
			fos.close();
	
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	private void setScore(String key, String value) {
		String oldScore = properties.getProperty(key);
//		System.out.println(oldScore);
		
		if(oldScore == null) {
			properties.setProperty(key, value);
		} else if(Integer.parseInt(value) > Integer.parseInt(oldScore)) {
			properties.setProperty(key, value);
		}
	}
}