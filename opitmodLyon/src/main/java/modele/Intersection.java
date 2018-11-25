package modele;

public abstract class Intersection {
	protected long id;
	protected double latitude;
	protected double longitude;
	
	public Intersection(long id) {
		this.id = id;
	}
	
	public Intersection(long id, double latitude, double longitude) {
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
	}
	
	@Override
	public abstract String toString();

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
