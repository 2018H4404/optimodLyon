package vue;

import javafx.scene.Parent;
import modele.Plan;

import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("restriction")
public abstract class Vue extends Parent implements Observer{
	
	protected Plan monPlan;
	
	public Vue(Plan p) {
		monPlan = p;
	}

	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
}
