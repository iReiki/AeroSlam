package controleur;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import modele.*;
import vue.*;

public class ActionAjouterPassager implements ActionListener {
	
	private JTextField jtfNom;
	private JTextField jtfPrenom;
	private JTextField jtfRue;
	private JTextField jtfCp;
	private JTextField jtfVille;
	
	public ActionAjouterPassager(JTextField leNom, JTextField lePrenom, JTextField laRue, JTextField leCp, JTextField laVille) {
		this.jtfNom = leNom;
		this.jtfPrenom = lePrenom;
		this.jtfRue = laRue;
		this.jtfCp = leCp;
		this.jtfVille = laVille;
	}
	
	public void actionPerformed(ActionEvent e) {
		// Récupération des valeurs dans des variables
		String nom = this.jtfNom.getText();
		String prenom = this.jtfPrenom.getText();
		String rue = this.jtfRue.getText();
		String cp = this.jtfCp.getText();
		String ville = this.jtfVille.getText();
		VuePopup pop = new VuePopup();
		// Vérification si champ vide
		if (nom.length() == 0 || prenom.length() == 0 || rue.length() == 0 || cp.length() == 0 || ville.length() == 0) {
			pop.addPopErreur("Tous les champs ne sont pas remplis.");
		}
		else {
			// Vérification du champ du code postal
			if (cp.length() == 5 && isInteger(cp)) {
				int code = Integer.parseInt(cp);
				Modele.ajouterPassager(nom, prenom, rue, code, ville);
				pop.addPopMessage("Passager ajouté.");
			}
			else {
				pop.addPopErreur("Le code postal n'est pas valide.");
			}
		}
	}
	
	// Fonction pour vérifier si la chaine contient que des chiffres
	public boolean isInteger(String str) {
	    boolean trouver = true;
		int i = 0;
	    while (i < str.length() && Character.isDigit(str.charAt(i))) {
	    	i++;
	    }
	    if (i < str.length()) {
	    	trouver = false;
	    }
	    return trouver;
	}
}
