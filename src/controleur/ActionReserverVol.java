package controleur;

import java.awt.event.*;
import javax.swing.*;

import modele.Modele;
import vue.*;
import objet.*;

public class ActionReserverVol implements ActionListener {
	
	private JTable tableau;
	private JComboBox listeP;
	private Passager leP;
	private Avion leA;
	
	public ActionReserverVol(JTable tableauVol, JComboBox listePassager) {
		this.tableau = tableauVol;
		this.listeP = listePassager;
	}
	
	public void actionPerformed(ActionEvent e) {
		VuePopup pop = new VuePopup();
		int selection = this.tableau.getSelectedRow();
		if (selection == -1) {
			pop.addPopErreur("Aucune ligne sélectionnée.");
		}
		else {
			int numVol = (Integer)this.tableau.getValueAt(selection, 0);
			int numP = this.listeP.getSelectedIndex() + 1;
			leA = Modele.getUnVolCourrier(numVol).getUnAvion();
			int nbPlacesPrises = Modele.getNbPlacesReservees(numVol);
			if (nbPlacesPrises >= leA.getNbPlace()) {
				pop.addPopErreur("Ce vol est déjà complet !");
			}
			else {
				if (Modele.passagerEstAjoute(numVol, numP)) {
					pop.addPopErreur("Ce passager est déjà présent dans ce vol.");
				}
				else {
					this.leP = Modele.getUnPassager(numP);
					pop.addPopMessage(this.leP.getPrenom() + " " + this.leP.getNom() + " a été ajouté au vol " + numVol);
					Modele.reserverVol(numVol, numP);
				}
			
			}
			
		}
		
	}
}
