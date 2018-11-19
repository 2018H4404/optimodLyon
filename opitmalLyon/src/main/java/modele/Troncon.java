package modele;

public class Troncon {
	
	protected long destination;
	protected double longueur;
	protected String nomRue;
	protected long origine;
	
	public Troncon(long destination, double longueur, String nomRue, long origine) {
		this.destination = destination;
		this.longueur = longueur;
		this.nomRue = nomRue;
		this.origine = origine;
	}

	@Override
	public String toString() {
		return "Troncon [destination=" + destination + ", longueur=" + longueur + ", nomRue=" + nomRue + ", origine="
				+ origine + "]";
	}

	public long getDestination() {
		return destination;
	}

	public void setDestination(long destination) {
		this.destination = destination;
	}

	public double getLongueur() {
		return longueur;
	}

	public void setLongueur(double longueur) {
		this.longueur = longueur;
	}

	public String getNomRue() {
		return nomRue;
	}

	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}

	public long getOrigine() {
		return origine;
	}

	public void setOrigine(long origine) {
		this.origine = origine;
	}
	
	
	
	
}
