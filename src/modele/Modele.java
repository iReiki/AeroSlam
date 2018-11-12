package modele;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

import objet.*;
import objet.Date;

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
	
	// Obtenir une destination
		public static Destination getUneDestination(int unNum) {
			connexionBDD();
			Destination uneDest = new Destination();
			try {
				String req = "SELECT * FROM Destination WHERE idDest = ?";
				pst = connexion.prepareStatement(req);
				pst.setInt(1, unNum);
				rs = pst.executeQuery();
				rs.next();
				uneDest = new Destination(rs.getInt(1), rs.getString(2), rs.getString(3));
				rs.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			deconnexionBDD();
			return uneDest;
		}
		
		public static Destination getUneDestination(String ville) {
			connexionBDD();
			Destination uneDest = new Destination();
			try {
				String req = "SELECT * FROM Destination WHERE villeDest = ?";
				pst = connexion.prepareStatement(req);
				pst.setString(1, ville);
				rs = pst.executeQuery();
				rs.next();
				uneDest = new Destination(rs.getInt(1), rs.getString(2), rs.getString(3));
				rs.close();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			deconnexionBDD();
			return uneDest;
		}
	
	//------------------ VOL ----------------------------
	// Ajouter un vol
		public static void ajouterVol(Date uneDate, int unType, int unIdDest, int unNumAv) {
			connexionBDD();
			try {
				String req = "INSERT INTO Vol (dateVol, typeVol, idDest, numAv ) VALUES (?, ?, ?, ?)";
				pst = connexion.prepareStatement(req);
				pst.setString(1, String.valueOf(uneDate.getDate()));
				pst.setInt(2, unType);
				pst.setInt(3, unIdDest);
				pst.setInt(4, unNumAv);
				pst.executeUpdate();
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			deconnexionBDD();
		}
		
		// Obtenir la liste des vols
		public static ArrayList<Vol> getLesVols() {
			connexionBDD();
			ArrayList<Vol> lesVols = new ArrayList<Vol>();
		    try {  
				String req = "SELECT * FROM Vol";
		    	st = connexion.createStatement();
				rs = st.executeQuery(req);
				while(rs.next()) {
					// Conversion de la date de String vers Date
					String date = rs.getString(2);
					int jour, mois, annee;
					jour = Integer.parseInt(date.substring(0, 2));
					mois = Integer.parseInt(date.substring(3, 5));
					annee = Integer.parseInt(date.substring(6, 10));
					Date laDate = new Date(LocalDate.of(annee, mois, jour));
					// Conversion de la destination de int vers Destination
					Destination laDest = getUneDestination(rs.getInt(4));
					// Conversion de l'avion de int vers Avion
					Avion lAvion = getUnAvion(rs.getInt(5));
					Vol unVol = new Vol(rs.getInt(1), laDate, rs.getInt(3), laDest , lAvion);
					lesVols.add(unVol);
				}
				rs.close();
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			deconnexionBDD();
			return lesVols; 
		}
}
