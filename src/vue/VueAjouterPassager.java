package vue;

import java.awt.*;
import javax.swing.*;

import controleur.*;

public class VueAjouterPassager extends JPanel {
	
	private JPanel panelGrid;
	private JLabel lblTitre;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblRue;
	private JLabel lblCp;
	private JLabel lblVille;
	private JTextField jtfNom;
	private JTextField jtfPrenom;
	private JTextField jtfRue;
	private JTextField jtfCp;
	private JTextField jtfVille;
	private JButton btnValider;
	
	public VueAjouterPassager() {
		
		lblTitre = new JLabel("Ajouter un passager", JLabel.CENTER);
		lblNom = new JLabel("Nom :");
		lblPrenom = new JLabel("Prénom :");
		lblRue = new JLabel("Rue du domicile:");
		lblCp = new JLabel("Code Postal :");
		lblVille = new JLabel("Ville :");
		
		jtfNom = new JTextField();
		jtfPrenom = new JTextField();
		jtfRue = new JTextField();
		jtfCp = new JTextField();
		jtfVille = new JTextField();
		btnValider = new JButton("Valider");
		
		Dimension taille = new Dimension(200,20);
		jtfNom.setPreferredSize(taille);
		jtfPrenom.setPreferredSize(taille);
		jtfRue.setPreferredSize(taille);
		jtfCp.setPreferredSize(taille);
		jtfVille.setPreferredSize(taille);
		
		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(12,1));
		
		panelGrid.add(lblTitre);
		panelGrid.add(lblNom);
		panelGrid.add(jtfNom);
		panelGrid.add(lblPrenom);
		panelGrid.add(jtfPrenom);
		panelGrid.add(lblRue);
		panelGrid.add(jtfRue);
		panelGrid.add(lblCp);
		panelGrid.add(jtfCp);
		panelGrid.add(lblVille);
		panelGrid.add(jtfVille);
		panelGrid.add(btnValider);
		
		this.add(panelGrid);
		
		btnValider.addActionListener(new ActionAjouterPassager(jtfNom, jtfPrenom, jtfRue, jtfCp, jtfVille));
	}
}
