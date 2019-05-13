package game.solo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import network.Client;

public class GameFrameSolo extends JPanel implements ActionListener {

	private static final long serialVersionUID = 4L;

	static Timer mainTimer;
	static Player player;
	ImageIcon background = new ImageIcon("./res/backgroundResized2.png");

	static int life = 3;
	static int score = 0;
	String username;
	static int nbArrive = 0;

	static int sec = 30;
	static int timerCpt = 0;
	static int intervalWoodCroco = 0;

	static int level = 1;
	static int gameLevel = 1;

	static ArrayList<Rectangle> endGameArea = new ArrayList<Rectangle>();
	static ArrayList<Rectangle> hitBox = new ArrayList<Rectangle>();

	static BiblioEntity biblioEntity = new BiblioEntity();

	public GameFrameSolo(String username, String ipaddress, String port) {
		this.username = username;
		
		Client client = new Client(ipaddress, Integer.parseInt(port), this);
		client.start();

		setFocusable(true);
		initHitBox();
		biblioEntity.initFloatable();
		biblioEntity.initCar();
		biblioEntity.initSnake();
		initTimer();
		initPlayer();
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g2d.setColor(Color.white);

		drawBackground(g2d);
		hitBoxDraw(g2d, false);
		writeInfos(g2d);
		drawEndGameAreaFilled(g2d);
		biblioEntity.draw(g2d);
		drawTimerBar(g2d);
		drawPlayer(g2d);
	}
	
	// network things
	public String getUserData() {
		String res = getUsername()+"-"+getScore();
		return res;
	}
	
	public String getUsername() {
		return username;
	}
	
	public int getScore() {
		return score;
	}
	
	

	// Draw things
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(background.getImage(), 0, 0, null);
	}

	public void writeInfos(Graphics2D g2d) {
		g2d.drawString("Lifes: " + life, 10, 30);
		g2d.drawString("Score: " + score, 200, 30);
		g2d.drawString("Current Level: " + gameLevel, 390, 30);
		g2d.drawString("Timer: ", 10, 830);
	}

	public void drawEndGameAreaFilled(Graphics2D g2d) {
		ImageIcon winArea = new ImageIcon("./res/win_00.png");
		for (int i = 0; i < endGameArea.size(); i++) {
			if (player.Arrive[i])
				g2d.drawImage(winArea.getImage(), (int) endGameArea.get(i).getX() + 12,
						(int) endGameArea.get(i).getY() + 10, null);
		}
	}

	public void drawTimerBar(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(70, 810, sec * 10, 30);
	}

	public void drawPlayer(Graphics2D g2d) {
		player.draw(g2d);
	}

	public void hitBoxDraw(Graphics2D g2d, boolean shouldDraw) {
		if(shouldDraw) {
			for (int i = 0; i < hitBox.size(); i++) {
				g2d.draw(hitBox.get(i));
			}
			drawEndGameAreaHitBox(g2d);
		}
	}
	
	public void drawEndGameAreaHitBox(Graphics2D g2d) {
		Color color = g2d.getColor();
		g2d.setColor(Color.BLUE);
		for(int i=0; i<endGameArea.size(); i++) {
			g2d.draw(endGameArea.get(i));
		}
		g2d.setColor(color);
	}

	// Timer stuff
	public void actionPerformed(ActionEvent e) {
		updateTimer();
		player.update();
		biblioEntity.update();
		
		repaint();
		if (System.getProperty("os.name").equals("Linux"))
			Toolkit.getDefaultToolkit().sync();
	}

	public void updateTimer() {
		timerCpt += 10;
		if (timerCpt / 1000 == 1) {
			timerCpt -= 1000;
			sec--;
			if (sec == 0) {
				life--;
				player.resetPos();
				sec = 30;
			}
		}
	}

	// init Stuff
	private void initTimer() {
		mainTimer = new Timer(10, this);
		mainTimer.start();
	}

	private void initPlayer() {
		player = new Player(270, 760);
		addKeyListener(new KeyAdapt(player));
	}

	public void initHitBox() {
		Rectangle topArea = new Rectangle(0, 0, 800, 140);
		Rectangle river = new Rectangle(0, 140, 800, 280);
		Rectangle middleArea = new Rectangle(0, 420, 800, 50);
		Rectangle lowArea = new Rectangle(0, 747, 800, 50);
		hitBox.add(topArea);
		hitBox.add(river);
		hitBox.add(middleArea);
		hitBox.add(lowArea);
		initEndGameArea();
	}

	// Win Area
	private void initEndGameArea() {
		Rectangle firstArea = new Rectangle(110, 80, 50, 60);
		Rectangle secondArea = new Rectangle(270, 80, 50, 60);
		Rectangle thirdArea = new Rectangle(430, 80, 50, 60);
		endGameArea.add(firstArea);
		endGameArea.add(secondArea);
		endGameArea.add(thirdArea);
	}

	public void addEndGameArea(Rectangle r) {
		endGameArea.add(r);
	}

	public static void removeEndGameArea(Rectangle r) {
		endGameArea.remove(r);
	}

	public static ArrayList<Rectangle> getEndGameAreaList() {
		return endGameArea;
	}
}
