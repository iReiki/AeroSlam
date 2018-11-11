package vue;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import controleur.*;
import objet.*;

public class VueAjouterVol extends JPanel {
	
	private JPanel panelGrid;
	private JPanel panelJr;
	private JLabel lblTitre;
	private JLabel lblDate;
	private JLabel lblNom;
	private JLabel lblDest;
	private JLabel lblType;
	private JTextField jtfDate;
	private JComboBox listeAvion;
	private JComboBox listeDest;
	private JRadioButton jr1;
	private JRadioButton jr2;
	private ButtonGroup groupeBouton;
	private JButton btnValider;
	
	public VueAjouterVol(ArrayList<Avion> lesAvions, ArrayList<Destination> lesDest) {
				
		lblTitre = new JLabel("Ajouter un vol", JLabel.CENTER);
		lblDate = new JLabel("Date du vol (Format JJ/MM/AAAA) :");
		lblNom = new JLabel("Choix de l'avion :");
		lblDest = new JLabel("Destination du vol :");
		lblType = new JLabel("Type du vol :");
		
		jtfDate = new JTextField();
		listeAvion = new JComboBox();
		for(Avion a : lesAvions) {
			listeAvion.addItem(a.getNum());
		}
		listeDest = new JComboBox();
		for(Destination d : lesDest) {
			listeDest.addItem(d.getVille());
		}
		jr1 = new JRadioButton("Commercial");
		jr2 = new JRadioButton("Courrier");
		jr1.setSelected(true);
		groupeBouton = new ButtonGroup();
		groupeBouton.add(jr1);
		groupeBouton.add(jr2);
		btnValider = new JButton("Valider");
		
		Dimension taille = new Dimension(200,10);
		jtfDate.setPreferredSize(taille);
		listeAvion.setPreferredSize(taille);
		
		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(11,1));
		
		panelJr = new JPanel();
		panelJr.add(jr1);
		panelJr.add(jr2);
		
		panelGrid.add(lblTitre);
		panelGrid.add(lblDate);
		panelGrid.add(jtfDate);
		panelGrid.add(lblNom);
		panelGrid.add(listeAvion);
		panelGrid.add(lblDest);
		panelGrid.add(listeDest);
		panelGrid.add(lblType);
		panelGrid.add(panelJr);
		panelGrid.add(btnValider);
		
		this.add(panelGrid);
		
		listeAvion.addActionListener(new ActionAjouterVol("liste", this.panelGrid, listeAvion.getSelectedItem()));
		btnValider.addActionListener(new ActionAjouterVol("ajouter", jr1, jr2, listeAvion));
	}
}
