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
	
	// Attributs priv�s
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
			String[] title = {"Num�ro", "Date", "Type du vol", "Destination", "Avion"};
			this.tableau = new JTable(data, title);
			if (lesVols.size() < 10) {
				this.tableau.setPreferredScrollableViewportSize(new Dimension(600, 16*i));
			}
			else {
				this.tableau.setPreferredScrollableViewportSize(new Dimension(600, 160));
			}
			
			DefaultTableModel tableModel = new DefaultTableModel(data, title) { // Cr�ation d'un nouveau mod�le pour �diter la m�thode
			    @Override
			    public boolean isCellEditable(int row, int column) {
			       return false;
			    }
			};
			this.tableau.setModel(tableModel);
			centrerTable(this.tableau);
			// Pour emp�cher de pouvoir d�placer les colonnes
			this.tableau.getTableHeader().setReorderingAllowed(false);
			// Pour emp�cher de pouvoir modifier la taille des colonnes
			this.tableau.getTableHeader().setResizingAllowed(false);
			// Pour emp�cher la s�lection de plusieurs lignes 
			this.tableau.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			this.scroll = new JScrollPane(this.tableau);
			this.add(this.scroll);
			
			
			btnSuppr = new JButton("Supprimer");
			btnSuppr.setPreferredSize(new Dimension(300,20));
			this.add(btnSuppr);
			
			btnSuppr.addActionListener(new ActionRetirerVol(this.tableau));
			
		}
		
		// M�thode pour centrer les chaines de caract�res dans le tableau
		private void centrerTable(JTable table) {     
			DefaultTableCellRenderer custom = new DefaultTableCellRenderer();
			custom.setHorizontalAlignment(JLabel.CENTER);
			for (int i=0 ; i<table.getColumnCount() ; i++) {
				table.getColumnModel().getColumn(i).setCellRenderer(custom);
			}
		}
	
}
