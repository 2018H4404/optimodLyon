package vue;

import java.util.Collection;
import java.util.Observable;

import controleur.Controleur;
import javafx.scene.*;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Slider;
import modele.DemandeLivraison;
import modele.Entrepot;
import modele.IntersectionNormal;
import modele.Plan;
import modele.PointLivraison;
import modele.Troncon;



@SuppressWarnings("restriction")
public class VueGraphique extends Vue{
	
	private Group tronconGroup;
	private Group noeudGroup;
	private Group entrepotGroup;
	private Group livraisonGroup;
	private Group tourneeGroup;
	private static double hauteur = 1200;
	private static double largeur = 1200;
	
	public VueGraphique() {
		ScrollPane paneGraphique = new ScrollPane();
		BorderPane container = new BorderPane();
		Slider zoomSlider = new Slider();
		zoomSlider.setMin(1);
		zoomSlider.setMax(3);
		zoomSlider.setValue(1);
		zoomSlider.setBlockIncrement(0.01);
		
		paneGraphique.setPrefViewportHeight(800);
		paneGraphique.setPrefViewportWidth(800);
		Controleur.monPlan.addObserver(this);
		Controleur.maDemande.addObserver(this);
		noeudGroup = new Group();
		tronconGroup = new Group();
		entrepotGroup = new Group();
		livraisonGroup = new Group();
		tourneeGroup = new Group();
		Group rootGroup = new Group();
		
		//Ajouter les actions suivant les Événements
		
		
		rootGroup.getChildren().add(noeudGroup);
		rootGroup.getChildren().add(tronconGroup);
		rootGroup.getChildren().add(entrepotGroup);
		rootGroup.getChildren().add(livraisonGroup);
		rootGroup.getChildren().add(tourneeGroup);
		rootGroup.scaleXProperty().bind(zoomSlider.valueProperty());
		rootGroup.scaleYProperty().bind(zoomSlider.valueProperty());
		
		paneGraphique.setContent(rootGroup);
		container.setCenter(paneGraphique);
		container.setBottom(zoomSlider);
		
		this.getChildren().add(container);
	}
	
	public void dessinerPlan(Plan monPlan) {
		clearVue();
		Collection<IntersectionNormal> lesIntersections = monPlan.getAllIntersectionNormals();
		Collection<Troncon> lesTroncons = monPlan.getAllTroncons();
		System.out.println(lesIntersections.size());
		System.out.println(lesTroncons.size());
		for(IntersectionNormal intersection : lesIntersections) {
			IntersectionNormalVue tempPoint = new IntersectionNormalVue(monPlan.transformLongitude(intersection.getLongitude(), largeur),monPlan.transformLatitude(intersection.getLatitude(), hauteur),2,intersection.getId());
			noeudGroup.getChildren().add(tempPoint);
		}
		for(Troncon troncon : lesTroncons) {
			IntersectionNormal depart = troncon.getOrigine();
			IntersectionNormal destination = troncon.getDestination();
			TronconVue tempLigne = new TronconVue(monPlan.transformLongitude(depart.getLongitude(), largeur),monPlan.transformLatitude(depart.getLatitude(), hauteur),
													monPlan.transformLongitude(destination.getLongitude(), largeur),monPlan.transformLatitude(destination.getLatitude(), hauteur),troncon.getNomRue());
			tronconGroup.getChildren().add(tempLigne);
		}
		System.out.println("Done Plan");
	}
	
	public void dessinerDemandeLivraison(DemandeLivraison maDemande) {
		clearEntrepotLivraison();
		Collection<Entrepot> lesEntrepots = maDemande.getAllEntrepots();
		Collection<PointLivraison> lesPointLivraisons = maDemande.getAllPointLivraisons();
		System.out.println(lesEntrepots.size());
		System.out.println(lesPointLivraisons.size());
		for(Entrepot entrepot : lesEntrepots) {
			EntrepotVue tempEntrepot = new EntrepotVue(Controleur.monPlan.transformLongitude(entrepot.getLongitude(), largeur),Controleur.monPlan.transformLatitude(entrepot.getLatitude(), hauteur),5,entrepot.getId());
			entrepotGroup.getChildren().add(tempEntrepot);
		}
		for(PointLivraison livraison : lesPointLivraisons) {
			PointLivraisonVue tempPointLivraison = new PointLivraisonVue(Controleur.monPlan.transformLongitude(livraison.getLongitude(), largeur),Controleur.monPlan.transformLatitude(livraison.getLatitude(), hauteur),5,livraison.getId());
			livraisonGroup.getChildren().add(tempPointLivraison);
		}
		System.out.println("Done Demande");
	}
	
	public void clearVue() {
		tronconGroup.getChildren().clear();
		noeudGroup.getChildren().clear();
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
		tourneeGroup.getChildren().clear();
	}
	
	public void clearEntrepotLivraison() {
		entrepotGroup.getChildren().clear();
		livraisonGroup.getChildren().clear();
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub\
		String sujet = (String)arg;
		switch(sujet) {
			case "Plan":
				dessinerPlan((Plan)o);
				break;
			case "DemandeLivraison":
				dessinerDemandeLivraison((DemandeLivraison)o);
				break;	
				
		}
		
	}

	
}
