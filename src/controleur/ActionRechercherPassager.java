package controleur;

import java.awt.event.*;
import javax.swing.*;

import modele.*;
import vue.*;
import objet.*;

public class ActionRechercherPassager implements ActionListener{
	
	private JTextField jtfNom;
	private JTextField jtfPrenom;
	private VueInfoPassager info;
	private VueInfoSesVols infoV;
	private Passager unPassager;
	
	public ActionRechercherPassager (JTextField leNom, JTextField lePrenom, VueInfoPassager laVue, VueInfoSesVols laVueV ) {
		this.jtfNom = leNom;
		this.jtfPrenom = lePrenom;
		this.info = laVue;
		this.infoV = laVueV;
	}
	
	public void actionPerformed(ActionEvent e) {
		// R�cup�ration du nom et pr�nom
		String nom = this.jtfNom.getText().toUpperCase();
		String prenom = this.jtfPrenom.getText();
		VuePopup pop = new VuePopup();
		// V�rification champ vide
		if(nom.length() == 0 || prenom.length() == 0) {
			pop.addPopErreur("Les champs ne sont pas remplis !");
		}
		else {
			// V�rification si le passager est pr�sent ou non
			if (Modele.rechercherUnPassager(nom, prenom) == null) {
				pop.addPopErreur("Ce passager n'existe pas !");	
			}
			else {
				// Mise � jour des affichages
				pop.addPopMessage("Passager trouv�.");
				unPassager = Modele.rechercherUnPassager(nom, prenom);
				this.info.addLesInfos(unPassager);
				this.infoV.addLesInfos(unPassager.getSesVols());
			}
		}
	}
}
