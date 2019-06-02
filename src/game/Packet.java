/**
 * 
 */
package game;

import java.io.Serializable;

/**
 * @author Elouan
 *
 */
public class Packet implements Serializable {
	private static final long serialVersionUID = 1L;
	
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
	
	public BiblioEntity getBiblio() {
		return this.biblio;
	}
}
