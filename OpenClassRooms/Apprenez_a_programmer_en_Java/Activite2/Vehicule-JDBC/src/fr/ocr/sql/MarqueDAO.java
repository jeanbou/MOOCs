package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.Marque;

public class MarqueDAO extends DAO<Marque> {
	
	public MarqueDAO(Connection conn) {
	    super(conn);
	  }

	  public boolean create(Marque obj) {
	    return false;
	  }

	  public boolean delete(Marque obj) {
	    return false;
	  }
	   
	  public boolean update(Marque obj) {
	    return false;
	  }
	   
	  public Marque find(int id) {
		  
		  Marque marque = new Marque();      
	      
		  try {
			  ResultSet result = this.connect.createStatement(
					  				ResultSet.TYPE_SCROLL_INSENSITIVE,
					  				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM MARQUE WHERE id = " + id);
			  if(result.first())
				  marque = new Marque(id,result.getString("nom"));         
	    }
		catch (SQLException e)
		{
	      e.printStackTrace();
	    }
		  
	    return marque;
	  }	  
	  
}
