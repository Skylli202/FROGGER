package TCPExemple;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import game.solo.BiblioEntity;

public class TestClientTCP{
	final static int port = 9632;
	
	public static void main(String[] args) {
		Socket socket;
		DataInputStream userInput;
		PrintStream theOutputStream;
		
		try {
			//InetAddress serveur = InetAddress.getByName(args[0]);
			socket = new Socket("192.168.1.18", port);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			BiblioEntity bib = new BiblioEntity();
			
			out.println("yolo");
			System.out.println(in.readLine());
			
			out.println(bib);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}