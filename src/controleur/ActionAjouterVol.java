package controleur;

import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import java.text.ParseException;

import modele.Modele;
import vue.*;
import objet.*;

public class ActionAjouterVol implements ActionListener {
	
	private String choix;
	private JTextField jtfDate;
	private JComboBox listeAvion;
	private JComboBox listeDest;
	private JRadioButton jr1;
	private JRadioButton jr2;
	private JPanel panel;
	private VueInfoAvion info;
	
	public ActionAjouterVol(String unChoix, JPanel p, JComboBox listeAv, VueInfoAvion laVue) {
		this.choix = unChoix;
		this.panel = p;
		this.listeAvion = listeAv;
		this.info = laVue;
	}
	
	public ActionAjouterVol(String unChoix, JTextField jtfDeLaDate, JComboBox laListeAvion, JComboBox laListeDest, JRadioButton jrCom, JRadioButton jrCou) {
		this.choix = unChoix;
		this.jtfDate = jtfDeLaDate;
		this.listeAvion = laListeAvion;
		this.listeDest = laListeDest;
		this.jr1 = jrCom;
		this.jr2 = jrCou;
	}
	
	public void actionPerformed(ActionEvent e) {
		VuePopup pop = new VuePopup();
		switch(choix) {
			case "liste" :
				Avion unAvion = Modele.getUnAvion((Integer)this.listeAvion.getSelectedItem());
				this.info.addlesInfos(unAvion);
				break;
			case "ajouter" :
				String date = this.jtfDate.getText();
				// V�rification du format de la date
				if (!date.matches("[0-9]{2}[/][0-9]{2}[/][0-9]{4}")) {
					pop.addPopErreur("Le format de la date n'est pas respect� !");
				}
				else {
					int jour, mois, annee;
					// R�cup�ration du jour, du mois et de l'ann�e
					jour = Integer.parseInt(date.substring(0, 2));
					mois = Integer.parseInt(date.substring(3, 5));
					annee = Integer.parseInt(date.substring(6, 10));
					// V�rification si le mois est valide
					if (mois > 12 || mois < 1) {
						pop.addPopErreur("La date saisie est invalide.");
					}
					else {
						// V�rification si le date du jour est valide
						LocalDate verifDate = LocalDate.of(annee, mois, 1);
						int nbJoursMois = verifDate.getMonth().maxLength();
						if (jour > nbJoursMois || jour < 1) {
							pop.addPopErreur("La date saisie est invalide.");
						}
						else {
							LocalDate dateVol = LocalDate.of(annee, mois, jour);
							LocalDate dateCourante = LocalDate.now();
							if(dateVol.isBefore(dateCourante)) {
								pop.addPopErreur("La date saisie est d�j� pass�e.");
							}
							else {
								// Date cr�e
								Date uneDate = new Date(dateVol);
								// R�cup�ration du choix de l'avion
								int numAv = (Integer)this.listeAvion.getSelectedItem();
								// R�cup�ration du choix de la destination
								String villeDest = String.valueOf(this.listeDest.getSelectedItem());
								Destination dest = Modele.getUneDestination(villeDest);
								// R�cup�ration du type de vol s�lectionn�
								int type = 0;
								if (jr1.isSelected()) {
									type = 0;
								}
								if (jr2.isSelected()) {
									type = 1;
								}
								Modele.ajouterVol(uneDate, type, dest.getId(), numAv);
								pop.addPopMessage("Vol ajout� avec succ�s.");
							}
							
						}
					}
					
				}
				break;
		}
	}
	
}
