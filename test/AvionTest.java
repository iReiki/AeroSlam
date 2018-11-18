import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import objet.*;

public class AvionTest {
	
	private Avion a;
	
	@Before
	public void setUp() throws Exception {
		a = new Avion(1, "A300", 50);
	}

	@Test
	public void testToXML() {
		Assert.assertNotNull("Chaine non vide", a.toXML());
	}

}
