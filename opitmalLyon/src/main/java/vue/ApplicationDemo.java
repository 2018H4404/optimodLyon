package vue;

import java.util.Collection;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import modele.Lieu;
import modele.Noeud;
import modele.Plan;
import modele.Troncon;
import services.LecteurDeXML;
import services.LieuFactory;


@SuppressWarnings("restriction")
public class ApplicationDemo extends Application{
	private VueGraphique graph;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		LieuFactory.initLieuFactory();
        LecteurDeXML.getInstance().lectureLivraisonEntrepotXML("fichiersXML2018/dl-petit-3.xml");
        Collection<Lieu> c = LieuFactory.getAllLieus();
        for(Lieu l : c) {
        	System.out.println(l.toString());
        }
        Plan p = LecteurDeXML.getInstance().lecturePlanXML("fichiersXML2018/petitPlan.xml");
        Collection<Noeud> n = p.getNoeuds().values();
        Collection<Troncon> t = p.getTroncons().values();
        for(Noeud tempN : n) {
        	System.out.println(tempN.toString());
        }
        for(Troncon tempT : t) {
        	System.out.println(tempT.toString());
        }
        graph = new VueGraphique(p);
        Button btnExit = new Button("Exit");
		BorderPane pane = new BorderPane();
        pane.setCenter(graph);

        Scene scene = new Scene(pane, 1000, 1000);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();

	}
	
	public static void main(String[] args) {
		
        launch(args);
    }

}
