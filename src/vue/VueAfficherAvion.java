package vue;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.*;

import objet.*;


public class VueAfficherAvion extends JPanel {
	
	// Attributs priv�s
	private JLabel lblTitre;
	private JTable tableau;
	private JScrollPane scroll;
	private JLabel lblNbAvion;
	
	public VueAfficherAvion (ArrayList<Avion> lesAvions) {
		
		this.setLayout(new FlowLayout());
		
		Dimension taille = new Dimension(300, 20);
		lblTitre = new JLabel("Liste des avions", JLabel.CENTER);
		lblTitre.setPreferredSize(taille);
		this.add(lblTitre);
		
		Object data[][] = new Object[lesAvions.size()][3];
		int i = 0;
		for (Avion a : lesAvions) {
			data[i][0] = a.getNum();
			data[i][1] = a.getNom();
			data[i][2] = a.getNbPlace();
			i++;
		}
		String[] title = {"Num�ro", "Nom", "Places"};
		this.tableau = new JTable(data, title);
		if (lesAvions.size() < 10) {
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
		this.scroll = new JScrollPane(this.tableau);
		this.add(this.scroll);
		
		lblNbAvion = new JLabel("Nombre total d'avions : " + lesAvions.size());
		lblNbAvion.setPreferredSize(taille);
		this.add(lblNbAvion);
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
