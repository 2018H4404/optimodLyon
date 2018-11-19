package services;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.Document;   
import org.w3c.dom.NodeList; 
import org.w3c.dom.Node; 
import org.w3c.dom.Element;
import javax.xml.parsers.DocumentBuilder;   
import javax.xml.parsers.DocumentBuilderFactory;   

import modele.Lieu;   

public class ModuleLectureXML {
	
	public ModuleLectureXML() {
		
	}
	
	public ArrayList<Lieu> LectureLivraisonEntrepot(String fileName){
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
				
				System.out.println("\nCurrent Element :" + nNode.getNodeName());
				NodeList children = nNode.getChildNodes();
				for(int i = 0; i < children.getLength(); i++) {
					Node child = children.item(i);
					System.out.println(child.toString());
				}
				/*if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;
					if(eElement.getTagName().equals("entrepot")) {
						
					}
				}*/
		    }   
		    } catch (Exception e) {   
			    e.printStackTrace();   
		    }    
		return null;
	}
}
