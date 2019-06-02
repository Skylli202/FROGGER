package Server;

import java.awt.BorderLayout;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;

import game.BiblioEntity;
import network.Connection;

public class ServFrogger extends JFrame {
	private static final long serialVersionUID = 1L;
	
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
	private JTextArea scoreTextArea;
	private JTextArea logTextArea;

	// Network declaration
	private static final int port = 8542;
	private static ServerSocket serverSocket;
	private static Socket socket;
	
	//
	private BiblioEntity biblio;
	private ArrayList<JTextArea> list = new ArrayList<JTextArea>();


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
		list.add(connexionTextArea);
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
		list.add(scoreTextArea);
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
		list.add(logTextArea);
		logTextArea.setLineWrap(true);
		logTextArea.setEditable(false);
		DefaultCaret logCaret = (DefaultCaret)logTextArea.getCaret();
		logCaret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		
		logScrollPane.add(logTextArea);
		logScrollPane.setViewportView(logTextArea);
	}

	public void running() {
		JTextArea tabJTextArea[] = new JTextArea[3];
		tabJTextArea[0] = connexionTextArea;
		tabJTextArea[1] = scoreTextArea;
		tabJTextArea[2] = logTextArea;
//		spamTextArea(tab);
		
		try {
			serverSocket = new ServerSocket(port);
			boolean running = true;

			while(running) {
				connexionTextArea.append("Serveur UP attente de connexion... \n"); 
				socket = serverSocket.accept();
				if(socket != null){
					connexionTextArea.append("[INFO] nouvelle connexion : \n"+socket+"\n");
					System.out.println("[INFO] nouvelle connexion : \n"+socket+"\n");
					Connection connect = new Connection(socket, this);
					connect.start();
				}
			}
			serverSocket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	public void writeInScoreTab(String str) {
		scoreTextArea.append(str);
	}
	
	public void writeInLogsTab(String str) {
		logTextArea.append(str);
	}
	
	public void setBiblio(BiblioEntity b) {
		this.biblio = b;
	}
	
	public BiblioEntity getBiblio() { return this.biblio; }
	
	public ArrayList<JTextArea> getJTextAreaArray() {
		return list;
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
