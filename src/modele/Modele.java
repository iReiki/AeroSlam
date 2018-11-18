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
	
	// Obtenir si l'avion a un vol
	public static boolean possedeUnVol(int unNum) {
		boolean trouver = false;
		connexionBDD();
		try {
			String req = "SELECT COUNT(numVol) FROM Vol WHERE numAv = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, unNum);
			rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) > 0) {
				trouver = true;
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return trouver;
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
	
	// Obtenir les infos de la destination selon sa ville
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
	    	Statement stm = connexion.createStatement();
			ResultSet rst = stm.executeQuery(req);
			while(rst.next()) {
				// Conversion de la date de String vers Date
				String date = rst.getString(2);
				int jour, mois, annee;
				annee = Integer.parseInt(date.substring(0, 4));
				mois = Integer.parseInt(date.substring(5, 7));
				jour = Integer.parseInt(date.substring(8, 10));
				Date laDate = new Date(LocalDate.of(annee, mois, jour));
				// Conversion de la destination de int vers Destination
				Destination laDest = getUneDestination(rst.getInt(4));
				// Conversion de l'avion de int vers Avion
				Avion avion = getUnAvion(rst.getInt(5));
				int type = rst.getInt(3);
				Vol unVol;
				if (type == 0) {
					unVol = new VolCommercial(rst.getInt(1), laDate, type, laDest, avion);
				}
				else {
					unVol = new VolCourrier(rst.getInt(1), laDate, type, laDest, avion);
				}
				lesVols.add(unVol);
			}
			rst.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesVols; 
	}
	
	// Obtenir la liste des vols courriers
	public static ArrayList<VolCourrier> getLesVolCourriers() {
		connexionBDD();
		ArrayList<VolCourrier> lesVols = new ArrayList<VolCourrier>();
	    try {  
			String req = "SELECT * FROM Vol WHERE typeVol = 1";
			Statement stm = connexion.createStatement();
			ResultSet rst = stm.executeQuery(req);
			while(rst.next()) {
				// Conversion de la date de String vers Date
				String date = rst.getString(2);
				int jour, mois, annee;
				annee = Integer.parseInt(date.substring(0, 4));
				mois = Integer.parseInt(date.substring(5, 7));
				jour = Integer.parseInt(date.substring(8, 10));
				Date laDate = new Date(LocalDate.of(annee, mois, jour));
				// Conversion de la destination de int vers Destination
				Destination laDest = getUneDestination(rst.getInt(4));
				// Conversion de l'avion de int vers Avion
				Avion lAvion = getUnAvion(rst.getInt(5));
				VolCourrier unVol = new VolCourrier(rst.getInt(1), laDate, rst.getInt(3), laDest , lAvion);
				lesVols.add(unVol);
			}
			rst.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesVols; 
	}
	
	// Obtenir la liste des vols commerciaux
	public static ArrayList<VolCommercial> getLesVolCommerciaux() {
		connexionBDD();
		ArrayList<VolCommercial> lesVols = new ArrayList<VolCommercial>();
	    try {  
			String req = "SELECT * FROM Vol WHERE typeVol = 0";
			Statement stm = connexion.createStatement();
			ResultSet rst = stm.executeQuery(req);
			while(rst.next()) {
				// Conversion de la date de String vers Date
				String date = rst.getString(2);
				int jour, mois, annee;
				annee = Integer.parseInt(date.substring(0, 4));
				mois = Integer.parseInt(date.substring(5, 7));
				jour = Integer.parseInt(date.substring(8, 10));
				Date laDate = new Date(LocalDate.of(annee, mois, jour));
				// Conversion de la destination de int vers Destination
				Destination laDest = getUneDestination(rst.getInt(4));
				// Conversion de l'avion de int vers Avion
				Avion lAvion = getUnAvion(rst.getInt(5));
				VolCommercial unVol = new VolCommercial(rst.getInt(1), laDate, rst.getInt(3), laDest , lAvion);
				lesVols.add(unVol);
			}
			rst.close();
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesVols; 
	}
	
	// Obtenir la liste des vols courriers
	public static VolCourrier getUnVolCourrier(int unNum) {
		connexionBDD();
		VolCourrier leVol = null;
	    try {  
			String req = "SELECT * FROM Vol WHERE typeVol = 1 AND numVol = ?";
			PreparedStatement psts = connexion.prepareStatement(req);
			psts.setInt(1, unNum);
			ResultSet rst = psts.executeQuery();
			rst.next();
			// Conversion de la date de String vers Date
			String date = rst.getString(2);
			int jour, mois, annee;
			annee = Integer.parseInt(date.substring(0, 4));
			mois = Integer.parseInt(date.substring(5, 7));
			jour = Integer.parseInt(date.substring(8, 10));
			Date laDate = new Date(LocalDate.of(annee, mois, jour));
			// Conversion de la destination de int vers Destination
			Destination laDest = getUneDestination(rst.getInt(4));
			// Conversion de l'avion de int vers Avion
			Avion lAvion = getUnAvion(rst.getInt(5));
			leVol = new VolCourrier(rst.getInt(1), laDate, rst.getInt(3), laDest , lAvion);
			psts.close();
			rst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return leVol; 
	}
	
	// Retire un vol
	public static void retirerVol(int numV) {
		connexionBDD();
		try {
			String req = "DELETE FROM Vol WHERE numVol = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, numV);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
	// Vol est vide ou non
	public static boolean volEstVide(int numV) {
		connexionBDD();
		boolean trouver = false;
		try {
			String req = "SELECT COUNT(numP) FROM Enregistrer WHERE numVol = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, numV);
			rs = pst.executeQuery();
			rs.next();
			if (rs.getInt(1) < 1) {
				trouver = true;
			}
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return trouver;
	}
	
	// Obtenir les passagers d'un vol donné
	public static ArrayList<Passager> getLesPassagers(int numV) {
		connexionBDD();
		ArrayList<Passager> lesPassagers = new ArrayList<Passager>();
	    try {  
			String req = "SELECT * FROM Passager, Enregistrer WHERE Passager.numP = Enregistrer.numP AND numVol = ?";
	    	pst = connexion.prepareStatement(req);
	    	pst.setInt(1, numV);
			rs = pst.executeQuery();
			while(rs.next()) {
				Passager unPassager = new Passager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
				lesPassagers.add(unPassager);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesPassagers; 
	}
	
	// -------------------- ENREGISTRER ------------------------
	// Réserver un vol
	public static void reserverVol(int numVol, int numP) {
		connexionBDD();
		try {
			String req = "INSERT INTO Enregistrer (numVol, numP) VALUES (?,?)";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, numVol);
			pst.setInt(2, numP);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
	// Passager déjà ajouté 
	public static boolean passagerEstAjoute(int numVol, int numP) {
		connexionBDD();
		boolean trouver = false;
		try {
			String req = "SELECT * FROM Enregistrer WHERE numVol = ? AND numP = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, numVol);
			pst.setInt(2, numP);
			rs = pst.executeQuery();
			if (rs.next()) {
				trouver = true;
			}
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return trouver;
	}
	
	// Annuler la réservation d'un vol
	public static void annulerReservation(int numVol, int numP) {
		connexionBDD();
		try {
			String req = "DELETE FROM Enregistrer WHERE numVol = ? AND numP = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, numVol);
			pst.setInt(2, numP);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
		
	//---------------------- PASSAGER ---------------------------------
	// Ajouter un passager
	public static void ajouterPassager(String unNom, String unPrenom, String uneRue, int unCp, String uneVille) {
		connexionBDD();
		try {
			String req = "INSERT INTO Passager (nomP, prenomP, rueP, cpP, villeP) VALUES (?, ?, ?, ?, ?)";
			pst = connexion.prepareStatement(req);
			pst.setString(1, unNom.toUpperCase());
			pst.setString(2, unPrenom);
			pst.setString(3, uneRue);
			pst.setInt(4, unCp);
			pst.setString(5, uneVille);
			pst.executeUpdate();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
	}
	
	// Rechercher un passager
	public static Passager rechercherUnPassager(String nom, String prenom) {
		connexionBDD();
		Passager lePassager = null;
		try {
			String req = "SELECT * FROM Passager WHERE nomP = ? AND prenomP = ?";
			pst = connexion.prepareStatement(req);
			pst.setString(1, nom.toUpperCase());
			pst.setString(2, prenom);
			rs = pst.executeQuery();
			if (rs.next()) {
				lePassager = new Passager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
			}		
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lePassager;
	}
	
	// Obtenir un passager
	public static Passager getUnPassager(int unNum) {
		connexionBDD();
		Passager lePassager = null;
		try {
			String req = "SELECT * FROM Passager WHERE numP = ?";
			pst = connexion.prepareStatement(req);
			pst.setInt(1, unNum);
			rs = pst.executeQuery();
			rs.next();
			lePassager = new Passager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));		
			pst.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lePassager;
	}

	// Obtenir tous les passagers
	public static ArrayList<Passager> getTousLesPassagers() {
		connexionBDD();
		ArrayList<Passager> lesPassagers = new ArrayList<Passager>();
	    try {  
			String req = "SELECT * FROM Passager";
	    	st = connexion.createStatement();
			rs = st.executeQuery(req);
			while(rs.next()) {
				Passager unPassager = new Passager(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(6));
				lesPassagers.add(unPassager);
			}
			st.close();
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesPassagers; 
	}
	
	// Obtenir le nombre de places réservées d'un vol
	public static int getNbPlacesReservees(int numV) {
		connexionBDD();
		int nb = 0;
	    try {  
			String req = "SELECT COUNT(numP) FROM Enregistrer WHERE numVol = ?";
	    	pst = connexion.prepareStatement(req);
	    	pst.setInt(1, numV);
			rs = pst.executeQuery();
			rs.next();
			nb = rs.getInt(1);
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return nb; 
	}
	
	// Obtenir la liste des vols d'un passager donné
	public static ArrayList<VolCourrier> getSesVols(int numP) {
		connexionBDD();
		ArrayList<VolCourrier> lesVols = new ArrayList<VolCourrier>();
	    try {  
			String req = "SELECT * FROM Vol, Enregistrer WHERE Vol.numVol = Enregistrer.numVol AND typeVol = 1 AND numP = ?";
			PreparedStatement pstt = connexion.prepareStatement(req);
			pstt.setInt(1, numP);
			ResultSet rst = pstt.executeQuery();
			while(rst.next()) {
				// Conversion de la date de String vers Date
				String date = rst.getString(2);
				int jour, mois, annee;
				annee = Integer.parseInt(date.substring(0, 4));
				mois = Integer.parseInt(date.substring(5, 7));
				jour = Integer.parseInt(date.substring(8, 10));
				Date laDate = new Date(LocalDate.of(annee, mois, jour));
				// Conversion de la destination de int vers Destination
				Destination laDest = getUneDestination(rst.getInt(4));
				// Conversion de l'avion de int vers Avion
				Avion lAvion = getUnAvion(rst.getInt(5));
				VolCourrier unVol = new VolCourrier(rst.getInt(1), laDate, rst.getInt(3), laDest , lAvion);
				lesVols.add(unVol);
			}
			rst.close();
			pstt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		deconnexionBDD();
		return lesVols; 
	}
	
	
}
