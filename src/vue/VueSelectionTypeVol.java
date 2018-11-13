package vue;

import java.awt.*;
import javax.swing.*;

public class VueSelectionTypeVol extends JPanel {
	
	private JLabel lblTitre;
	private JComboBox listeType;
	private JPanel panel;
	
	public VueSelectionTypeVol() {
		
		this.setLayout(new FlowLayout());
		
		Dimension taille = new Dimension(300, 20);
		lblTitre = new JLabel("Liste des avions", JLabel.CENTER);
		lblTitre.setPreferredSize(taille);
		this.add(lblTitre);
		
		listeType = new JComboBox();
		listeType.addItem("Commercial");
		listeType.addItem("Courrier");
		
		listeType.addActionListener(new ActionSelectionTypeVol());
	}
	
}
