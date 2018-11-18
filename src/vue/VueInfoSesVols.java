package vue;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import modele.Modele;
import objet.*;

public class VueInfoSesVols extends JPanel {

	public VueInfoSesVols() {
		
	}
	
	public void addLesInfos(ArrayList<VolCourrier> sesVols) {
		this.removeAll();
		
		JTable tableau;
		JScrollPane scroll;
		JLabel lblNbVol;
		
		this.setLayout(new GridLayout(3,1));
		
		Dimension taille = new Dimension(300, 20);
		
		Object data[][] = new Object[sesVols.size()][4];
		int i = 0;
		for (Vol v : sesVols) {
			data[i][0] = v.getNum();
			data[i][1] = v.getDate().getDateFrancais();
			data[i][2] = v.getUneDestination().getVille();
			data[i][3] = v.getUnAvion().getNom();
			i++;
		}
		String[] title = {"Numéro", "Date", "Destination", "Avion"};
		tableau = new JTable(data, title);
		if (sesVols.size() < 10) {
			tableau.setPreferredScrollableViewportSize(new Dimension(600, 16*i));
		}
		else {
			tableau.setPreferredScrollableViewportSize(new Dimension(600, 160));
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(data, title) { // Création d'un nouveau modèle pour éditer la méthode
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		tableau.setModel(tableModel);
		centrerTable(tableau);
		// Pour empêcher de pouvoir déplacer les colonnes
		tableau.getTableHeader().setReorderingAllowed(false);
		// Pour empêcher de pouvoir modifier la taille des colonnes
		tableau.getTableHeader().setResizingAllowed(false);
		scroll = new JScrollPane(tableau);
		this.add(scroll);
		
		lblNbVol = new JLabel("Nombre total de ses vols : " + sesVols.size(), JLabel.CENTER);
		lblNbVol.setPreferredSize(taille);
		this.add(lblNbVol);
		this.revalidate();
		
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
