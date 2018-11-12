package objet;

import java.util.ArrayList;

import modele.Modele;

public class Aeroport {
	
	public Aeroport() {
		
	}
	
	public ArrayList<Vol> getLesVols() {
		return Modele.getLesVols();
	}
	
	public ArrayList<Destination> getLesDestinations() {
		return Modele.getLesDestinations();
	}
	
	
	
}
