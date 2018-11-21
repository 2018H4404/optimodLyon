package vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class IntersectionVue extends Circle{
	
	private long idNoeud;
	
	public IntersectionVue(double x, double y, double radius, long id) {
		super(x,y,radius);
		this.setFill(Color.BLUE);
		idNoeud = id;
	}
	
}
