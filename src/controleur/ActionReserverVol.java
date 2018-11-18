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
		// V�rification si une ligne est s�lectionn�e
		int selection = this.tableau.getSelectedRow();
		if (selection == -1) {
			pop.addPopErreur("Aucune ligne s�lectionn�e.");
		}
		else {
			// R�cup�ration des valeurs
			int numVol = (Integer)this.tableau.getValueAt(selection, 0);
			int numP = this.listeP.getSelectedIndex() + 1;
			leA = Modele.getUnVolCourrier(numVol).getUnAvion();
			int nbPlacesPrises = Modele.getNbPlacesReservees(numVol);
			// V�rification si l'avion est complet
			if (nbPlacesPrises >= leA.getNbPlace()) {
				pop.addPopErreur("Ce vol est d�j� complet !");
			}
			else {
				// V�rification si le passager est d�j� dans le vol
				if (Modele.passagerEstAjoute(numVol, numP)) {
					pop.addPopErreur("Ce passager est d�j� pr�sent dans ce vol.");
				}
				else {
					// Ajout du passager dans le vol
					this.leP = Modele.getUnPassager(numP);
					pop.addPopMessage(this.leP.getPrenom() + " " + this.leP.getNom() + " a �t� ajout� au vol " + numVol);
					Modele.reserverVol(numVol, numP);
				}
			
			}
			
		}
		
	}
}
