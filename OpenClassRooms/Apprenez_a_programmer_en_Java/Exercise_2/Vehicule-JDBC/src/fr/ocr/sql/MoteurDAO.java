package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;

public class MoteurDAO extends DAO<Moteur> {
	
	public MoteurDAO(Connection conn) {
	    super(conn);
	  }

	  public boolean create(Moteur obj) {
	    return false;
	  }

	  public boolean delete(Moteur obj) {
	    return false;
	  }
	   
	  public boolean update(Moteur obj) {
	    return false;
	  }
	   
	  public Moteur find(int id) {
		  
		  Moteur moteur = new Moteur();      
	      
		  try {
			  ResultSet result = this.connect.createStatement(
					  				ResultSet.TYPE_SCROLL_INSENSITIVE,
					  				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM MOTEUR WHERE id = " + id);
			  if(result.first()) {
				  // IB: Moteur contains of object type of moteur, so first you need to find it
				  String moteurIDStr = result.getObject("MOTEUR").toString();			  		
				  TypeMoteur moteurType = (TypeMoteur) DAOFactory.getMoteurTypeDAO().find(Integer.parseInt(moteurIDStr.trim()));
			  		
				  // IB: I have all data to construct Object Moteur
				  moteur = new Moteur(id,moteurType,
						  				result.getString("CYLINDRE"),
						  				result.getDouble("PRIX"));			     

		  		}
	    }
		catch (SQLException e)
		{
	      e.printStackTrace();
	    }
		  
	    return moteur;
	  }	  
	  
}

