package modele;

public class Noeud {

	protected long id;
	protected double latitude;
	protected double longitude;
	
	public Noeud(long id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public String toString() {
		return "Noeud [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	
}
