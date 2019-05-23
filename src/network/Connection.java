package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JTextArea;

import game.solo.BiblioEntity;
import game.solo.Packet;

public class Connection extends Thread {
	private Socket socket;
	private JTextArea tabJTextArea[];
	private BufferedReader buffRead;
    private PrintWriter printWriter;
    private boolean running;
    private boolean connected = false;
    private String dataRead;
    private ScoreManager scoreManager;
    String username = "";
    String score = "";
    
    
//    public Connection(Socket socket, JTextArea textArea) {
//    	this.socket = socket;
//    	this.textArea = textArea;
//    	running = true;
//    	scoreManager = new ScoreManager();
//    }
    
    public Connection(Socket socket, JTextArea tab[]) {
    	this.socket = socket;
    	this.tabJTextArea = tab;
    	running = true;
    	scoreManager = new ScoreManager();
    }
    
    public void run() {
    	try {
    		buffRead = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    		printWriter = new PrintWriter(new  BufferedWriter(new  OutputStreamWriter(socket.getOutputStream ())),true);
    		
    		while(running) {
    			if(!connected) {
    				dataRead = buffRead.readLine();
    				// Answer Pong to Ping
    				if(dataRead.equalsIgnoreCase("Ping")) {
    					printClient("Pong");
    					connected = true;
    				}
    			}
//    			System.out.println("hello");
    			
    			ObjectInputStream trial = new ObjectInputStream(socket.getInputStream());
    			Packet packetReceive = (Packet) trial.readObject();
    			System.out.println("packetReceive : "+ packetReceive);
    			
    			// Display data onto ServerFrame
    			tabJTextArea[1].append(packetReceive.printScore());
    			tabJTextArea[2].append("Data Received.\n");
    			
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
