package vue;
import javax.swing.*;

public class Vue extends JFrame {

	private JPanel panel;
	
	public Vue() {
	
		this.setTitle("AeroSlam");
	
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 600);
		this.setResizable(false);	
		
		panel = new VueConnexion(this);
		
		this.getContentPane().add(panel);
		
		this.setLocationRelativeTo(null);
		this.setVisible(true); // Si problème, le mettre dans controleur
	}
}
