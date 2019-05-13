package menu;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import game.coop.MainCoop;
import game.solo.MainSolo;

public class AskUserInfo implements ActionListener{
	private JFrame frame;
	
	private Menu parent;
	private boolean isSolo;
	
	private static JTextField user = new JTextField("Skylli");
	private static JTextField ipaddr = new JTextField("192.168.1.20");
	private static JTextField localPort = new JTextField("8088");
	
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	private String username;
	private String ipaddress;
	private String port;
	
	public AskUserInfo(Menu parent, boolean isSolo) {
		this.parent = parent;
		this.isSolo = isSolo;
		
		frame = new JFrame();
		frame.setLayout(new GridLayout(4,2));
		frame.setSize(new Dimension(300,300));
		
		
		user.setName("username");
		
		
		user.setName("ip_address");
		
		
		user.setName("port");
		
		JButton ok = new JButton("OK");
		ok.setActionCommand("ok");
		ok.addActionListener(this);
		
		JButton cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);
		
		frame.add(new JLabel("Username :"));
		frame.add(user);
		
		frame.add(new JLabel("IP Address :"));
		frame.add(ipaddr);
		
		frame.add(new JLabel("Port :"));
		frame.add(localPort);
		
		frame.add(ok);
		frame.add(cancel);
		
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setResizable(false);
		frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if("ok".equals(e.getActionCommand())) {
			username = user.getText();
			ipaddress = ipaddr.getText();
			port = localPort.getText();
			if(isSolo) {
				new MainSolo(username, ipaddress, port);
			} else {
				new MainCoop(username, ipaddress, port);
			}
			frame.dispose();
		}
		
		if("cancel".equals(e.getActionCommand())) {
			frame.dispose();
			parent.show();
		}
	}
}
