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
	
	//----------------- AVION --------------------
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
			rs.close();
			st.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesAvions; 
	}
	
	// Obtenir un avion
	public static Avion getUnAvion(int unNum) {
		connexionBDD();
		Avion lAvion = new Avion();
		try {
			String req = "SELECT * FROM Avion WHERE numAv = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, unNum);
			rs = pst.executeQuery();
			rs.next();
			lAvion = new Avion(rs.getInt(1), rs.getString(2), rs.getInt(3));
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lAvion;
	}	
	
	// Retirer un avion
	public static void retirerAvion(int unNum) {
		connexionBDD();
		try {
			String req = "DELETE FROM Avion WHERE numAv = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, unNum);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
	// Nombre d'avions
	public static int getNbAvions() {
		return Modele.getLesAvions().size();
	}
	
	//---------------- DESTINATION --------------------
	// Obtenir les destinations
	public static ArrayList<Destination> getLesDestinations() {
		connexionBDD();
		ArrayList<Destination> lesDestinations = new ArrayList<Destination>();
	    try {  
			String req = "SELECT * FROM Destination";
	    	st = connexion.createStatement();
			rs = st.executeQuery(req);
			while(rs.next()) {
				Destination uneDestination = new Destination(rs.getInt(1), rs.getString(2), rs.getString(3));
				lesDestinations.add(uneDestination);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesDestinations; 
	}
	
}
