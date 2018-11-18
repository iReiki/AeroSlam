import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import modele.*;

public class ModeleTest {

	@Before
	public void setUp() throws Exception {
	}
	
	// Test qui vérifie que la méthode getNbAvions retourne bien le bon nombre d'avions
	@Test
	public void testNbAvions() {
		assertEquals("Le nombre d'avion est incorrect", 3, Modele.getNbAvions());
	}
	
	// Test qui vérifie le nombre d'avions dans cette liste
	@Test
    public void testGetLesAvions(){
        assertEquals("Nombre total des avions incorrect", 3, Modele.getLesAvions().size());
    }
	
	// Test qui vérifie le nombre de vols
	@Test
    public void testNbVols(){
        assertEquals("Nombre de vol incorrect", 13, Modele.getLesVols().size());
    }
	
		

}
