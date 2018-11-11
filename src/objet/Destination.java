package objet;

public class Destination {
	
	private int id;
	private String ville;
	private String pays;
	
	public Destination(int unId, String uneVille, String unPays) {
		this.id = unId;
		this.ville = uneVille;
		this.pays = unPays;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getVille() {
		return this.ville;
	}
	
	public String getPays() {
		return this.pays;
	}
}
