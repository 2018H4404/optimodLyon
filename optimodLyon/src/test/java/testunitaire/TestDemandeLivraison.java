package testunitaire;

import static org.junit.Assert.*;
import org.junit.*;
import java.util.HashMap;
import modele.DemandeLivraison;

public class TestDemandeLivraison {
	
	DemandeLivraison dl = new DemandeLivraison();
	
	@Before
	public void init() {
		dl.ajouterEntrepot(48830473, 45.75305, 4.857429, 8, 8, 0);	
		dl.ajouterPointLivraison(48830474, 45.75407, 4.857418, 60);	
	}
	
	@Test
	public void TestAjouterEntrepot() {
		dl.ajouterEntrepot(48830472, 45.75406, 4.857418, 8, 0, 0);
		assert(dl.getEntrepots().size()==2);
	}
	
	@Test
	public void TestAjouterPointLivraison() {
		dl.ajouterPointLivraison(48830472, 45.75406, 4.857418, 360);
		assert(dl.getEntrepots().size()==2);
	}
	
	@Test
	public void TestClear() {
		dl.clear();
		assertNull(dl.getEntrepots());
		assertNull(dl.getLivraisons());
	}
	

}
