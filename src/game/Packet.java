/**
 * 
 */
package game;

import java.io.Serializable;

/**
 * @author Elouan
 *
 */
public abstract class Packet implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String name;
	protected int score;
	
	public Packet(String name, int score) {
		this.name = name;
		this.score = score;
	}

	public String printScore() {
		return name+" : "+score+" points. \n";
	}
	
	public abstract String toString();
	public abstract boolean isMasterPacket();
}
