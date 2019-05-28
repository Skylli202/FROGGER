package network;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class ServerFrogger {
	private static int port = 8088;

	private static ServerSocket serverSocket;
	private static Socket socket;

	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

	public static void main(String[] args) throws Exception {
		JFrame frame = new JFrame("FROGGER SERVER");
		frame.setSize(new Dimension(350,350));
		frame.setLayout(new BorderLayout());

		JScrollPane scrollPane = new JScrollPane();

		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setFocusable(false);

		scrollPane.setViewportView(textArea);

		frame.getContentPane().add(scrollPane, java.awt.BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setVisible(true);


		try {
			serverSocket = new ServerSocket(port);
			int connectionCpt = 0; // Nb ppl connected
			boolean running = true;

			while(running) {
				textArea.append("Serveur Up attente de connexion \n");
				socket = serverSocket.accept();
				if(socket != null){
					textArea.append("[INFO] nouvelle connexion : \n"+socket+"\n");
					System.out.println("[INFO] nouvelle connexion : \n"+socket+"\n");
					connectionCpt++;
//					Connection connect = new Connection(socket, textArea);
//					connect.start();

					while(connectionCpt == 2) {
						Thread.sleep(2000);
						textArea.append("2 player found \n Starting the party...\n");
					}
				}
			}
			serverSocket.close();
		} catch (Exception e) { }
	}

}
