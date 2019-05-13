package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu implements ActionListener {
	private JFrame frame;
	private JPanel panel;
	private JPanel background;
	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	
	public Menu(ArrayList<String> menuList) {
		frame = new JFrame("FROGGER GAME MENU");
		frame.setSize(500, 500);
		frame.setLayout(new BorderLayout());
		
		background = new JPanel();
		background.setLayout(new BorderLayout());
		background.setSize(new Dimension(420,470));
		
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File("./res/Menu.png"));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(background.getWidth(), background.getHeight(),
		        Image.SCALE_SMOOTH);
		
		ImageIcon imageIcon = new ImageIcon(dimg);
		
		JLabel label = new JLabel();
		
		label.setIcon(imageIcon);
		
		background.add(label);
		
		frame.getContentPane().add(background, java.awt.BorderLayout.CENTER);
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(4,1));
		for(int i=0; i<menuList.size(); i++) {
			JButton b = new JButton(menuList.get(i));
			b.setActionCommand(menuList.get(i));
			b.addActionListener(this);
			panel.add(b);
		}
		frame.getContentPane().add(panel, java.awt.BorderLayout.WEST);
		frame.setResizable(false);
		frame.setLocation((int)(screenSize.getWidth()/2)-(frame.getWidth()/2), ((int) (screenSize.getHeight()/2)-(frame.getHeight()/2)));
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		Properties prop = new Properties();
		InputStream input = null;
		
		ArrayList<String> menuList = new ArrayList<String>();

		try {
			input = new FileInputStream("./config/config.properties");
			
			prop.load(input);
			
			menuList.add(prop.getProperty("menuone"));
			menuList.add(prop.getProperty("menutwo"));
			menuList.add(prop.getProperty("menuthree"));
			menuList.add(prop.getProperty("menufour"));

		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(menuList.isEmpty()) {
			menuList.add("item1");
			menuList.add("item2");
			menuList.add("item3");
			menuList.add("item4");
		}
		
		new Menu(menuList);
	}

	public void actionPerformed(ActionEvent e) {
		if("Jeu Solo".equals(e.getActionCommand())) {
			System.out.println("Solo Mode Selectionned");
			new AskUserInfo(this, true);
			this.hide();
		}
		
		if("Jeu Coop".equals(e.getActionCommand())) {
			System.out.println("Coop Mode Selectionned");
			new AskUserInfo(this, false);
			this.hide();
		}
		
		if("Scoreboard".equals(e.getActionCommand())) {
			new AskScoreboard();
			this.hide();
		}
		
		if("Quitter".equals(e.getActionCommand())) {
			System.out.println("quitter");
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
}
