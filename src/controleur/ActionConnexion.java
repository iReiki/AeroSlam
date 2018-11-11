package controleur;
import modele.*;
import vue.*;
import java.awt.event.*;
import javax.swing.*;

public class ActionConnexion implements ActionListener {
	
	private JTextField jtfLogin;
	private JPasswordField jtfMdp;
	private JPanel panel;
	private JFrame fenetre;
	
	public ActionConnexion (JTextField leLogin, JPasswordField leMdp, JPanel p,JFrame f) {
		this.jtfLogin = leLogin;
		this.jtfMdp = leMdp;
		this.panel = p;
		this.fenetre = f;
	}
	
	public void actionPerformed(ActionEvent e) {
		String login = this.jtfLogin.getText();
		String mdp = this.jtfMdp.getText();
		VuePopup pop = new VuePopup();
		if(Modele.connexionAdmin(login, mdp)) {
			pop.addPopMessage("Connexion réussie.");
			this.panel.removeAll();
			this.fenetre.setContentPane(new VueAccueil(this.fenetre));
			this.fenetre.getContentPane().revalidate();
		}
		else {
			pop.addPopErreur("Le login ou le mot de passe est incorrect !");
		}
	}
}
