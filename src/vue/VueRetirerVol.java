package vue;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import objet.*;
import controleur.*;

public class VueRetirerVol extends JPanel {
	
	// Attributs privés
		private JLabel lblTitre;
		private JTable tableau;
		private JScrollPane scroll;
		private JButton btnSuppr;
		
		public VueRetirerVol (ArrayList<Vol> lesVols) {
			
			this.setLayout(new FlowLayout());
			
			lblTitre = new JLabel("Liste des vols", JLabel.CENTER);
			lblTitre.setPreferredSize(new Dimension(300,20));
			this.add(lblTitre);
			
			Object data[][] = new Object[lesVols.size()][5];
			int i = 0;
			for (Vol v : lesVols) {
				data[i][0] = v.getNum();
				data[i][1] = v.getDate().getDateFrancais();
				data[i][2] = v.getLibelleTypeVol(v.getTypeVol());
				data[i][3] = v.getUneDestination().getVille();
				data[i][4] = v.getUnAvion().getNom();
				i++;
			}
			String[] title = {"Numéro", "Date", "Type du vol", "Destination", "Avion"};
			this.tableau = new JTable(data, title);
			if (lesVols.size() < 10) {
				this.tableau.setPreferredScrollableViewportSize(new Dimension(600, 16*i));
			}
			else {
				this.tableau.setPreferredScrollableViewportSize(new Dimension(600, 160));
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
			// Pour empêcher la sélection de plusieurs lignes 
			this.tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.scroll = new JScrollPane(this.tableau);
			this.add(this.scroll);
			
			
			btnSuppr = new JButton("Supprimer");
			btnSuppr.setPreferredSize(new Dimension(300,20));
			this.add(btnSuppr);
			
			btnSuppr.addActionListener(new ActionRetirerVol(this.tableau));
			
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
