package controleur;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import modele.Modele;
import objet.Avion;
import vue.*;

public class ActionRetirerAvion implements ActionListener {
	
	private JTable tableau;
	
	public ActionRetirerAvion(JTable unTableau) {
		this.tableau = unTableau;
	}
	
	public void actionPerformed(ActionEvent e) {
		VuePopup pop = new VuePopup();
		int option = pop.addPopChoix("Cette action est irréversible. Êtes-vous sûr de votre choix ?", "Validation de la suppression");
		// Vérification si une ligne est sélectionnée
		int selection = this.tableau.getSelectedRow();
		if (pop.choixEstOk(option)) {
			if (selection == -1) {
				pop.addPopErreur("Aucune ligne sélectionnée.");
			}
			else {
				// Vérification si l'avion est utilisé dans un vol
				int numAvion = (Integer)this.tableau.getValueAt(selection, 0);
				if (Modele.possedeUnVol(numAvion)) {
					pop.addPopErreur("Impossible de supprimer l'avion : un ou plusieurs vol(s) lui a été attribué.");
				}
				else {
					Modele.retirerAvion(numAvion);
					// Retirer la ligne sélectionnée
					((DefaultTableModel)this.tableau.getModel()).removeRow(selection);
				}
				
			}
		}
	}	
	
}
