package menu;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

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
//		    img = ImageIO.read(new File("res/Menu.png"));
			String imgPath = "/Menu.png";
			img = ImageIO.read(this.getClass().getResourceAsStream(imgPath));

		} catch (IOException e) {
		    e.printStackTrace();
		}
		
		Image dimg = img.getScaledInstance(background.getWidth(), background.getHeight(), Image.SCALE_SMOOTH);
		
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
		ArrayList<String> menuList = new ArrayList<String>();

		menuList.add("Jeu Maitre");
		menuList.add("Jeu Slave");
		menuList.add("Scoreboard");
		menuList.add("Quitter");
		
		new Menu(menuList);
	}

	public void actionPerformed(ActionEvent e) {
		if("Jeu Maitre".equals(e.getActionCommand())) {
			System.out.println("Jeu Maitre selectionner");
			new AskUserInfo(this, true);
			this.hide();
		}
		
		if("Jeu Slave".equals(e.getActionCommand())) {
			System.out.println("Jeu Esclave selectionner");
			new AskUserInfo(this, false);
			this.hide();
		}
		
		if("Scoreboard".equals(e.getActionCommand())) {
//			new AskScoreboard();
			System.out.println("Scoreboard is no longer supported in ESIREM Version of E.G. Frogger Game");
			this.hide();
		}
		
		if("Quitter".equals(e.getActionCommand())) {
//			System.out.println("quitter");
			System.exit(0);
		}
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
}
