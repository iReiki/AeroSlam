package vue;
import controleur.*;
import java.awt.*;

import javax.swing.*;

public class VueConnexion extends JPanel {
	private JPanel panelGrid;
	//private JPanel panelBorder;
	private JLabel lblTitre;
	private JLabel lblLogin;
	private JLabel lblMdp;
	private JTextField jtfLogin;
	private JPasswordField jtfMdp;
	private JButton btnValider;
	
	public VueConnexion(JFrame f) {
		
		lblTitre = new JLabel("Connexion", JLabel.CENTER);
		lblLogin = new JLabel("Login :");
		lblMdp = new JLabel("Mot de passe :");
		
		jtfLogin = new JTextField();
		jtfMdp = new JPasswordField();
		btnValider = new JButton("Valider");
		
		Dimension taille = new Dimension(200,20);
		jtfLogin.setPreferredSize(taille);
		jtfMdp.setPreferredSize(taille);
		
		panelGrid = new JPanel();
		panelGrid.setLayout(new GridLayout(6,1));
		//panelBorder = new JPanel();
		//this.setLayout(new FlowLayout());
		
		panelGrid.add(lblTitre);
		panelGrid.add(lblLogin);
		panelGrid.add(jtfLogin);
		panelGrid.add(lblMdp);
		panelGrid.add(jtfMdp);
		panelGrid.add(btnValider);
		
		//this.add(lblTitre);
		this.add(panelGrid);
		
		btnValider.addActionListener(new ActionConnexion(jtfLogin, jtfMdp, panelGrid, f));
	}
}
