package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import objet.*;


public class VueAfficherVolCommercial extends JPanel {
	
	// Attributs priv�s
	
	private JTable tableau;
	private JScrollPane scroll;
	private JPanel panel;
	private JLabel lblNbVol;
	
	public VueAfficherVolCommercial (ArrayList<VolCommercial> lesVols) {
		
		this.setLayout(new GridLayout(2,1));
		
		Dimension taille = new Dimension(300, 20);
		
		Object data[][] = new Object[lesVols.size()][4];
		int i = 0;
		for (Vol v : lesVols) {
			data[i][0] = v.getNum();
			data[i][1] = v.getDate().getDateFrancais();
			data[i][2] = v.getUneDestination().getVille();
			data[i][3] = v.getUnAvion().getNom();
			i++;
		}
		String[] title = {"Num�ro", "Date", "Destination", "Avion"};
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
		this.scroll = new JScrollPane(this.tableau);
		this.add(this.scroll);
		
		panel = new JPanel();
		lblNbVol = new JLabel("Nombre total de vols commerciaux : " + lesVols.size(), JLabel.CENTER);
		lblNbVol.setPreferredSize(taille);
		panel.add(lblNbVol);
		this.add(panel);
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
