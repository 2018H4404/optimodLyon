package modele;

import java.util.ArrayList;


public class Tournee {
	
	protected ArrayList <IntersectionNormal> listeIntersections;
	protected ArrayList <Troncon> listeTroncons;
	
	public ArrayList<IntersectionNormal> getListeIntersections() {
		return listeIntersections;
	}
	
	public ArrayList<Troncon> getListeTroncons(){
		return listeTroncons;
	}
}
