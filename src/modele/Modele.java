package modele;
import java.sql.*;

public class Modele {
	
// Attributs priv�s
	private static Connection connexion;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	// M�thodes statiques
	// Connexion � la BDD
	public static void connexionBDD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/aeroslam", "root", "");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver non charg�" + e);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// D�connexion � la BDD
	public static void deconnexionBDD() {
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// V�rification connexion admin
	public static boolean connexionAdmin(String login, String mdp) {
		boolean trouver = false;
		connexionBDD();
		try {
			String req = "SELECT COUNT('login') FROM Administrateur WHERE login = ? AND Mdp = ?";
			pst = connexion.prepareStatement(req);
			pst.setString(1, login);
			pst.setString(2, mdp);
			rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) == 1) {
				trouver = true;
			}
			rs.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return trouver;
	}
}
