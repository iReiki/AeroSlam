package objet;

import java.time.LocalDate;

public class Date {
	
	//Attribut privé
	private LocalDate date;
	
	//Constructeur
	public Date(LocalDate uneDate){
		// LocalDate(int, int, int)
		this.date = uneDate;
	}
	
	// Méthodes
	public LocalDate getDate(){
		return this.date;
	}
	public String getDateFrancais(){
		return this.date.getDayOfMonth() + "/" + this.date.getMonthValue() + "/" + this.date.getYear();
	}
	
}
