package vue;

import controleur.*;

import javax.swing.*;

public class Menu extends JMenuBar{
	
	private JMenu programme;
	private JMenuItem index;
	private JMenuItem quitter;
	private JMenu avion;
	private JMenuItem ajouterAvion;
	private JMenuItem afficherAvion;
	private JMenuItem retirerAvion;
	private JMenu vol;
	private JMenuItem ajouterVol;
	private JMenuItem afficherVol;
	private JMenuItem retirerVol;
	private JMenu passager;
	private JMenuItem ajouterPassager;
	private JMenuItem afficherPassager;
	private JMenuItem rechercherPassager;
	private JMenu info;
	private JMenuItem aPropos;
	
	public Menu (JFrame f, JPanel p) {
		
		this.programme = new JMenu("Programme");
		this.index = new JMenuItem("Accueil");
		this.quitter = new JMenuItem("Quitter");
		programme.add(index);
		programme.add(quitter);
		
		this.avion = new JMenu("Avion");
		this.ajouterAvion = new JMenuItem("Ajouter");
		this.afficherAvion = new JMenuItem("Afficher les avions");
		this.retirerAvion = new JMenuItem("Retirer");
		avion.add(ajouterAvion);
		avion.add(afficherAvion);
		avion.add(retirerAvion);
		
		this.vol = new JMenu("Vol");
		this.ajouterVol = new JMenuItem("Ajouter");
		this.afficherVol = new JMenuItem("Afficher les vols");
		this.retirerVol = new JMenuItem("Retirer");
		vol.add(ajouterVol);
		vol.add(afficherVol);
		vol.add(retirerVol);
		
		this.passager = new JMenu("Passager");
		this.ajouterPassager = new JMenuItem("Ajouter");
		this.afficherPassager = new JMenuItem("Afficher tous les passagers");
		this.rechercherPassager = new JMenuItem("Rechercher un passager et ses vols");
		passager.add(ajouterPassager);
		passager.add(afficherPassager);
		passager.add(rechercherPassager);
		
		this.info = new JMenu("?");
		this.aPropos = new JMenuItem("A propos");
		info.add(aPropos);
		
		this.add(programme);
		this.add(avion);
		this.add(vol);
		this.add(passager);
		this.add(info);
		
		index.addActionListener(new ActionMenu("index", f, p));
		quitter.addActionListener(new ActionMenu("quitter", f, p));
		ajouterAvion.addActionListener(new ActionMenu("ajouterAvion", f, p));
		afficherAvion.addActionListener(new ActionMenu("afficherAvion", f, p));
		retirerAvion.addActionListener(new ActionMenu("retirerAvion", f, p));
		ajouterVol.addActionListener(new ActionMenu("ajouterVol", f, p));
		afficherVol.addActionListener(new ActionMenu("afficherVol", f, p));
		retirerVol.addActionListener(new ActionMenu("retirerVol", f, p));
		ajouterPassager.addActionListener(new ActionMenu("ajouterPassager", f, p));
		afficherPassager.addActionListener(new ActionMenu("afficherPassager", f, p));
		rechercherPassager.addActionListener(new ActionMenu("rechercherPassager", f, p));
		aPropos.addActionListener(new ActionMenu("aPropos", f, p));
		
	}
	
}
