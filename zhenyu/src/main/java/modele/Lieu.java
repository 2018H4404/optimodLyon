package modele;

public class Lieu {
	protected long idAdresse;
	
	public Lieu(long unId) {
		this.idAdresse = unId;
	}
	
	public long GetIdAdresse() {
		return idAdresse;
	}
}
