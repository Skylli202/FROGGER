package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import game.BiblioEntity;
import game.Packet;
import game.frame.GameFrameSolo;

public class Client extends Thread{
	private Socket socket;
	private int port;
	private String ip;
	private String dataRead;
	private GameFrameSolo gameFrameSolo;
	
	public boolean running = true;
	
	public Client(String ip, int port, GameFrameSolo gameFrameSolo) {
		this.ip=ip;
		this.port=port;
		this.gameFrameSolo = gameFrameSolo;
	}
	
	public Client(Socket s, GameFrameSolo gameFrameSolo) {
		this.socket = s;
		this.gameFrameSolo = gameFrameSolo;
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
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			
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
				
//				oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(packet);
				oos.flush();
    		}
			
			buffRead.close();
			printWriter.close();
			oos.close();
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