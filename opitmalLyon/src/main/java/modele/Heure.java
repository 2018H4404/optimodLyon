package modele;

public class Heure {
	protected int heure;
	protected int minute;
	protected int seconde;
	
	public Heure(int h, int m, int s) {
		heure = h;
		minute = m;
		seconde = s;
	}
	
	public int getHeure() {
		return heure;
	}
	
	public int getMinute() {
		return minute;
	}
		
	public int getSeconde() {
		return seconde;
	}

	@Override
	public String toString() {
		return "Heure [heure=" + heure + ", minute=" + minute + ", seconde=" + seconde + "]";
	}
	
}
