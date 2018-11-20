package vue;

import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

@SuppressWarnings("restriction")
public class LigneTroncon extends Line{
	
	String rueName;
	
	public LigneTroncon(double startX, double startY, double endX, double endY, String name) {
		super(startX,startY,endX,endY);
		this.rueName = name;
		this.setFill(Color.BLACK);
	}
}
