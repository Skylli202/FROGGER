package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

import game.solo.BiblioEntity;

public class Connection extends Thread {
	private Socket socket;
	private JTextArea textArea;
	private BufferedReader buffRead;
    private PrintWriter printWriter;
    private boolean running;
    private String dataRead;
    private ScoreManager scoreManager;
    String username = "";
    String score = "";
    
    
    public Connection(Socket socket, JTextArea textArea) {
    	this.socket = socket;
    	this.textArea = textArea;
    	running = true;
    	scoreManager = new ScoreManager();
    }
    
    public void run() {
//    	textArea.append("[INFO] nouvelle connexion : \n"+socket+"\n");
    	try {
    		buffRead = new BufferedReader(
    				new InputStreamReader(
    						socket.getInputStream()));
    		
    		printWriter = new PrintWriter(
    				new  BufferedWriter(
    						new  OutputStreamWriter(
    								socket.getOutputStream ())),true);
    		
    		while(running) {
//    			dataRead = buffRead.readLine();
//    			textArea.append("Server receive = "+dataRead);
    			
    			if(dataRead.equalsIgnoreCase("Ping")) {
    				printClient("Pong");
    			}
    			
    			if(readUserData(dataRead) != -1) {
    				username = getUsername(dataRead, readUserData(dataRead));
    				score = getUserScore(dataRead, readUserData(dataRead));
    				textArea.append(username+" : "+score+" points. \n");
    				System.out.println(username+" : "+score+" points. \n");
    				scoreManager.write(username, score);
//    				Thread t = new Thread(scoreManager);
//    				t.start();
//    				scoreManager.writeDataUser(username, score);
    			}
    			
    			if(dataRead.contains("hashtable")) {
    				System.out.println("this is hastable ? : " + dataRead);
    			}
    			 
    			// --
    			System.out.println("Connexion avec le client : " + socket.getInetAddress());
    			
    			InputStream is = socket.getInputStream();
    			ObjectInputStream ois = new ObjectInputStream(is);
    			BiblioEntity table = (BiblioEntity) ois.readObject();
    			
    			System.out.println(table);
    			// --
    		}
    		socket.close();
    	} catch(Exception e) {
    		
    	}
    }
    
    public int readUserData(String s) {
    	int j = -1;
    	for(int i=0; i<s.length(); i++) {
    		if(s.charAt(i)=='-') {
    			j = i;
    		}
    	}
    	return j;
    }
    
    public String getUsername(String s, int indice) {
    	String res = "";
    	for(int i=0; i<indice; i++) {
    		res = res + s.charAt(i);
    	}
    	return res;
    }
    
    public String getUserScore(String s, int indice) {
    	String res = "";
    	for(int i=indice+1; i<s.length(); i++) {
    		res = res + s.charAt(i);
    	}
    	return res;
    }
    
    public void printClient(String s){
        printWriter.println(s);
    }
}
