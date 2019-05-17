package TCPExemple;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;

import game.solo.BiblioEntity;

public class TestClientTCP{
	final static int port = 8090;
	
	public static void main(String[] args) {
		Socket socket;
		DataInputStream userInput;
		PrintStream theOutputStream;
		
		try {
			//InetAddress serveur = InetAddress.getByName(args[0]);
			String str = "127.0.0.1";
			socket = new Socket(str, port);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintStream out = new PrintStream(socket.getOutputStream());
			
			//BiblioEntity bib = new BiblioEntity();
			
			out.println("Player One");
			System.out.println(in.readLine());
			
			BiblioEntity biblio = new BiblioEntity();
			biblio.initCar();
			
			ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
			oos.writeObject(biblio);
			oos.close();
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}