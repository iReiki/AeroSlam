package objet;

import java.util.*;

public class Passager {
	
	private int num;
	private String nom;
	private String prenom;
	private String rue;
	private int cp;
	private String ville;
	private ArrayList<VolCourrier> sesVols;
	
	public Passager(int unNum, String unNom, String unPrenom, String uneRue, int unCp, String uneVille) {
		this.num = unNum;
		this.nom = unNom;
		this.prenom = unPrenom;
		this.rue = uneRue;
		this.cp = unCp;
		this.ville = uneVille;
		this.sesVols = new ArrayList<VolCourrier>();
	}
	
	public int getNum() {
		return this.num;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public String getPrenom() {
		return this.prenom;
	}
	
	public String getRue() {
		return this.rue;
	}
	
	public int getCp() {
		return this.cp;
	}
	
	public String getVille() {
		return this.ville;
	}
	
}
