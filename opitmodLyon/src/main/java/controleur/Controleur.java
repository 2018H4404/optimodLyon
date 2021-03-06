package controleur;

import java.io.File;

import modele.DemandeLivraison;
import modele.Plan;
import services.LecteurDeXML;

public class Controleur {
	public static Plan monPlan;
	public static DemandeLivraison maDemande;
	
	public static void chargerFichierPlan(File f) {
		LecteurDeXML.getInstance().lecturePlanXML(f);
	}
	
	public static void chargerFichierDemandeLivraison(File f) {
		LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(f);
	}
}
