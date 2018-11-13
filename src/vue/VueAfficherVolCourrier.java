package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import modele.Modele;
import objet.*;


public class VueAfficherVolCourrier extends JPanel {
	
	// Attributs privés
	
	private JTable tableau;
	private JScrollPane scroll;
	private JLabel lblNbVol;
	
	public VueAfficherVolCourrier (ArrayList<VolCourrier> lesVols) {
		
		this.setLayout(new FlowLayout());
		
		Dimension taille = new Dimension(300, 20);
		
		Object data[][] = new Object[lesVols.size()][5];
		int i = 0;
		for (Vol v : lesVols) {
			int nbPlaceReserv = Modele.getNbPlacesReservees(v.getNum());
			data[i][0] = v.getNum();
			data[i][1] = v.getDate().getDateFrancais();
			data[i][2] = v.getUneDestination().getVille();
			data[i][3] = v.getUnAvion().getNom();
			data[i][4] = nbPlaceReserv + "/" + v.getUnAvion().getNbPlace();
			i++;
		}
		String[] title = {"Numéro", "Date", "Destination", "Avion", "Nombre de places"};
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
		this.scroll = new JScrollPane(this.tableau);
		this.add(this.scroll);
		
		lblNbVol = new JLabel("Nombre total de vols : " + lesVols.size());
		lblNbVol.setPreferredSize(taille);
		this.add(lblNbVol);
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
