package vue;

import java.util.Observable;

import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.geometry.Orientation;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

@SuppressWarnings("restriction")
public class VueTextuelle extends Vue{
	
	private Label monLabel;
	private TabPane infos;
	private Tab nomRue;
	private Tab infosLivraison;
	private Tab infosTournee;
	
	public VueTextuelle() {
		//Creation du separateur avec la vue graphique
		Separator separator = new Separator();
		separator.setOrientation(Orientation.VERTICAL);
		separator.setMinHeight(1200);
		separator.setMaxWidth(20);
		separator.setLayoutX(300);
		infos = new TabPane();
		monLabel = new Label();
		monLabel.setLayoutX(0);
		monLabel.setLayoutY(50);
		monLabel.setMinWidth(300);
		//Creation des Tabs
		nomRue = new Tab();
		nomRue.setContent(monLabel);
		nomRue.setText("Nom de la rue");
		infosLivraison = new Tab();
		infosLivraison.setText("Livraisons");
		infosTournee = new Tab();
		infosTournee.setText("Tournees");
		infos.getTabs().addAll(nomRue,infosLivraison,infosTournee);
		this.getChildren().add(infos);
		this.getChildren().add(separator);
	}
	
	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

}
