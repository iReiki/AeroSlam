package controleur;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

import vue.*;
import modele.*;
import objet.*;

public class ActionMenu implements ActionListener {
	
	private String choix;
	private JFrame fenetre;
	private JPanel panel;
	
	public ActionMenu(String leChoix, JFrame uneFenetre, JPanel unPanel) {
		this.choix = leChoix;
		this.fenetre = uneFenetre;
		this.panel = unPanel;
	}
	
	public void actionPerformed(ActionEvent e) {
		// Récupération des listes
		ArrayList<Avion> lesAvions = Modele.getLesAvions();
		ArrayList<Destination> lesDest = Modele.getLesDestinations();
		ArrayList<VolCourrier> lesVolsCou = Modele.getLesVolCourriers();
		ArrayList<VolCommercial> lesVolsCom = Modele.getLesVolCommerciaux();
		ArrayList<Vol> lesVols = Modele.getLesVols();
		ArrayList<Passager> tousLesPassagers = Modele.getTousLesPassagers();
		VuePopup pop = new VuePopup();
		switch (choix) {
			case "index" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAccueil(this.fenetre));
				this.fenetre.getContentPane().revalidate();
				break;
			case "quitter" :
				int option = pop.addPopChoix("Êtes-vous sûr de vouloir quitter ?", "Quitter l'application");
				if (pop.choixEstOk(option)) {
					fenetre.dispose();
				}
				break;
			case "ajouterAvion" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAjouterAvion());
				this.fenetre.getContentPane().revalidate();
				break;
			case "afficherAvion" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAfficherAvion(lesAvions));
				this.fenetre.getContentPane().revalidate();
				break;
			case "retirerAvion" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueRetirerAvion(lesAvions));
				this.fenetre.getContentPane().revalidate();
				break;
			case "ajouterVol" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAjouterVol(lesAvions, lesDest));
				this.fenetre.getContentPane().revalidate();
				break;
			case "afficherVol":
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueSelectionTypeVol(lesVolsCou, lesVolsCom, tousLesPassagers));
				this.fenetre.getContentPane().revalidate();
				break;
			case "retirerVol":
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueRetirerVol(lesVols));
				this.fenetre.getContentPane().revalidate();
				break;
			case "ajouterPassager" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAjouterPassager());
				this.fenetre.getContentPane().revalidate();
				break;
			case "afficherPassager" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAfficherTousLesPassagers(tousLesPassagers));
				this.fenetre.getContentPane().revalidate();
				break;
			case "rechercherPassager" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueRechercherPassager());
				this.fenetre.getContentPane().revalidate();
				break;	
			case "aPropos" :
				pop.addPopMessage("Ce programme a été développé par Jean-Luc LYS--PHORIMAVONG.");
				break;
		}
		
	}
}
