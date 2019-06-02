package game.frame;

import java.awt.Rectangle;
import java.net.Socket;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import game.BiblioEntity;
import game.Player;
import network.Client;

public class GameFrame extends JPanel {
	private static final long serialVersionUID = 1L;
	
	protected Client client;
	protected Socket socket;
	protected static Timer mainTimer;
	protected static Player player;
	protected ImageIcon background = new ImageIcon("./res/backgroundResized2.png");

	protected static int life = 3;
	protected static int score = 0;
	protected String username;
	protected static int nbArrive = 0;

	protected static int sec = 30;
	static int timerCpt = 0;
	protected static int intervalWoodCroco = 0;

	protected static int level = 1;
	protected static int gameLevel = 1;

	protected static ArrayList<Rectangle> endGameArea = new ArrayList<Rectangle>();
	protected static ArrayList<Rectangle> hitBox = new ArrayList<Rectangle>();

	protected static BiblioEntity biblioEntity = new BiblioEntity();

	public static int getLife() {
		return life;
	}

	public static void setLife(int life) {
		GameFrameSolo.life = life;
	}

	public static int getSec() {
		return sec;
	}

	public static void setSec(int sec) {
		GameFrameSolo.sec = sec;
	}
	
	public static int getScore() {
		return GameFrameSolo.score;
	}
	
	public static void setScore(int score) {
		GameFrameSolo.score = score;
	}

	public static int getLevel() {
		return level;
	}

	public static void setLevel(int level) {
		GameFrameSolo.level = level;
	}

	public static BiblioEntity getBiblioEntity() {
		return biblioEntity;
	}

	public void setBiblioEntity(BiblioEntity biblioEntity) {
		GameFrameSolo.biblioEntity = biblioEntity;
	}

	public static int getNbArrive() {
		return nbArrive;
	}

	public static void setNbArrive(int nbArrive) {
		GameFrameSolo.nbArrive = nbArrive;
	}

	public static int getGameLevel() {
		return gameLevel;
	}

	public static void setGameLevel(int gameLevel) {
		GameFrameSolo.gameLevel = gameLevel;
	}

	public static int getIntervalWoodCroco() {
		return intervalWoodCroco;
	}

	public static void setIntervalWoodCroco(int intervalWoodCroco) {
		GameFrameSolo.intervalWoodCroco = intervalWoodCroco;
	}
	
	public String getUsername() {
		return username;
	}
	
	public BiblioEntity getBiblio() {
		return getBiblioEntity();
	}
}
