package modele;

public class Entrepot extends Lieu{
	protected Heure heureDepart;
	
	public Entrepot(long unId, int h, int m, int s) {
		super(unId);
		heureDepart = new Heure(h,m,s);
	}
	
	public String GetHeure() {
		return heureDepart.toString();
	}
	
}
