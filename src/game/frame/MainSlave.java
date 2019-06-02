package game.frame;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainSlave {
	
	public static final int WIDTH = 600;
	public static final int HEIGHT = 890;
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private JFrame frame;
	
	public MainSlave(String username, String ipaddress, String port) {
		WindowListener exitListener = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showOptionDialog(frame,
                        "Are You Sure to Close this Application?",
                        "Exit Confirmation", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, null, null);
                if (confirm == 0) {
                    System.exit(0);
                }
            }
        };
		
		frame = new JFrame("FROGGER GAME - SLAVE");
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.add(new GameFrameSlave(username, ipaddress, port));
		frame.addWindowListener(exitListener);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setVisible(true);
	}
}
