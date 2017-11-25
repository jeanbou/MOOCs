package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Marque;
import voiture.moteur.Moteur;
import voiture.Vehicule;


public class VehiculeDAO extends DAO<Vehicule> {
	
	public VehiculeDAO(Connection conn) {
	    super(conn);
	  }

	  public boolean create(Vehicule obj) {
	    return false;
	  }

	  public boolean delete(Vehicule obj) {
	    return false;
	  }
	   
	  public boolean update(Vehicule obj) {
	    return false;
	  }
	   
	  public Vehicule find(int id) { // IB: No options, here we use the Vehicule constructor that doesn't touch option
		  
		  Vehicule vehicule = new Vehicule();      
		  
		  try {
			  	ResultSet result = this.connect.createStatement(
			  	ResultSet.TYPE_SCROLL_INSENSITIVE,
			  	ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM VEHICULE WHERE ID = " + id);
			  	
			  	if(result.first())
			  		vehicule = new Vehicule(id,result.getString("NOM"),
			  									(Marque) result.getObject("MARQUE"),
			  									(Moteur) result.getObject("MOTEUR"),
			  									result.getDouble("PRIX")
	    			  						);         
		  }
		  catch (SQLException e) {
			  e.printStackTrace();
		  }
		  
		  return vehicule;
	  }

}
