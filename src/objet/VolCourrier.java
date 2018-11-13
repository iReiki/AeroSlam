package objet;

import java.util.*;

public class VolCourrier extends Vol {
	
	private ArrayList<Passager> lesPassagers;
	
	public VolCourrier(int unNum, Date uneDate, int unType, Destination laDest, Avion lAvion) {
		super(unNum, uneDate, unType, laDest, lAvion);
		this.lesPassagers = new ArrayList<Passager>();
	}
}
