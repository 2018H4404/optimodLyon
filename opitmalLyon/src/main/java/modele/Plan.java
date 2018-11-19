package modele;

import java.util.ArrayList;

public class Plan {

	protected ArrayList<Noeud> noeuds;
	protected ArrayList<Troncon> troncons;
	protected ArrayList<Lieu> lieus;
	
	public Plan(ArrayList<Noeud> noeuds, ArrayList<Troncon> troncons, ArrayList<Lieu> lieus) {
		this.noeuds = noeuds;
		this.troncons = troncons;
		this.lieus = lieus;
	}
	
	public Plan(ArrayList<Noeud> noeuds, ArrayList<Troncon> troncons) {
		this.noeuds = noeuds;
		this.troncons = troncons;
		this.lieus = null;
	}

	public ArrayList<Noeud> getNoeuds() {
		return noeuds;
	}

	public void setNoeuds(ArrayList<Noeud> noeuds) {
		this.noeuds = noeuds;
	}

	public ArrayList<Troncon> getTroncons() {
		return troncons;
	}

	public void setTroncons(ArrayList<Troncon> troncons) {
		this.troncons = troncons;
	}

	public ArrayList<Lieu> getLieus() {
		return lieus;
	}

	public void setLieus(ArrayList<Lieu> lieus) {
		this.lieus = lieus;
	}
	
	
	
	
	
}
