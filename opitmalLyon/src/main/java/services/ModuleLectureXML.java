package services;

import java.io.File;
import java.util.ArrayList;

import org.w3c.dom.Document;   
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;

import modele.Entrepot;
import modele.Lieu;
import modele.Noeud;
import modele.Plan;
import modele.PointLivraison;
import modele.Troncon;   

public class ModuleLectureXML {
	
	public ModuleLectureXML() {
		
	}
	
	public ArrayList<Lieu> LectureLivraisonEntrepotXML(String fileName){
		ArrayList<Lieu> tempLieus = new ArrayList<Lieu>();  
		try {   
			File f = new File(fileName);   
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
							Lieu tempObject = null;
							if(elemChild.getTagName().equals("entrepot")) {
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("adresse"));
								String heureDepart = elemChild.getAttribute("heureDepart");
								String[] times= heureDepart.split(":");
								int heure = Integer.parseInt(times[0]);
								int minute = Integer.parseInt(times[1]);
								int seconde = Integer.parseInt(times[2]);
								tempObject = new Entrepot(tempId,heure,minute,seconde);
								tempLieus.add(tempObject);
							}else if(elemChild.getTagName().equals("livraison")) {
								long tempId = Long.parseUnsignedLong(elemChild.getAttribute("adresse"));
								int duree = Integer.parseInt(elemChild.getAttribute("duree"));
								tempObject = new PointLivraison(tempId,duree);
								tempLieus.add(tempObject);
							}else {
								
							}
						}
					}
				}
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }    
		return tempLieus;
	}

	public Plan LecturePlanXML(String fileName) {
		ArrayList<Noeud> tempNoeuds = new ArrayList<Noeud>();  
		ArrayList<Troncon> tempTroncons = new ArrayList<Troncon>();
		try {   
			File f = new File(fileName);   
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();   
			DocumentBuilder builder = factory.newDocumentBuilder();   
			Document doc = builder.parse(f);   
			doc.getDocumentElement().normalize();
			System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
			NodeList nl = doc.getElementsByTagName("reseau");   
			for (int temp = 0; temp < nl.getLength(); temp++) {   
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
								tempNoeud = new Noeud(tempId,tempLatitude,tempLongitude);
								tempNoeuds.add(tempNoeud);
							}else if(elemChild.getTagName().equals("troncon")) {
								Troncon tempTroncon = null;
								long tempDest = Long.parseUnsignedLong(elemChild.getAttribute("destination"));
								double longueur = Double.parseDouble(elemChild.getAttribute("longueur"));
								String nomRue = elemChild.getAttribute("nomRue");
								long tempOrigine = Long.parseUnsignedLong(elemChild.getAttribute("origine"));
								tempTroncon = new Troncon(tempDest,longueur,nomRue,tempOrigine);
								tempTroncons.add(tempTroncon);
							}else {
								
							}
						}
					}
				}
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }
		Plan tempPlan = new Plan(tempNoeuds,tempTroncons);
		return tempPlan;
	}
	
}