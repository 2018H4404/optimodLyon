package vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class PointNoeud extends Circle{
	
	private long idNoeud;
	
	public PointNoeud(double x, double y, double radius, long id) {
		super(x,y,radius);
		this.setFill(Color.BLUE);
		idNoeud = id;
	}
	
}