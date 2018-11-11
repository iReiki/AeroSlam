package vue;

import javax.swing.JOptionPane;

public class VuePopup extends JOptionPane {
	
	public void addPopErreur(String message) {
		this.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
	}
	
	public void addPopMessage(String message) {
		this.showMessageDialog(null, message, "Information", JOptionPane.INFORMATION_MESSAGE);
	}
	
	public int addPopChoix(String message, String entete) {
		int option = this.showConfirmDialog(null, message, entete, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		return option;
	}
	
	public boolean choixEstOk(int choix) {
		if (choix == JOptionPane.OK_OPTION) {
			return true;
		}
		else {
			return false;
		}
	}
}
