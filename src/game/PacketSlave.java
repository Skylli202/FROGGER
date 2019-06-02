package game;

public class PacketSlave extends Packet {
	private static final long serialVersionUID = 1L;

	public PacketSlave(String name, int score) {
		super(name, score);
	}

	@Override
	public boolean isMasterPacket() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public String toString() {
		return name + " - " + score;
	}
}
