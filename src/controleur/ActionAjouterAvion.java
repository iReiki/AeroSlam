package controleur;

import java.awt.event.*;
import javax.swing.*;

import modele.*;
import vue.*;

public class ActionAjouterAvion implements ActionListener{
	private JTextField jtfNom;
	private JTextField jtfPlace;
	
	public ActionAjouterAvion (JTextField leNom, JTextField leNb ) {
		this.jtfNom = leNom;
		this.jtfPlace = leNb;
	}
	
	public void actionPerformed(ActionEvent e) {
		String nom = this.jtfNom.getText();
		String nb = this.jtfPlace.getText();
		VuePopup pop = new VuePopup();
		// Vérification si champ vide
		if(nom.length() == 0 || nb.length() == 0) {
			pop.addPopErreur("Les champs ne sont pas remplis !");
		}
		else {
			// Vérification si le nombre de place contient que des chiffres
			if (nb.matches("[0-9]*")) {
				int nbPlace = Integer.parseInt(nb);
				Modele.ajouterAvion(nom, nbPlace);
				pop.addPopMessage("Avion ajouté");
			}
			else {
				pop.addPopErreur("Le nombre de place comporte des caractères incorrects !");
			}
		}
	}
}
