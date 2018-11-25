package modele;

import java.util.ArrayList;

public class TourneeManager {
	
	protected ArrayList <Tournee> listeTournees;
	
	public void calculTournees(Plan plan){
		listeTournees = Algo(plan);
		
	}
	
	public ArrayList<Tournee> Algo(Plan plan){
		ArrayList<Tournee> res = null;
		return res;
	}

}
