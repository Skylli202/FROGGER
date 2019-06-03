package network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

import game.BiblioEntity;
import game.Packet;
import game.PacketMaster;
import game.PacketSlave;
import game.frame.GameFrame;
import game.frame.GameFrameSlave;
import game.frame.GameFrameSolo;

public class Client extends Thread{
	private Socket socket;
	private int port;
	private String ip;

	private ObjectInputStream ois;
	private ObjectOutputStream oos;

	private GameFrame gameFrame;
	private BiblioEntity tmpBiblio;
	
	public boolean running = true;

	public Client(String ip, int port, GameFrame gameFrame) {
		this.ip=ip;
		this.port=port;
		this.gameFrame = gameFrame;
	}

	public Client(Socket s, GameFrame gameFrame) {
		this.socket = s;
		this.gameFrame = gameFrame;
	}

	public void run() {
		// Quelques verification
		checkSocket();

		ois = null;
		oos = null;
		try {
			oos = new ObjectOutputStream(socket.getOutputStream());
			ois = new ObjectInputStream(socket.getInputStream());
			
			while(running) {
				try {
					Thread.sleep(1000);
					if(gameFrame instanceof GameFrameSolo) {
						Packet packetToSend = new PacketMaster(gameFrame.getUsername(), GameFrame.getScore(), GameFrameSolo.getBiblioEntity());
						oos.writeObject(packetToSend);
						oos.flush();
					} else if (gameFrame instanceof GameFrameSlave) {
						Packet packetToSend = new PacketSlave(gameFrame.getUsername(), GameFrame.getScore());
						oos.writeObject(packetToSend);
						oos.flush();
						
						Object objectReceived = ois.readObject();
						System.out.println("obj received = " + objectReceived);
						//gameFrame.setBiblioEntity((BiblioEntity) ois.readObject());
					}
					
				} catch (SocketException e) {
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

	private void checkSocket() {
		if(this.socket == null) {
			try {
				this.socket = new Socket(this.ip, this.port);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public BiblioEntity getTmpBiblio() {
		return tmpBiblio;
	}

//	public void setTmpBiblio(BiblioEntity tmpBiblio) {
//		this.tmpBiblio = tmpBiblio;
//	}
}