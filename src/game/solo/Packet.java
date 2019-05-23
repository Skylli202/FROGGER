/**
 * 
 */
package game.solo;

import java.io.Serializable;

/**
 * @author Elouan
 *
 */
public class Packet implements Serializable {
	private String name;
	private String score;
	private BiblioEntity biblio;
	
	public Packet(String name, String score, BiblioEntity biblio) {
		this.name = name;
		this.score = score;
		this.biblio = biblio;
	}
	
	public String toString() {
		return name + " - " + score + " // " + biblio.toString();
	}
	
	public String printScore() {
		return name+" : "+score+" points. \n";
	}
}
