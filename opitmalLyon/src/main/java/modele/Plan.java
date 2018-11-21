package modele;

import java.util.Collection;
import java.util.HashMap;
import java.util.IdentityHashMap;

public class Plan {

	protected HashMap<Long,Intersection> intersections;
	protected IdentityHashMap<Long,Troncon> troncons;
	protected double maxLong;
	protected double minLong;
	protected double maxLat;
	protected double minLat;
	
	
	public Plan(HashMap<Long, Intersection> intersections, IdentityHashMap<Long, Troncon> troncons, double maxLong, double minLong,
			double maxLat, double minLat) {
		super();
		this.intersections = intersections;
		this.troncons = troncons;
		this.maxLong = maxLong;
		this.minLong = minLong;
		this.maxLat = maxLat;
		this.minLat = minLat;
	}

	public void clear() {
		this.intersections.clear();
		this.troncons.clear();
	}
	
	public Intersection getIntersection(long id) {
		return this.intersections.get(id);
	}
	
	public Troncon getTroncon(long origine) {
		return this.troncons.get(origine);
	}

	public HashMap<Long, Intersection> getIntersections() {
		return intersections;
	}
	
	public Collection<Intersection> getAllIntersections() {
		return intersections.values();
	}
	
	public Collection<Troncon> getAllTroncons() {
		return troncons.values();
	}

	public void setIntersections(HashMap<Long, Intersection> intersections) {
		this.intersections = intersections;
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
