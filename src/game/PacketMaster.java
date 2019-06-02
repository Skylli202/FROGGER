package game;

public class PacketMaster extends Packet {
	private static final long serialVersionUID = 1L;
	
	private BiblioEntity biblio;

	public PacketMaster(String name, int score, BiblioEntity biblio) {
		super(name, score);
		this.biblio = biblio;
	}

	public BiblioEntity getBiblio() {
		return biblio;
	}

	public void setBiblio(BiblioEntity biblio) {
		this.biblio = biblio;
	}

	@Override
	public boolean isMasterPacket() {
		return true;
	}
	
	@Override
	public String toString() {
		return name + " - " + score + " // " + biblio.toString();
	}

	public static boolean isMaster(Object objectReceived) {
		return false;
	}
}
