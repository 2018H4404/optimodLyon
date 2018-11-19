package vue;

import java.util.ArrayList;

import modele.Lieu;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import services.ModuleLectureXML;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        ModuleLectureXML module = new ModuleLectureXML();
        ArrayList<Lieu> l = module.LectureLivraisonEntrepotXML("E:\\PLD AGILE\\TP\\opitmalLyon\\fichiersXML2018\\dl-petit-3.xml");
        for(int i = 0; i < l.size();i++) {
        	System.out.println(l.get(i).toString());
        }
        Plan p = module.LecturePlanXML("E:\\PLD AGILE\\TP\\opitmalLyon\\fichiersXML2018\\petitPlan.xml");
        ArrayList<Noeud> n = p.getNoeuds();
        ArrayList<Troncon> t = p.getTroncons();
        for(int i = 0; i < n.size();i++) {
        	System.out.println(n.get(i).toString());
        }
        for(int i = 0; i < t.size();i++) {
        	System.out.println(t.get(i).toString());
        }
    }
}
