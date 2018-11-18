package vue;

import java.awt.*;
import javax.swing.*;

import objet.*;

public class VueInfoPassager extends JPanel {

	private JLabel lblNom;
	private JLabel lblAdresse;

	public VueInfoPassager() {
		Dimension taille = new Dimension(400,10);
		lblNom = new JLabel("", JLabel.CENTER);
        lblAdresse = new JLabel("", JLabel.CENTER);
        lblNom.setPreferredSize(taille);
        lblAdresse.setPreferredSize(taille);
        
        this.setLayout(new GridLayout(2,1));
        
        this.add(lblNom);
        this.add(lblAdresse);
	}
	
	public void addLesInfos(Passager lePassager) {
		lblNom.setText("Passager n°" + lePassager.getNum() + " - " + lePassager.getNom() + " " +lePassager.getPrenom());
		lblAdresse.setText("Adresse : " + lePassager.getRue() + " - " +  lePassager.getCp() + " " + lePassager.getVille());
		this.revalidate();
	}
	
	
}
