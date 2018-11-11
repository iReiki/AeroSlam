package vue;

import controleur.*;

import javax.swing.*;

public class Menu extends JMenuBar{
	
	private JMenu programme;
	private JMenuItem index;
	private JMenuItem quitter;
	private JMenu avion;
	private JMenuItem afficherAvion;
	private JMenuItem ajouterAvion;
	private JMenuItem retirerAvion;
	private JMenu vol;
	private JMenuItem afficherVol;
	private JMenuItem ajouterVol;
	private JMenuItem retirerVol;
	
	public Menu (JFrame f, JPanel p) {
		
		this.programme = new JMenu("Programme");
		this.index = new JMenuItem("Accueil");
		this.quitter = new JMenuItem("Quitter");
		programme.add(index);
		programme.add(quitter);
		
		this.avion = new JMenu("Avion");
		this.afficherAvion = new JMenuItem("Afficher les avions");
		this.ajouterAvion = new JMenuItem("Ajouter");
		this.retirerAvion = new JMenuItem("Retirer");
		avion.add(afficherAvion);
		avion.add(ajouterAvion);
		avion.add(retirerAvion);
		
		this.vol = new JMenu("Vol");
		this.afficherVol = new JMenuItem("Afficher les vols");
		this.ajouterVol = new JMenuItem("Ajouter");
		this.retirerVol = new JMenuItem("Retirer");
		vol.add(afficherVol);
		vol.add(ajouterVol);
		vol.add(retirerVol);
		
		
		this.add(programme);
		this.add(avion);
		this.add(vol);
		
		index.addActionListener(new ActionMenu("index", f, p));
		quitter.addActionListener(new ActionMenu("quitter", f, p));
		afficherAvion.addActionListener(new ActionMenu("afficherAvion", f, p));
		ajouterAvion.addActionListener(new ActionMenu("ajouterAvion", f, p));
		retirerAvion.addActionListener(new ActionMenu("retirerAvion", f, p));
		afficherVol.addActionListener(new ActionMenu("afficherVol", f, p));
		ajouterVol.addActionListener(new ActionMenu("ajouterVol", f, p));
		retirerVol.addActionListener(new ActionMenu("retirerVol", f, p));
		
	}
	
}
