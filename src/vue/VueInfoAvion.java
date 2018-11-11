package vue;

import java.awt.*;
import javax.swing.*;

import objet.*;

public class VueInfoAvion extends JPanel {
	
	private Avion unAvion;
	private JLabel lblNom;
	private JLabel lblPlace;
	private JPanel panel;
	
	public VueInfoAvion (Avion lAvion) {
		this.unAvion = lAvion;
				
		lblNom = new JLabel("Nom de l'avion : " + this.unAvion.getNom());
		lblPlace = new JLabel("Nombre de places : " + this.unAvion.getNbPlace());
		
		panel = new JPanel();
		panel.setLayout(new GridLayout(2,1));
		
		panel.add(lblNom);
		panel.add(lblPlace);
		this.add(panel);
	}
	
}
