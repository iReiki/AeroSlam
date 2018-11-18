package vue;

import java.awt.*;
import javax.swing.*;

import controleur.*;

public class VueRechercherPassager extends JPanel {
	
	private JPanel panelGrid;
	private JLabel lblTitre;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JTextField jtfNom;
	private JTextField jtfPrenom;
	private JButton btnValider;
	private VueInfoPassager info;
	private VueInfoSesVols infoSesVols;
	
	public VueRechercherPassager() {
		
		lblTitre = new JLabel("Rechercher un passager", JLabel.CENTER);
		lblNom = new JLabel("Nom du passager :");
		lblPrenom = new JLabel("Prénom du passager :");
		
		jtfNom = new JTextField();
		jtfPrenom = new JTextField();
		btnValider = new JButton("Valider");
		
		Dimension taille = new Dimension(200,20);
		jtfNom.setPreferredSize(taille);
		jtfPrenom.setPreferredSize(taille);
		
		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(7,1));
		
		panelGrid.add(lblTitre);
		panelGrid.add(lblNom);
		panelGrid.add(jtfNom);
		panelGrid.add(lblPrenom);
		panelGrid.add(jtfPrenom);
		panelGrid.add(btnValider);
		
		info = new VueInfoPassager();
		panelGrid.add(info);
		
		this.add(panelGrid);
		
		infoSesVols = new VueInfoSesVols();
		this.add(infoSesVols);
		
		btnValider.addActionListener(new ActionRechercherPassager(jtfNom, jtfPrenom, info, infoSesVols));
	}
}
