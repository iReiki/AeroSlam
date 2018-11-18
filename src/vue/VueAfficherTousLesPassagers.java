package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import objet.*;


public class VueAfficherTousLesPassagers extends JPanel {
	
	// Attributs privés
	private JLabel lblTitre;
	private JTable tableau;
	private JScrollPane scroll;
	private JLabel lblNbP;
	
	public VueAfficherTousLesPassagers (ArrayList<Passager> lesP) {
		
		this.setLayout(new FlowLayout());
		
		Dimension taille = new Dimension(300, 20);
		lblTitre = new JLabel("Liste de tous les passagers", JLabel.CENTER);
		lblTitre.setPreferredSize(taille);
		this.add(lblTitre);
		
		Object data[][] = new Object[lesP.size()][6];
		int i = 0;
		for (Passager p : lesP) {
			data[i][0] = p.getNum();
			data[i][1] = p.getNom();
			data[i][2] = p.getPrenom();
			data[i][3] = p.getRue();
			data[i][4] = p.getCp();
			data[i][5] = p.getVille();
			i++;
		}
		String[] title = {"Numéro", "Nom", "Prénom", "Rue", "Code Postal", "Ville"};
		this.tableau = new JTable(data, title);
		if (lesP.size() < 10) {
			this.tableau.setPreferredScrollableViewportSize(new Dimension(800, 16*i));
		}
		else {
			this.tableau.setPreferredScrollableViewportSize(new Dimension(800, 160));
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, title) { // Création d'un nouveau modèle pour éditer la méthode
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		this.tableau.setModel(tableModel);
		centrerTable(this.tableau);
		// Pour empêcher de pouvoir déplacer les colonnes
		this.tableau.getTableHeader().setReorderingAllowed(false);
		// Pour empêcher de pouvoir modifier la taille des colonnes
		this.tableau.getTableHeader().setResizingAllowed(false);
		this.scroll = new JScrollPane(this.tableau);
		this.add(this.scroll);
		
		lblNbP = new JLabel("Nombre total de passagers : " + lesP.size());
		lblNbP.setPreferredSize(taille);
		this.add(lblNbP);
	}
	
	// Méthode pour centrer les chaines de caractères dans le tableau
	private void centrerTable(JTable table) {     
		DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
		custom.setHorizontalAlignment(JLabel.CENTER);
		for (int i=0 ; i<table.getColumnCount() ; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(custom);
		}
	}
}
