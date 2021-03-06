package vue;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.*;

import controleur.*;
import modele.Modele;
import objet.*;


public class VueAfficherVolCourrier extends JPanel {
	
	// Attributs priv�s
	
	private JPanel panelGrid;
	private JTable tableau;
	private JScrollPane scroll;
	private JPanel panel;
	private JLabel lblNbVol;
	private JButton btnAfficher;
	private JButton btnReserver;
	private JPanel panelReservation;
	private JLabel lblTitreR;
	private JLabel lblPassager;
	private JComboBox listePassager;
	
	public VueAfficherVolCourrier (ArrayList<VolCourrier> lesVols, ArrayList<Passager> lesP) {
		
		this.setLayout(new GridLayout(3,1));
		
		panelGrid = new JPanel(new GridLayout(2,1));
		
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
		String[] title = {"Num�ro", "Date", "Destination", "Avion", "Nombre de places"};
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
		lblNbVol = new JLabel("Nombre total de vols courriers : " + lesVols.size(), JLabel.CENTER);
		lblNbVol.setPreferredSize(taille);
		btnAfficher = new JButton("Afficher les passagers");
		btnReserver = new JButton("R�server un vol");
		panel.add(lblNbVol);
		panel.add(btnAfficher);
		panel.add(btnReserver);
		this.add(panel);
		
		//this.add(panelGrid);
		
		panelReservation = new JPanel();
		lblPassager = new JLabel("S�lection d'un passager :");
		listePassager = new JComboBox();
		for(Passager p : lesP) {
			listePassager.addItem(p.getNom() + " " + p.getPrenom());
		}
		panelReservation.add(lblPassager);
		panelReservation.add(listePassager);
		this.add(panelReservation);
		
		btnAfficher.addActionListener(new ActionAfficherPassager(this.tableau));
		btnReserver.addActionListener(new ActionReserverVol(this.tableau, this.listePassager));
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
