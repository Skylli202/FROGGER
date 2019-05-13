package scorebord;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class Scoreboard {
	private JFrame frame;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	Properties properties;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Scoreboard() {
		frame = new JFrame("SCORE BOARD");
		scrollPane = new JScrollPane();
		textArea = new JTextArea();
		
		textArea.setFocusable(false);
		scrollPane.setViewportView(textArea);
		
		frame.getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
		frame.setSize(new Dimension(400,400));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setVisible(true);
		
		properties = new Properties();
		try {
			FileInputStream in = new FileInputStream("./score/score.properties");
			properties.load(in);
			in.close();
		} catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public Set<Object> getAllKeys(){
		Set<Object> keys = properties.keySet();
		return keys;
	}
	
	public String getValue(String key) {
		return properties.getProperty(key);
	}
	
	public void showAllScore() {
		Set<Object> keys = getAllKeys();
		
		for(Object k:keys){
            String key = (String)k;
            System.out.println(key+": "+properties.getProperty(key));
            textArea.append("User : "+key+" had scored "+properties.getProperty(key)+" points. Good Job !\n");
        }
	}
	
	public void showRanking() {
		int length = this.size();
//		System.out.println("lenght "+this.size());
//		while(cpt != length) {
		for(int rank=1; rank <= length; rank++) {
			Set<Object> keys = getAllKeys();
			int maxScore = 0;
			for(Object k:keys){
				String key = (String) k;
				if(Integer.parseInt(properties.getProperty(key)) > maxScore) {
					maxScore = Integer.parseInt(properties.getProperty(key));
				}
			}
			textArea.append(rank+". "+getKeyFromValue(""+maxScore)+" with "+maxScore+" points. \n");
			properties.remove(getKeyFromValue(""+maxScore));
			System.out.println(maxScore);
		}
	}
	
	public String getKeyFromValue(String value) {
		Set<Object> keys = getAllKeys();
		String res="";
		
		for(Object k:keys){
			String key = (String) k;
			if(properties.getProperty(key).equals(value)) {
				res = key;
			}
		}
		
		return res;
	}
	
	public int size() {
		int cpt = 0;
		Set<Object> keys = getAllKeys();
		for(Object k:keys) {
			cpt++;
		}
		return cpt;
	}
}
