package vue;

import java.awt.*;
import javax.swing.*;

import objet.*;

public class VueInfoAvion extends JPanel {
	
	private JLabel lblNom;
	private JLabel lblPlace;
	
	public VueInfoAvion () {
        lblNom = new JLabel("", JLabel.CENTER);
        lblPlace = new JLabel("", JLabel.CENTER);
        
        this.setLayout(new GridLayout(2,1));
        
        this.add(lblNom);
        this.add(lblPlace);
    }
	
	public void addlesInfos(Avion lAvion) {
		lblNom.setText("Nom de l'avion : " + lAvion.getNom());
		lblPlace.setText("Nombre de places : " + lAvion.getNbPlace());
		this.revalidate();
	}
	
}
