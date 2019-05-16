package Step1;

import java.net.InetAddress;
import java.net.Socket;

public class Client {
	private String ip = "192.168.1.18";
	private int port = 8088;
	
	public Client() throws Exception {
		// Serveur address is 192.168.1.18
		// Port is hard set to 8088
		
		Socket socket = new Socket(ip, port);
	}

}
