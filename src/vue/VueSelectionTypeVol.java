package vue;

import java.awt.*;
import javax.swing.*;
import java.util.*;

import objet.*;
import controleur.*;

public class VueSelectionTypeVol extends JPanel {
	
	private JLabel lblTitre;
	private JComboBox listeType;
	private JPanel panel;
	private JPanel panelCard;
	private VueAfficherVolCourrier vueVolCou;
	private VueAfficherVolCommercial vueVolCom;
	
	public VueSelectionTypeVol(ArrayList<VolCourrier> lesVolsCou, ArrayList<VolCommercial> lesVolsCom, ArrayList<Passager> lesP) {
		
		this.setLayout(new FlowLayout());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		Dimension taille = new Dimension(300, 20);
		lblTitre = new JLabel("Liste des vols", JLabel.CENTER);
		lblTitre.setPreferredSize(taille);
		panel.add(lblTitre);
		
		listeType = new JComboBox();
		listeType.addItem("Commerciaux");
		listeType.addItem("Courriers");
		panel.add(listeType);
		
		this.add(panel);
		
		panelCard = new JPanel(new CardLayout());
		vueVolCou = new VueAfficherVolCourrier(lesVolsCou, lesP);
		vueVolCom = new VueAfficherVolCommercial(lesVolsCom);
		panelCard.add(vueVolCom, "Commerciaux");
		panelCard.add(vueVolCou, "Courriers");
		
		this.add(panelCard);
		
		listeType.addItemListener(new ActionSelectionTypeVol(listeType, panelCard));
	}
	
}
