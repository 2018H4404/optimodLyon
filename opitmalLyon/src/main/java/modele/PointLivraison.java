package modele;

public class PointLivraison extends Lieu{
	public int duree;
	
	public PointLivraison(long unId, int uneDuree) {
		super(unId);
		duree = uneDuree;
	}
	
	public int getDuree() {
		return duree;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String retour = "{ idAdresse : " + this.getIdAdresse() + ". duree : " + this.duree + ". }";
		return retour;
	}
	
	
	
}
