package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import voiture.option.Option;


public class OptionDAO extends DAO<Option> {
	
	public OptionDAO(Connection conn) {
	    super(conn);
	  }

	  public boolean create(Option obj) {
	    return false;
	  }

	  public boolean delete(Option obj) {
	    return false;
	  }
	   
	  public boolean update(Option obj) {
	    return false;
	  }
	   
	  public Option find(int id) {
		  
		  Option option = new Option();      
	      
		  try {
			  ResultSet result = this.connect.createStatement(
					  				ResultSet.TYPE_SCROLL_INSENSITIVE,
					  				ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM OPTION WHERE id = " + id);
			  if(result.first())
				  option = new Option(id,result.getString("DESCRIPTION"),result.getDouble("PRIX"));         
	    }
		catch (SQLException e)
		{
	      e.printStackTrace();
	    }
		  
	    return option;
	  }	  
	  
}
