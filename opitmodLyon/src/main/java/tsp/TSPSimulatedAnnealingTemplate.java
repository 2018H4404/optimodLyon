package tsp;

public class TSPSimulatedAnnealingTemplate implements TSP{
	
	private Integer[] meilleuresSolutions;
    private Double coutMeilleuresSolutions;
    private Boolean tempsLimiteAtteint;
    
    private double temperature;
    private double ratioRefroidisement;
    private Boolean arreterCalcul;
    
    private final double RATIO_REFROIDISSEMENT = 0.03;
    private final double TEMPERATURE = 10000;
    private final double TEMPERATURE_MINIMALE = 0;
    
	public Boolean getTempsLimiteAtteint(){
        return tempsLimiteAtteint;
    }
	
	public TSPSimulatedAnnealingTemplate(){
        temperature = TEMPERATURE;
        ratioRefroidisement = RATIO_REFROIDISSEMENT;
    }
	

	@Override
	public void chercheSolution(int tpsLimite, int nbSommets, int[][] cout, int[] duree) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Integer getMeilleureSolution(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCoutMeilleureSolution() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void arreterCalcul(Boolean valeur){
        this.arreterCalcul = valeur;
    }
	
}
