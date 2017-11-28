package fr.ocr.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HsqldbConnection {
	// URL de connexion
	private String url = "jdbc:hsqldb:file:hsqldb/database/VEHICULE";
	// Nom du user
	private String user = "SA";
	// Mot de passe de l'utilisateur
	private String passwd = "";
	// Objet Connection
	private static Connection connect;
	private static HsqldbConnection instance = new HsqldbConnection();

	// Constructeur priv�
	private HsqldbConnection() {
		try {
			connect = DriverManager.getConnection(url, user, passwd);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// M�thode d'acc�s au singleton
	public static Connection getInstance() {
		if (connect == null)
			instance = new HsqldbConnection();

		return connect;
	}
}
