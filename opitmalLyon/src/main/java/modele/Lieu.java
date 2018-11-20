package modele;

public abstract class Lieu {
	protected long idAdresse;
	
	public Lieu(long unId) {
		this.idAdresse = unId;
	}
	
	public long getIdAdresse() {
		return idAdresse;
	}
	
	public abstract String toString();
}
