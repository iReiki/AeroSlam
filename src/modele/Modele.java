package modele;

import java.sql.*;
import java.util.ArrayList;

import objet.*;

public class Modele {
	
// Attributs privés
	private static Connection connexion;
	private static Statement st;
	private static PreparedStatement pst;
	private static ResultSet rs;
	
	// Méthodes statiques
	// Connexion à la BDD
	public static void connexionBDD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connexion = DriverManager.getConnection("jdbc:mysql://localhost/aeroslam", "root", "");
		} 
		catch (ClassNotFoundException e) {
			System.out.println("Driver non chargé" + e);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Déconnexion à la BDD
	public static void deconnexionBDD() {
		try {
			connexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// Vérification connexion admin
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
	
	// Ajouter un avion
	public static void ajouterAvion(String unNom, int unNbPlace) {
		connexionBDD();
		try {
			String req = "INSERT INTO Avion (nomAv, nbPlace ) VALUES (?, ?)";
			pst = connexion.prepareStatement(req);
			pst.setString(1, unNom);
			pst.setInt(2, unNbPlace);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
	// Obtenir la liste des avions
	public static ArrayList<Avion> getLesAvions() {
		connexionBDD();
		ArrayList<Avion> lesAvions = new ArrayList<Avion>();
	    try {  
			String req = "SELECT * FROM Avion";
	    	st = connexion.createStatement();
			rs = st.executeQuery(req);
			while(rs.next()) {
				Avion unAvion = new Avion(rs.getInt(1), rs.getString(2), rs.getInt(3));
				lesAvions.add(unAvion);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesAvions; 
	}
	
	// Retirer un avion
	public static void retirerAvion(int unNum) {
		connexionBDD();
		try {
			String req = "DELETE FROM Avion WHERE numAv = ?)";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, unNum);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
}
