package modele;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.TreeMap;

import services.Paire;

public class TourneeManager {
	
	protected ArrayList <Tournee> listeTournees;
	private static final double RAYON_TERRE = 6378.137;
	
	public void calculTournees(Plan plan){
		listeTournees = Algo(plan);
		
	}
	
	public ArrayList<Tournee> Algo(Plan plan){
		ArrayList<Tournee> res = null;
		return res;
	}
	
	private ArrayList<Troncon> trouverVosins(final long idCourant, Plan monPlan) {
		return monPlan.getTronconsParOrigine(idCourant);
	}
	
	public ArrayList<Intersection> algoAEtoile(Intersection depart, Intersection dest, Plan monPlan){
		ArrayList<Intersection> meilleurChemin = new ArrayList<Intersection>();
		
		HashMap<Intersection,Intersection> parents = new HashMap<Intersection,Intersection>();
		parents.put(depart, depart);
		HashMap<Intersection,Double> distanceEstimeeF = new HashMap<Intersection,Double>();
		distanceEstimeeF.put(depart,heuristique(depart,dest));
		
		ArrayList<Intersection> noir = new ArrayList<Intersection>();
		
		/*PriorityQueue<Paire> gris = new PriorityQueue<Paire>(1,
				new Comparator<Paire>() {  
	                  public int compare(Paire p1, Paire p2) {  
	                	  if (p1.valeurF < p2.valeurF)
	                      {
	                          return -1;
	                      }
	                      if (p1.valeurF  > p2.valeurF)
	                      {
	                          return 1;
	                      }
	                      return 0;
	                    }  
	                  }); */
		Map<Double, Intersection> gris = new TreeMap<Double, Intersection>(
				new Comparator<Double>() {  
	                  public int compare(Double p1, Double p2) {  
	                	  if (p1 < p2)
	                      {
	                          return -1;
	                      }
	                      if (p1 > p2)
	                      {
	                          return 1;
	                      }
	                      return 0;
	                    }  
	                  });
		gris.put(heuristique(depart,dest), depart);
		//gris.offer(new Paire(depart,heuristique(depart,dest)));
		
		ArrayList<Troncon> voisins = new ArrayList<Troncon>();
		
		/*while( !gris.isEmpty() )
		{
			Intersection interCourant = gris.;
			
			if(dest.equals(interCourant)) {
				meilleurChemin.clear();
				while(!depart.equals(interCourant)) { 
					meilleurChemin.add(0, interCourant);
					interCourant = parents.get(interCourant);
				}
				meilleurChemin.add(0, depart);
				return meilleurChemin;
			}
			
			noir.add(interCourant);
			
			voisins = trouverVosins(interCourant.getId(),monPlan);
			
			for(Troncon voisin : voisins) {
				if(noir.contains(voisin.getDestination())) continue;
				Intersection interVoisin = voisin.getDestination();
				double nouvelleDistance = distanceEstimeeF.get(interCourant) + voisin.getLongueur() - heuristique(interCourant,dest) + heuristique(interVoisin,dest);
				if(isGris(gris,voisin.getDestination().getId())) {
					if(distanceEstimeeF.get(interVoisin) > nouvelleDistance) {
						distanceEstimeeF.remove(interVoisin);
						distanceEstimeeF.put(interVoisin, nouvelleDistance);
						parents.remove(interVoisin);
						parents.put(interVoisin, interCourant);
						gris.offer(new Paire(interVoisin,nouvelleDistance));
						
					}
				}else {
					distanceEstimeeF.put(interVoisin, nouvelleDistance);
					parents.put(interVoisin, interCourant);
					gris.offer(new Paire(interVoisin,nouvelleDistance));
				}
			}
			
		}*/
		return null;
	}
	
	public Map.Entry<Double, Intersection> premierElement(Map<Double,Intersection> map){
		Map.Entry<Double, Intersection> retour = null;
		Set<Entry<Double,Intersection>> set = map.entrySet();
		for(Map.Entry<Double, Intersection> element : set) {
			retour = element;
			break;
		}
		return retour;
	}
	
	public double heuristique(Intersection depart, Intersection dest) {
		return getDistance(depart.getLatitude(), depart.getLongitude(), dest.getLatitude(), dest.getLongitude());
	}
	
	public boolean isGris(PriorityQueue<Paire> gris, Long voisin) {
		boolean retour = false;
		for(Paire element : gris) {
			if(element.monInter.getId() == voisin) {//.getId()
				retour = true;
			}
		}
		return retour;
	}
	
	//Calculer la distance entre deux points selon leurs latitude et longitude
	public double getDistance(double latStart,double longStart,double latEnd,double longEnd)
    {
        double radLat1 = rad(latStart);
        double radLat2 = rad(latEnd);
        double a = radLat1 - radLat2;
        double b = rad(longStart) - rad(longEnd);
        
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a/2),2) +
                Math.cos(radLat1)*Math.cos(radLat2)*Math.pow(Math.sin(b/2),2)));
        s = s * RAYON_TERRE * 1000;
        s = Math.round(s * 10000) / 10000;
        return s;
    
    }
	
	private double rad(double d)
    {
        return d * Math.PI / 180.0;
    }

}
