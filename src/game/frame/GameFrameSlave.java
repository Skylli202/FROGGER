package game.frame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import game.KeyAdapt;
import game.Player;
import network.Client;

public class GameFrameSlave extends GameFrame {

	private static final long serialVersionUID = 4L;

	public GameFrameSlave(String username, String ipaddress, String port) {
		this.username = username;
		
		try {
			socket = new Socket(ipaddress, Integer.parseInt(port));
		} catch(Exception e) {
			e.printStackTrace();
		}
		client = new Client(socket, this);
		client.start();

		setFocusable(true);
		initHitBox();
//		initFromNetwork();
		getBiblioEntity().initFloatable();
		getBiblioEntity().initCar();
		getBiblioEntity().initSnake();
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
		drawTimerBar(g2d);
		drawPlayer(g2d);
		
		// Program cannot work properly due to orignal design where GameFrameMaster was GameFrameSolo and one and only one class.
		// Player and entity in general use some GameFrameMaster Static properties that should NOT be static but are static...
		// So even when Player or Entioty instance are created by GameFrameSlave instance there are referencing to GameFrameMaster ...
		
		// Comment or Uncomment this line to display or hide local BiblioEntity
		getBiblioEntity().draw(g2d);
	}
	
	// Draw things
	public void drawBackground(Graphics2D g2d) {
		g2d.drawImage(background.getImage(), 0, 0, null);
	}

	public void writeInfos(Graphics2D g2d) {
		g2d.drawString("Lifes: " + getLife(), 10, 30);
		g2d.drawString("Score: " + score, 200, 30);
		g2d.drawString("Current Level: " + getGameLevel(), 390, 30);
		g2d.drawString("Timer: ", 10, 830);
	}

	public void drawEndGameAreaFilled(Graphics2D g2d) {
		ImageIcon winArea = new ImageIcon("./res/win_00.png");
		for (int i = 0; i < endGameArea.size(); i++) {
			if (player.getArrive()[i])
				g2d.drawImage(winArea.getImage(), (int) endGameArea.get(i).getX() + 12,
						(int) endGameArea.get(i).getY() + 10, null);
		}
	}

	public void drawTimerBar(Graphics2D g2d) {
		g2d.setColor(Color.GREEN);
		g2d.fillRect(70, 810, getSec() * 10, 30);
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

	public void updateBiblioFromNetwork() {
		GameFrameSlave.biblioEntity = client.getTmpBiblio();
	}

	// init Stuff
	private void initTimer() {
		mainTimer = new Timer(10, this); // Timer is 10 base line - pass to 100 to slow down the spam
		mainTimer.start();
	}

	@SuppressWarnings("unused")
	private void initPlayer() {
		player = new Player(270, 760);
		addKeyListener(new KeyAdapt(player));
	}
	
	public void initFromNetwork() {
		GameFrameSlave.biblioEntity = client.getTmpBiblio();
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
