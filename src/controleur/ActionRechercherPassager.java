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
		// Récupération du nom et prénom
		String nom = this.jtfNom.getText().toUpperCase();
		String prenom = this.jtfPrenom.getText();
		VuePopup pop = new VuePopup();
		// Vérification champ vide
		if(nom.length() == 0 || prenom.length() == 0) {
			pop.addPopErreur("Les champs ne sont pas remplis !");
		}
		else {
			// Vérification si le passager est présent ou non
			if (Modele.rechercherUnPassager(nom, prenom) == null) {
				pop.addPopErreur("Ce passager n'existe pas !");	
			}
			else {
				// Mise à jour des affichages
				pop.addPopMessage("Passager trouvé.");
				unPassager = Modele.rechercherUnPassager(nom, prenom);
				this.info.addLesInfos(unPassager);
				this.infoV.addLesInfos(unPassager.getSesVols());
			}
		}
	}
}
