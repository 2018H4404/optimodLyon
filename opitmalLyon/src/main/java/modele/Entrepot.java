package modele;

public class Entrepot extends Lieu{
	protected Heure heureDepart;
	
	public Entrepot(long unId, int h, int m, int s) {
		super(unId);
		heureDepart = new Heure(h,m,s);
	}
	
	public String getHeure() {
		return heureDepart.toString();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String retour = "{ idAdresse : " + this.getIdAdresse() + ". heureDpart : " + this.getHeure() + ". }";
		return retour;
	}
	
}
