package objet;

public class Vol {
	
	private int num;
	private Date date;
	private int type;
	private Destination uneDestination;
	private Avion unAvion;
	
	public Vol(int unNum, Date uneDate, int unType, Destination laDest, Avion lAvion) {
		this.num = unNum;
		this.date = uneDate;
		this.type = unType;
		this.uneDestination = laDest;
		this.unAvion = lAvion;
	}
	
	public int getNum() {
		return this.num;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public int getTypeVol() {
		return this.type;
	}
	
	public Destination getUneDestination() {
		return this.uneDestination;
	}
	
	public Avion getUnAvion() {
		return this.unAvion;
	}
	
	public String getLibelleTypeVol(int num) {
		String type = "";
		if (num == 0) {
			type = "Commercial";
		}
		else {
			type = "Courrier";
		}
		return type;
	}
	
	public String toXML(){
        String chaine = "\n <vol>"
        				+ "\n \t <numvol>" + this.num + "</numvol> \n"
                        + "\t <datevol>" + this.date.getDateFrancais() + "</datevol> \n"
                        + "\t <typevol>" + this.type + "</typevol> \n"
                        + "\t <iddest>" + this.uneDestination.getId() + "</iddest> \n"
                        + "\t <numav>" + this.unAvion.getNum() + "</NUMEROAVION> \n "
                        + "</vol>";
        return chaine;
    }
	
}
