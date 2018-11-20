package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;

public class Plan {

	protected HashMap<Long,Noeud> noeuds;
	protected IdentityHashMap<Long,Troncon> troncons;
	protected double maxLong;
	protected double minLong;
	protected double maxLat;
	protected double minLat;
	
	
	public Plan(HashMap<Long, Noeud> noeuds, IdentityHashMap<Long, Troncon> troncons, double maxLong, double minLong,
			double maxLat, double minLat) {
		super();
		this.noeuds = noeuds;
		this.troncons = troncons;
		this.maxLong = maxLong;
		this.minLong = minLong;
		this.maxLat = maxLat;
		this.minLat = minLat;
	}

	public void clear() {
		this.noeuds.clear();
		this.troncons.clear();
	}
	
	public Noeud getNoeud(long id) {
		return this.noeuds.get(id);
	}
	
	public Troncon getTroncon(long origine) {
		return this.troncons.get(origine);
	}

	public HashMap<Long, Noeud> getNoeuds() {
		return noeuds;
	}
	
	public Collection<Noeud> getAllNoeuds() {
		return noeuds.values();
	}
	
	public Collection<Troncon> getAllTroncons() {
		return troncons.values();
	}

	public void setNoeuds(HashMap<Long, Noeud> noeuds) {
		this.noeuds = noeuds;
	}
	
	public IdentityHashMap<Long, Troncon> getTroncons() {
		return troncons;
	}

	public void setTroncons(IdentityHashMap<Long, Troncon> troncons) {
		this.troncons = troncons;
	}

	public double transformLongitude(double longitude, double largeur) {
		return (longitude - minLong) * largeur / (maxLong - minLong);
	}
	
	public double transformLatitude(double latitude, double hauteur) {
		return (maxLat - latitude) * hauteur / (maxLat - minLat);
	}
	
	
	
	
	
}
