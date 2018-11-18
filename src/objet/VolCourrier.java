package objet;

import java.util.*;

import modele.Modele;

public class VolCourrier extends Vol {
	
	private ArrayList<Passager> lesPassagers;
	
	public VolCourrier(int unNum, Date uneDate, int unType, Destination laDest, Avion lAvion) {
		super(unNum, uneDate, unType, laDest, lAvion);
		this.lesPassagers = new ArrayList<Passager>();
	}
	
	public ArrayList<Passager> getLesPassagers() {
		this.lesPassagers = Modele.getLesPassagers(super.getNum());
		return this.lesPassagers;
	}
}
