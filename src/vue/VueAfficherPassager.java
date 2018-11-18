package vue;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.Dimension;
import java.util.*;

import objet.*;
import controleur.*;

public class VueAfficherPassager extends JFrame {
	
	// Attributs priv�s
	private JPanel panel;
	private JTable tableau;
	private JScrollPane scroll;
	private JLabel lblNbP;
	private JButton btnRetirer;
	
	public VueAfficherPassager(ArrayList<Passager> lesP, int numVol) {
	
		this.setTitle("Liste de passagers");
	
		this.setSize(500, 500);
		this.setResizable(false);	
		
		panel = new JPanel();
		
		Dimension taille = new Dimension(300, 20);
		
		Object data[][] = new Object[lesP.size()][3];
		int i = 0;
		for (Passager p : lesP) {
			data[i][0] = p.getNum();
			data[i][1] = p.getNom();
			data[i][2] = p.getPrenom();
			i++;
		}
		String[] title = {"Num�ro", "Nom", "Pr�nom"};
		this.tableau = new JTable(data, title);
		if (lesP.size() < 20) {
			this.tableau.setPreferredScrollableViewportSize(new Dimension(500, 16*i));
		}
		else {
			this.tableau.setPreferredScrollableViewportSize(new Dimension(500, 320));
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
		panel.add(this.scroll);
		
		lblNbP = new JLabel("Nombre de passagers dans le vol : " + lesP.size());
		lblNbP.setPreferredSize(taille);
		panel.add(lblNbP);
		
		btnRetirer = new JButton("Annuler la r�servation");
		panel.add(btnRetirer);
		
		btnRetirer.addActionListener(new ActionRetirerPassager(this.tableau, numVol));
		
		this.getContentPane().add(panel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true);
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
