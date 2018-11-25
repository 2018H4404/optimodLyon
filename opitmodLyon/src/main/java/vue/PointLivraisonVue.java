package vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

@SuppressWarnings("restriction")
public class PointLivraisonVue extends Circle{
	
	private double idPointLivraison;
	
	public PointLivraisonVue(double x, double y, double radius, long unId) {
		super(x,y,radius);
		this.setFill(Color.DARKKHAKI);
		this.idPointLivraison = unId;
	}
	
}
