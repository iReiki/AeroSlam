package controleur;

import java.awt.event.*;
import javax.swing.*;

import modele.Modele;
import objet.*;
import vue.*;

public class ActionAfficherPassager implements ActionListener {
	
	private JTable tableau;
	
	public ActionAfficherPassager(JTable tableauVol) {
		this.tableau = tableauVol;
	}
	
	public void actionPerformed(ActionEvent e) {
		VuePopup pop = new VuePopup();
		int selection = this.tableau.getSelectedRow();
		// Vérification si une ligne est sélectionnée
		if (selection == -1) {
			pop.addPopErreur("Aucune ligne sélectionnée.");
		}
		else {
			// Affichage de la liste des passagers du vol dans une autre fenetre
			int numVol = (Integer)this.tableau.getValueAt(selection, 0);
			VolCourrier unVol = Modele.getUnVolCourrier(numVol);
			new VueAfficherPassager(unVol.getLesPassagers(), numVol);
		}
	}
	
}
