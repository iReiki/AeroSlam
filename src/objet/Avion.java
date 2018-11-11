package objet;

public class Avion {
	
	// Attributs privés
	private int num;
	private String nom;
	private int nbPlace;
	
	public Avion() {
		
	}
	
	public Avion(int unNum, String unNom, int unNb) {
		this.num = unNum;
		this.nom = unNom;
		this.nbPlace = unNb;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public void setNom(String unNom) {
		this.nom = unNom;
	}
	
	public int getNbPlace() {
		return this.nbPlace;
	}
	
	public void setNbPlace(int unNb) {
		this.nbPlace = unNb;
	}
	
	public String toXML() {
		String chaine = "";
		chaine += "<avion>";
		chaine += "<numav>" + this.num +"</numav>";
		chaine += "<nomav>" + this.nom +"</nomav>";
		chaine += "<nbplace>" + this.nbPlace +"</nbplace>";
		chaine += "</avion>";
		return chaine;
	}
	
}
