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
		int option = pop.addPopChoix("Cette action est irr�versible. �tes-vous s�r de votre choix ?", "Validation de la suppression");
		int selection = this.tableau.getSelectedRow();
		if (pop.choixEstOk(option)) {
			if (selection == -1) {
				pop.addPopErreur("Aucune ligne s�lectionn�e.");
			}
			else {
				int numAvion = (Integer)this.tableau.getValueAt(selection, 0);
				Modele.retirerAvion(numAvion);
				// Retirer la ligne s�lectionn�e
				((DefaultTableModel)this.tableau.getModel()).removeRow(selection);
			}
		}
	}	
	
}
