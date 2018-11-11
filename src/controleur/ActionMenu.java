package controleur;

import vue.*;

import java.awt.event.*;
import javax.swing.*;

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
		switch (choix) {
			case "index" :
				this.panel.removeAll();
				this.fenetre.setContentPane(new VueAccueil(this.fenetre));
				this.fenetre.getContentPane().revalidate();
				break;
			case "quitter" :
				VuePopup pop = new VuePopup();
				int option = pop.addPopChoix("Êtes-vous sûr de vouloir quitter ?", "Quitter l'application");
				if (pop.choixEstOk(option)) {
					fenetre.dispose();
				}
				break;
		}
		
	}
}
