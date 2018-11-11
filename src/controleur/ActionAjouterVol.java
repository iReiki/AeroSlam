package controleur;

import java.awt.event.*;
import javax.swing.*;

import modele.Modele;
import vue.*;
import objet.*;

public class ActionAjouterVol implements ActionListener {
	
	private String choix;
	private JRadioButton jr1;
	private JRadioButton jr2;
	private JComboBox liste;
	private Avion unAvion;
	private JPanel panel;
	private JPanel panelGrid;
	
	public ActionAjouterVol(String unChoix, JPanel p, Object numAv) {
		this.choix = unChoix;
		this.panel = p;
		this.unAvion = Modele.getUnAvion((Integer)numAv);
	}
	
	public ActionAjouterVol(String unChoix, JRadioButton leJr, JRadioButton autreJr, JComboBox laListe) {
		this.choix = unChoix;
		this.jr1 = leJr;
		this.jr2 = autreJr;
		this.liste = laListe;
	}
	
	public void actionPerformed(ActionEvent e) {
		switch(choix) {
			case "liste" :
				// A voir
				this.panel.add(new VueInfoAvion(this.unAvion));
				this.panel.revalidate();
				System.out.println("1");
				break;
			case "ajouter" :
				
				break;
		}
	}
	
}
