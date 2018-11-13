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
		ArrayList<Avion> lesAvions = Modele.getLesAvions();
		ArrayList<Destination> lesDest = Modele.getLesDestinations();
		ArrayList<VolCourrier> lesVolsC = Modele.getLesVolCourriers();
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
				this.fenetre.setContentPane(new VueAfficherVolCourrier(lesVolsC));
				this.fenetre.getContentPane().revalidate();
				break;
			case "aPropos" :
				pop.addPopMessage("Ce programme a été développé par Jean-Luc LYS--PHORIMAVONG.");
				break;
		}
		
	}
}
