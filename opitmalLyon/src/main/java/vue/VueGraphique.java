package vue;

import java.util.Collection;
import java.util.Observable;

import javafx.scene.*;
import modele.Intersection;
import modele.Plan;
import modele.Troncon;



@SuppressWarnings("restriction")
public class VueGraphique extends Vue{
	
	private Group tronconGroup;
	private Group noeudGroup;
	private static double hauteur = 800;
	private static double largeur = 800;
	
	public VueGraphique(Plan unPlan) {
		super(unPlan);
		tronconGroup = new Group();
		noeudGroup = new Group();
		dessinerPlan();
		Group rootGroup = new Group();
		rootGroup.getChildren().add(noeudGroup);
		rootGroup.getChildren().add(tronconGroup);
		this.getChildren().add(rootGroup);
	}
	
	public void dessinerPlan() {
		tronconGroup.getChildren().clear();
		noeudGroup.getChildren().clear();
		Collection<Intersection> lesNoeuds = monPlan.getAllIntersections();
		Collection<Troncon> lesTroncons = monPlan.getAllTroncons();
		System.out.println(lesNoeuds.size());
		System.out.println(lesTroncons.size());
		for(Intersection intersection : lesNoeuds) {
			IntersectionVue tempPoint = new IntersectionVue(monPlan.transformLongitude(intersection.getLongitude(), largeur),monPlan.transformLatitude(intersection.getLatitude(), hauteur),5,intersection.getId());
			noeudGroup.getChildren().add(tempPoint);
		}
		for(Troncon troncon : lesTroncons) {
			Intersection depart = monPlan.getIntersection(troncon.getOrigine());
			Intersection destination = monPlan.getIntersection(troncon.getDestination());
			TronconVue tempLigne = new TronconVue(monPlan.transformLongitude(depart.getLongitude(), largeur),monPlan.transformLatitude(depart.getLatitude(), hauteur),
													monPlan.transformLongitude(destination.getLongitude(), largeur),monPlan.transformLatitude(destination.getLatitude(), hauteur),troncon.getNomRue());
			tronconGroup.getChildren().add(tempLigne);
		}
		System.out.println("Done");
	}
	
	
	
	
	

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	
}
