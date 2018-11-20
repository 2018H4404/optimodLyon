package services;

import java.io.File;
import java.util.HashMap;
import java.util.IdentityHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import modele.Noeud;
import modele.Plan;
import modele.Troncon;

public class LecteurDeXML {
	
	private static LecteurDeXML instance = null;
	
	private LecteurDeXML() {}
	
	public static LecteurDeXML getInstance() {
		if(instance == null) instance = new LecteurDeXML();
		return instance;
	}
	
	public void lectureLivraisonEntrepotXML(String fileName){ 
		try {   
			File f = new File(fileName);   
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(f);   
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
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
								LieuFactory.ajouterEntrepot(tempId, heure, minute, seconde);
							}else if(elemChild.getTagName().equals("livraison")) {
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("adresse"));
								int duree = Integer.parseInt(elemChild.getAttribute("duree"));
								LieuFactory.ajouterPointLivraison(tempId, duree);
							}else {
								
							}
						}
					}
				}
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }    
	}

	public Plan lecturePlanXML(String fileName) {
		HashMap<Long,Noeud> tempNoeuds = new HashMap<Long,Noeud>();  
		IdentityHashMap<Long,Troncon> tempTroncons = new IdentityHashMap<Long,Troncon>();
		double maxLong = 0;
		double maxLat = 0;
		double minLong = 0;
		double minLat = 0;
		try {   
			File f = new File(fileName);   
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(f);   
			doc.getDocumentElement().normalize();
//			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
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
								Noeud tempNoeud = null;
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
								tempNoeud = new Noeud(tempId,tempLatitude,tempLongitude);
								tempNoeuds.put(tempId, tempNoeud);
							}else if(elemChild.getTagName().equals("troncon")) {
								Troncon tempTroncon = null;
								long tempDest = Long.parseUnsignedLong(elemChild.getAttribute("destination"));
								double longueur = Double.parseDouble(elemChild.getAttribute("longueur"));
								String nomRue = elemChild.getAttribute("nomRue");
								long tempOrigine = Long.parseUnsignedLong(elemChild.getAttribute("origine"));
								tempTroncon = new Troncon(tempDest,longueur,nomRue,tempOrigine);
								tempTroncons.put(tempOrigine, tempTroncon);
							}else {
								Exception e = new Exception();
								System.out.println("Erreur de l'architecture du fichier xml");
								throw e;
							}
						}
					}
				}
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }
		Plan tempPlan = new Plan(tempNoeuds,tempTroncons,maxLong,minLong,maxLat,minLat);
		return tempPlan;
	}
}