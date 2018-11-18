package controleur;

import java.awt.CardLayout;
import java.awt.event.*;
import javax.swing.*;

import objet.*;

public class ActionSelectionTypeVol implements ItemListener{
	
	private JComboBox listeType;
	private JPanel panel;
	
	public ActionSelectionTypeVol(JComboBox liste, JPanel p) {
		this.listeType = liste;
		this.panel = p;
	}
	
	public void itemStateChanged(ItemEvent i) {
		CardLayout cl = (CardLayout)(panel.getLayout());
	    cl.show(panel, (String)i.getItem());
	}
	
}
