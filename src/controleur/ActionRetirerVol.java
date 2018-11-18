package controleur;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import modele.Modele;
import objet.Vol;
import vue.*;

public class ActionRetirerVol implements ActionListener {
	
	private JTable tableau;
	
	public ActionRetirerVol(JTable unTableau) {
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
				int numVol = (Integer)this.tableau.getValueAt(selection, 0);
				if (Modele.volEstVide(numVol)) {
					pop.addPopErreur("Impossible de supprimer le vol : le vol comporte des passagers.");
				}
				else {
					Modele.retirerAvion(numVol);
					// Retirer la ligne s�lectionn�e
					((DefaultTableModel)this.tableau.getModel()).removeRow(selection);
				}
				
			}
		}
	}	
	
}
