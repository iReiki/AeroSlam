package controleur;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import modele.Modele;
import objet.Avion;
import vue.*;

public class ActionRetirerPassager implements ActionListener {
	
	private JTable tableau;
	private int numVol;
	
	public ActionRetirerPassager(JTable unTableau, int numV) {
		this.tableau = unTableau;
		this.numVol = numV;
	}
	
	public void actionPerformed(ActionEvent e) {
		VuePopup pop = new VuePopup();
		int option = pop.addPopChoix("Cette action est irr�versible. �tes-vous s�r de votre choix ?", "Validation de la suppression");
		// V�rification si une ligne est s�lectionn�e
		int selection = this.tableau.getSelectedRow();
		if (pop.choixEstOk(option)) {
			if (selection == -1) {
				pop.addPopErreur("Aucune ligne s�lectionn�e.");
			}
			else {
				int numP = (Integer)this.tableau.getValueAt(selection, 0);
				Modele.annulerReservation(this.numVol, numP);
				// Retirer la ligne s�lectionn�e
				((DefaultTableModel)this.tableau.getModel()).removeRow(selection);
			}
		}
	}	
	
}
