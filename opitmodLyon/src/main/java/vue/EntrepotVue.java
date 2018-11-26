package vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class EntrepotVue extends Circle{
	
	private double idEntrepot;
	
	public EntrepotVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.TOMATO);
		this.idEntrepot = unId;
	}
	
}
