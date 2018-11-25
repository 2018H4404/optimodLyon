package vue;

import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.geometry.Pos;

import modele.DemandeLivraison;
import modele.Plan;
import controleur.Controleur;


@SuppressWarnings("restriction")
public class ApplicationDemo extends Application{
	private VueGraphique graph;
	private VueTextuelle texte;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
        Controleur.monPlan = new Plan();
        Controleur.maDemande = new DemandeLivraison();
        graph = new VueGraphique();
        texte = new VueTextuelle();
        
		BorderPane pane = new BorderPane();	
        
		//Ajout de la barre de menu
        AjouterBarreNavigateur(pane,primaryStage);
        
        //Placement de differents vues
        pane.setLeft(texte);
        pane.setCenter(graph);
        BorderPane.setAlignment(graph, Pos.TOP_CENTER);
        BorderPane.setMargin(graph, new Insets(30,0,0,0));
        
        //Creation de la scene
        Scene scene = new Scene(pane, 1150, 900);
        scene.setFill(Color.BLACK);

        primaryStage.setScene(scene);
        primaryStage.setTitle("PLD Agile");
        primaryStage.show();

	}
	
	public void AjouterBarreNavigateur(BorderPane pane, Stage primaryStage) {
		
		MenuBar menuBar = new MenuBar();
		
		//Ajout de l'onglet Fichiers
		Menu menuFile = new Menu("Fichiers");
        MenuItem itemChargerPlan = new MenuItem("Charger Plan");
        itemChargerPlan.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   Controleur.chargerFichierPlan(file);
               }
               
	         }
	      }); 
        MenuItem itemChargerDemandeLivraison = new MenuItem("Charger DemandeLivraison");
        itemChargerDemandeLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
               FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
               fileChooser.getExtensionFilters().add(extFilter);
               File file = fileChooser.showOpenDialog(primaryStage);
               if(file != null) {
            	   Controleur.chargerFichierDemandeLivraison(file);
               }
	         }
	      });
 
        menuFile.getItems().addAll(itemChargerPlan,itemChargerDemandeLivraison);
 
      //Ajout de l'onglet Opération
        Menu menuOperation = new Menu("Opération");
        
      //Ajout de l'onglet View(Composant prevu pour apres)
        Menu menuView = new Menu("View");
        
        menuBar.getMenus().addAll(menuFile, menuOperation, menuView);
		////////////////////////////////////
        //Code abandonnee
		/*Group buttonGroup = new Group();
		Button btnChargerPlan = new Button("Charger Plan");
		//Position du bouton
		btnChargerPlan.setLayoutX(0);
		btnChargerPlan.setLayoutY(0);
		btnChargerPlan.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(primaryStage);
                LecteurDeXML.getInstance().lecturePlanXML(file);
	         }
	      });
		Button btnChargerDemandeLivraison = new Button("Charger DemandeLivraison");
		//Position du bouton
		btnChargerDemandeLivraison.setLayoutX(0);
		btnChargerDemandeLivraison.setLayoutY(0);
		btnChargerDemandeLivraison.setOnAction(new EventHandler<ActionEvent>() {
			 
	         @Override
	         public void handle(ActionEvent event) {
	        	
	        	FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("XML Fichiers", "*.xml");
                fileChooser.getExtensionFilters().add(extFilter);
                File file = fileChooser.showOpenDialog(primaryStage);
                LecteurDeXML.getInstance().lectureLivraisonEntrepotXML(file);
	         }
	      });
		buttonGroup.getChildren().add(btnChargerPlan);
		buttonGroup.getChildren().add(btnChargerDemandeLivraison);*/
		pane.setTop(menuBar);
	}
	
	public static void main(String[] args) {
		
        launch(args);
    }

}
