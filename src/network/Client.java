package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.Socket;

import game.solo.BiblioEntity;
import game.solo.GameFrameSolo;

public class Client extends Thread{
	private Socket socket;
	private int port = 8088;
	private String ip;
	private String dataRead;
	//private GameFrameCoop gameFrameCoop;
	private GameFrameSolo gameFrameSolo;
	private boolean isSolo;
	
	public boolean running = true;
	
//	public Client(String ip, int port, GameFrameCoop gameFrameCoop) {
//		this.ip=ip;
//		this.port=port;
//		this.gameFrameCoop = gameFrameCoop;
//		this.isSolo = false;
//	}
	
	public Client(String ip, int port, GameFrameSolo gameFrameSolo) {
		this.ip=ip;
		this.port=port;
		this.gameFrameSolo = gameFrameSolo;
		this.isSolo = true;
	}
	
	public Client(Socket s, GameFrameSolo gameFrameSolo) {
		this.socket = s;
		this.gameFrameSolo = gameFrameSolo;
		this.isSolo = true;
	}
	
	public void run() {
		try {
			System.out.println("Client started");
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(gameFrameSolo.getBiblio());
			oos.close();
			
			socket.close();
		} catch(Exception e) {}
	}
	
	public void sendMessage(PrintWriter writer, String msg) {
		writer.println(msg);
	}
	
	public void sendBiblio(BiblioEntity biblio) {
	
	}
}