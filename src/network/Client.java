package network;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import game.coop.GameFrameCoop;
import game.solo.GameFrameSolo;

public class Client extends Thread{
	private int port = 8088;
	private String ip;
	private String dataRead;
	private GameFrameCoop gameFrameCoop;
	private GameFrameSolo gameFrameSolo;
	private boolean isSolo;
	
	public boolean running = true;
	
	public Client(String ip, int port, GameFrameCoop gameFrameCoop) {
		this.ip=ip;
		this.port=port;
		this.gameFrameCoop = gameFrameCoop;
		this.isSolo = false;
	}
	
	public Client(String ip, int port, GameFrameSolo gameFrameSolo) {
		this.ip=ip;
		this.port=port;
		this.gameFrameSolo = gameFrameSolo;
		this.isSolo = true;
	}
	
	public void run() {
		try {
//			System.out.println("Client started");
			Socket socket = new Socket(ip,port);
			
			BufferedReader buffRead = new BufferedReader(
					new InputStreamReader(
							socket.getInputStream()));
			
			PrintWriter printWriter = new PrintWriter(
					new BufferedWriter(
							new OutputStreamWriter(
									socket.getOutputStream())), true);
			boolean tmp = true;
			while(running) {
				Thread.sleep(1000);
				while(tmp) {
					sendMessage(printWriter, "Ping");
					dataRead = buffRead.readLine();
					if(dataRead.equals("Pong")) {
						tmp = false;
						System.out.println("Client connected to the server");
					}
				}
				
				if(isSolo) {
					sendMessage(printWriter, gameFrameSolo.getUserData());
				} else {
					sendMessage(printWriter, gameFrameCoop.getUserData());
				}
				
//				if(dataRead.equals("Pong")) {
//					running = false;
//					sendMessage(printWriter, "END");
//				}
			}
			
			printWriter.close();
			buffRead.close();
			socket.close();
		} catch(Exception e) {}
	}
	
	public void sendMessage(PrintWriter writer, String msg) {
		writer.println(msg);
	}
}