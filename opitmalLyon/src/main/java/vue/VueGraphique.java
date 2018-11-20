package vue;

import java.util.Collection;
import java.util.Observable;

import javafx.scene.*;
import modele.Noeud;
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
		Collection<Noeud> lesNoeuds = monPlan.getAllNoeuds();
		Collection<Troncon> lesTroncons = monPlan.getAllTroncons();
		System.out.println(lesNoeuds.size());
		System.out.println(lesTroncons.size());
		for(Noeud noeud : lesNoeuds) {
			PointNoeud tempPoint = new PointNoeud(monPlan.transformLongitude(noeud.getLongitude(), largeur),monPlan.transformLatitude(noeud.getLatitude(), hauteur),5,noeud.getId());
			noeudGroup.getChildren().add(tempPoint);
		}
		for(Troncon troncon : lesTroncons) {
			Noeud depart = monPlan.getNoeud(troncon.getOrigine());
			Noeud destination = monPlan.getNoeud(troncon.getDestination());
			LigneTroncon tempLigne = new LigneTroncon(monPlan.transformLongitude(depart.getLongitude(), largeur),monPlan.transformLatitude(depart.getLatitude(), hauteur),
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
