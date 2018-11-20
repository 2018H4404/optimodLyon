package services;

import java.util.Collection;
import java.util.HashMap;

import modele.Entrepot;
import modele.Lieu;
import modele.PointLivraison;

public class LieuFactory {
	
	private static HashMap<Long,Lieu> lieus;
	
	public static void initLieuFactory() {
		lieus = new HashMap<Long,Lieu>();
	}
	
	public static void ajouterEntrepot(long tempId,int heure,int minute,int seconde) {
		Lieu tempObject = new Entrepot(tempId,heure,minute,seconde);
		lieus.put(tempId,tempObject);
	}
	
	public static void ajouterPointLivraison(long tempId,int duree) {
		Lieu tempObject = new PointLivraison(tempId,duree);
		lieus.put(tempId,tempObject);
	}
	
	public static void clear() {
		lieus.clear();
	}
	
	public static Lieu getLieuParId(long id) {
		return lieus.get(id);
	}
	
	public static Collection<Lieu> getAllLieus(){
		return lieus.values();
	}
}
