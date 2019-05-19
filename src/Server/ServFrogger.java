package Server;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import network.Connection;

public class ServFrogger extends JFrame {
	// GUI declaration
	private JPanel contentPane;
	private JTabbedPane tabbedPane;
	private JPanel connexionPanel;
	private JPanel scorePanel;
	private JPanel logPanel;
	private JScrollPane connexionScrollPane;
	private JScrollPane scoreScrollPane;
	private JScrollPane logScrollPane;
	private JTextArea connexionTextArea;
	public JTextArea scoreTextArea;
	public JTextArea logTextArea;

	// Network declaration
	private static final int port = 8088;
	private static ServerSocket serverSocket;
	private static Socket socket;



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ServFrogger frame = new ServFrogger();
		frame.setVisible(true);

		frame.running();
	}

	/**
	 * Create the frame.
	 */
	public ServFrogger() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setTitle("[E.G.] Server Frogger");
		contentPane.setLayout(new BorderLayout(0, 0));

		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane);

		// Connexion Tab
		connexionPanel = new JPanel();
		tabbedPane.addTab("Connexion", null, connexionPanel, "Connexion logs");
		connexionPanel.setLayout(new BorderLayout(0, 0));

		connexionScrollPane = new JScrollPane();
		connexionPanel.add(connexionScrollPane);

		connexionTextArea = new JTextArea();
		connexionTextArea.setLineWrap(true);
		connexionTextArea.setEditable(false);
		DefaultCaret connexionCaret = (DefaultCaret)connexionTextArea.getCaret();
		connexionCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		connexionScrollPane.add(connexionTextArea);
		connexionScrollPane.setViewportView(connexionTextArea);

		// Score Tab
		scorePanel = new JPanel();
		tabbedPane.addTab("Score",  null, scorePanel, "Score of player currently playing");
		scorePanel.setLayout(new BorderLayout(0, 0));
		
		scoreScrollPane = new JScrollPane();
		scorePanel.add(scoreScrollPane);
		
		scoreTextArea = new JTextArea();
		scoreTextArea.setLineWrap(true);
		scoreTextArea.setEditable(false);
		DefaultCaret scoreCaret = (DefaultCaret)scoreTextArea.getCaret();
		scoreCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		scoreScrollPane.add(scoreTextArea);
		scoreScrollPane.setViewportView(scoreTextArea);
		
		// Log Tob
		logPanel = new JPanel();
		tabbedPane.addTab("Logs",  null, logPanel, "Logs of data Received");
		logPanel.setLayout(new BorderLayout(0, 0));
		
		logScrollPane = new JScrollPane();
		logPanel.add(logScrollPane);
		
		logTextArea = new JTextArea();
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		DefaultCaret logCaret = (DefaultCaret)logTextArea.getCaret();
		logCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		logScrollPane.add(logTextArea);
		logScrollPane.setViewportView(logTextArea);
	}

	public void running() {
		JTextArea tab[] = new JTextArea[1];
//		tab[0] = connexionTextArea;
//		tab[1] = scoreTextArea;
		tab[0] = logTextArea;
		spamTextArea(tab);
		
		try {
			serverSocket = new ServerSocket(port);
			int connectionCpt = 0; // Nb ppl connected
			boolean running = true;

			while(running) {
				connexionTextArea.append("Serveur Up attente de connexion \n"); 
				socket = serverSocket.accept();
				if(socket != null){
					connexionTextArea.append("[INFO] nouvelle connexion : \n"+socket+"\n");
					System.out.println("[INFO] nouvelle connexion : \n"+socket+"\n");
					connectionCpt++;
					Connection connect = new Connection(socket, scoreTextArea);
					connect.start();
					

					//while(connectionCpt == 2) {
						//Thread.sleep(2000);
						//textArea.append("2 player found \n Starting the party...\n");
					//}
				}
			}
			serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void spamTextArea(JTextArea textArea[]) {
		for(int i=0; i<100; i++) {
			for(int j=0; j<textArea.length; j++) {
				textArea[j].append("Hello : " + i + "\n");
			}
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void appendConnexionText(String str) {
		this.connexionTextArea.append(str);
	}

}
