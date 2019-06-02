package network;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import Server.ServFrogger;
import game.Packet;
import game.PacketMaster;
import game.PacketSlave;

public class Connection extends Thread {
	private Socket socket;
	private String ip;
	private int port;
	
	private ServFrogger srv;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private boolean running;
	
	public Connection(Socket socket, ServFrogger srv) {
		this.socket = socket;
		this.srv = srv;
		running = true;
	}

	public void run() {
		checkSocket();
		
		ois = null;
		oos = null;
		try {
			ois = new ObjectInputStream(socket.getInputStream());
			oos = new ObjectOutputStream(socket.getOutputStream());
			
			while(running) {
				try {
					Object objectReceived = ois.readObject();
					RenderToUI((Packet)objectReceived);
					
					if(objectReceived instanceof PacketMaster) {
						srv.setBiblio(((PacketMaster) objectReceived).getBiblio());
						srv.writeInLogsTab("Biblio updated \n");
					}
					
					if(objectReceived instanceof PacketSlave) {
						oos.writeObject(srv.getBiblio());
						oos.flush();
					}
										
					// Listener
//					System.out.println("objectReceived = " + objectReceived.toString());
				} catch (EOFException e) {
					running = false;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {if(ois != null) ois.close();} catch (IOException e) {e.printStackTrace();}
			try {if(oos != null) oos.close();} catch (IOException e) {e.printStackTrace();}
			try {if(socket != null) socket.close();} catch (IOException e) {e.printStackTrace();}
		}
	}

	private void RenderToUI(Packet packetReceived) {
		srv.writeInScoreTab(packetReceived.printScore());
		srv.writeInLogsTab("Data Received\n");
	}

	private void checkSocket() {
		if(this.socket == null) {
			try {
				this.socket = new Socket(this.ip, this.port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
