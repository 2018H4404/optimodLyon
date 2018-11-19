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
	
	public int GetHeure() {
		return heure;
	}
	
	public int GetMinute() {
		return minute;
	}
		
	public int GetSeconde() {
		return seconde;
	}

	@Override
	public String toString() {
		return "Heure [heure=" + heure + ", minute=" + minute + ", seconde=" + seconde + "]";
	}
	
}
