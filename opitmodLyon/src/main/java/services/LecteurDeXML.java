package services;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import controleur.Controleur;
import modele.Entrepot;
import modele.IntersectionNormal;
import modele.PointLivraison;
import modele.Troncon;

public class LecteurDeXML {
	
	private static LecteurDeXML instance = null;
	
	private LecteurDeXML() {}
	
	public static LecteurDeXML getInstance() {
		if(instance == null) instance = new LecteurDeXML();
		return instance;
	}
	
	public void lectureLivraisonEntrepotXML(File f){ 
		HashMap<Long,PointLivraison> tempLivraisons = new HashMap<Long,PointLivraison>();
		HashMap<Long,Entrepot> tempEntrepots = new HashMap<Long,Entrepot>();
		try {   
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(f);   
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nl = doc.getElementsByTagName("demandeDeLivraisons");   
			for (int temp = 0; temp < nl.getLength(); temp++) {   
				Node nNode = nl.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList children = nNode.getChildNodes(); 
					int nbChildren = children.getLength();
					for(int i = 0; i < nbChildren; i++) {
						Node child = children.item(i);
						if (child.getNodeType() == Node.ELEMENT_NODE) {
							Element elemChild = (Element) child;
							if(elemChild.getTagName().equals("entrepot")) {
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("adresse"));
								String heureDepart = elemChild.getAttribute("heureDepart");
								String[] times= heureDepart.split(":");
								int heure = Integer.parseInt(times[0]);
								int minute = Integer.parseInt(times[1]);
								int seconde = Integer.parseInt(times[2]);
								IntersectionNormal tempInter = Controleur.monPlan.getIntersectionNormal(tempId);
								Entrepot tempObject = new Entrepot(tempId,tempInter.getLatitude(),tempInter.getLongitude(),heure,minute,seconde);
								tempEntrepots.put(tempId,tempObject);
							}else if(elemChild.getTagName().equals("livraison")) {
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("adresse"));
								int duree = Integer.parseInt(elemChild.getAttribute("duree"));
								IntersectionNormal tempInter = Controleur.monPlan.getIntersectionNormal(tempId);
								PointLivraison tempObject = new PointLivraison(tempId,tempInter.getLatitude(),tempInter.getLongitude(),duree);
								tempLivraisons.put(tempId,tempObject);
							}else {
								
							}
						}
					}
				}
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    } 
		Controleur.maDemande.intialiserDemandeLivraison(tempLivraisons, tempEntrepots);
	}

	public void lecturePlanXML(File f) {
		HashMap<Long,IntersectionNormal> tempIntersections = new HashMap<Long,IntersectionNormal>();  
		HashMap<Long,ArrayList<Troncon>> tempTroncons = new HashMap<Long,ArrayList<Troncon>>();
		double maxLong = 0;
		double maxLat = 0;
		double minLong = 0;
		double minLat = 0;
		try {     
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(f);   
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nl = doc.getElementsByTagName("reseau");   
			for (int temp = 0; temp < nl.getLength(); temp++) {   
				boolean first = true;
				Node nNode = nl.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					NodeList children = nNode.getChildNodes();
					int nbChildren = children.getLength();
					for(int i = 0; i < nbChildren; i++) {
						Node child = children.item(i);
						if (child.getNodeType() == Node.ELEMENT_NODE) {
							Element elemChild = (Element) child;
							if(elemChild.getTagName().equals("noeud")) {
								IntersectionNormal tempIntersection = null;
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("id"));
								double tempLatitude = Double.parseDouble(elemChild.getAttribute("latitude"));
								double tempLongitude = Double.parseDouble(elemChild.getAttribute("longitude"));
								if(first) {
									maxLong = tempLongitude;
									minLong = tempLongitude;
									maxLat = tempLatitude;
									minLat = tempLatitude;
									first = false;
								}else {
									if(tempLongitude > maxLong) {
										maxLong = tempLongitude;
									}else if(tempLongitude < minLong) {
										minLong = tempLongitude;
									}
									if(tempLatitude > maxLat) {
										maxLat = tempLatitude;
									}else if(tempLatitude < minLat) {
										minLat = tempLatitude;
									}
								}
								tempIntersection = new IntersectionNormal(tempId,tempLatitude,tempLongitude);
								tempIntersections.put(tempId, tempIntersection);
							}else if(elemChild.getTagName().equals("troncon")) {
								Troncon tempTroncon = null; 
								long idDest = Long.parseUnsignedLong(elemChild.getAttribute("destination"));
								double longueur = Double.parseDouble(elemChild.getAttribute("longueur"));
								String nomRue = elemChild.getAttribute("nomRue");
								long idOrigine = Long.parseUnsignedLong(elemChild.getAttribute("origine"));
								IntersectionNormal dest = new IntersectionNormal(idDest);
								IntersectionNormal origine = new IntersectionNormal(idOrigine);
								tempTroncon = new Troncon(dest,origine,longueur,nomRue);
								if(tempTroncons.containsKey(idOrigine)) {
									tempTroncons.get(idOrigine).add(tempTroncon);
								}else {
									ArrayList<Troncon> tempListe = new ArrayList<Troncon>();
									tempListe.add(tempTroncon);
									tempTroncons.put(idOrigine, tempListe);
								}
							}else {
								Exception e = new Exception();
								System.out.println("Erreur de l'architecture du fichier xml");
								throw e;
							}
						}
					}
				}
				setLatLongDesTroncons(tempIntersections, tempTroncons);
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }
		Controleur.monPlan.initialiserPlan(tempIntersections, tempTroncons, maxLong, minLong, maxLat, minLat);
	}
	
	
	private void setLatLongDesTroncons(HashMap<Long,IntersectionNormal> tempIntersections, HashMap<Long,ArrayList<Troncon>> tempTroncons) {
		for(HashMap.Entry<Long,ArrayList<Troncon>> entry: tempTroncons.entrySet())
        {
			for(Troncon c : entry.getValue()) {
				IntersectionNormal origine = tempIntersections.get(c.getOrigine().getId());
				IntersectionNormal dest = tempIntersections.get(c.getDestination().getId());
				c.getOrigine().setLatitude(origine.getLatitude());
				c.getOrigine().setLongitude(origine.getLongitude());
				c.getDestination().setLatitude(dest.getLatitude());
				c.getDestination().setLongitude(dest.getLongitude());
			}
        }
	}
}



