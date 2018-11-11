package vue;

import javax.swing.*;

public class VueAccueil extends JPanel {
	
	private JLabel lblTitre;
	
	public VueAccueil(JFrame f) {
		
		lblTitre = new JLabel("Application AeroSlam");
		
		this.add(lblTitre);
		
		f.setJMenuBar(new Menu(f, this));
		
	}
}
