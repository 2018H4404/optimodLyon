package vue;

import java.util.Collection;

import modele.Lieu;
import modele.Intersection;
import modele.Plan;
import modele.Troncon;
import services.LecteurDeXML;
import services.LieuFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	LieuFactory.initLieuFactory();
        LecteurDeXML.getInstance().lectureLivraisonEntrepotXML("fichiersXML2018/dl-petit-3.xml");
        Collection<Lieu> c = LieuFactory.getAllLieus();
        for(Lieu l : c) {
        	System.out.println(l.toString());
        }
        Plan p = LecteurDeXML.getInstance().lecturePlanXML("fichiersXML2018/petitPlan.xml");
        Collection<Intersection> n = p.getIntersections().values();
        Collection<Troncon> t = p.getTroncons().values();
        for(Intersection tempN : n) {
        	System.out.println(tempN.toString());
        }
        for(Troncon tempT : t) {
        	System.out.println(tempT.toString());
        }
    }
}
