package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import game.solo.BiblioEntity;
import game.solo.GameFrameSolo;
import game.solo.Packet;

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
		checkSocket();
		try {
			BufferedReader buffRead = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			
			PrintWriter printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									socket.getOutputStream())), true);
			
			
			boolean tmp = true;
			while(running) {
				Thread.sleep(100);
				// little Ping Pong process :)
				while(tmp) {
					printWriter.println("Ping");
					dataRead = buffRead.readLine();
					if(dataRead.equals("Pong")) {
						tmp = false;
						System.out.println("Client connected to the server");
					}
				}
				
				Packet packet = new Packet(gameFrameSolo.getUsername(),Integer.toString(gameFrameSolo.getScore()),gameFrameSolo.getBiblio());
				
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				String str = "lala";
				oos.writeObject("lala");
				oos.writeObject(packet);
				oos.close();
    		}
			
			buffRead.close();
			printWriter.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void checkSocket() {
		if(this.socket == null) {
			try {
				this.socket = new Socket(this.ip, this.port);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}