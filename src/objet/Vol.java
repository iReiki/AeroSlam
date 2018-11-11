package objet;

public class Vol {
	
	private int num;
	private Date date;
	private Avion unAvion;
	
	public Vol(int unNum, Date uneDate, Avion lAvion) {
		this.num = unNum;
		this.date = uneDate;
		this.unAvion = lAvion;
	}
}
