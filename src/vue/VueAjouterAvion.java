package vue;

import java.awt.*;
import javax.swing.*;

import controleur.*;

public class VueAjouterAvion extends JPanel {
	
	private JPanel panelGrid;
	private JLabel lblTitre;
	private JLabel lblNom;
	private JLabel lblPlace;
	private JTextField jtfNom;
	private JTextField jtfPlace;
	private JButton btnValider;
	
	public VueAjouterAvion() {
		
		lblTitre = new JLabel("Ajouter un avion", JLabel.CENTER);
		lblNom = new JLabel("Nom de l'avion :");
		lblPlace = new JLabel("Nombre de places :");
		
		jtfNom = new JTextField();
		jtfPlace = new JTextField();
		btnValider = new JButton("Valider");
		
		Dimension taille = new Dimension(200,20);
		jtfNom.setPreferredSize(taille);
		jtfPlace.setPreferredSize(taille);
		
		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(6,1));
		//panelBorder = new JPanel();
		//this.setLayout(new FlowLayout());
		
		panelGrid.add(lblTitre);
		panelGrid.add(lblNom);
		panelGrid.add(jtfNom);
		panelGrid.add(lblPlace);
		panelGrid.add(jtfPlace);
		panelGrid.add(btnValider);
		
		//this.add(lblTitre);
		this.add(panelGrid);
		
		btnValider.addActionListener(new ActionAjouterAvion(jtfNom, jtfPlace));
	}
}
