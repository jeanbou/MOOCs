package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAOFactory {
	  
	  protected static final Connection conn = HsqldbConnection.getInstance();   
	   
	  public static DAO getMarqueDAO(){
	    return new MarqueDAO(conn);
	  }

	  public static DAO getVehiculeDAO(){
	    return new VehiculeDAO(conn);
	  }

	  public static DAO getMoteurDAO(){
	    return new MoteurDAO(conn);
	  }

	  
	  public static DAO getOptionDAO(){
	    return new OptionDAO(conn);
	  }
	  
	  public static DAO getMoteurTypeDAO(){
		    return new TypeMoteurDAO(conn);
		  }
	  	  
	  public static ResultSet getTable(DatabaseTable tableType) {
		  
			ResultSet result = null;   
		    
			try {
		    	result = DAOFactory.conn.createStatement(
		        ResultSet.TYPE_SCROLL_INSENSITIVE,
		        // IB: Thanks to the Prof. who organized DatabaseTable enum is a way that its' string value is identical to table name, so we can use the following generalization
		        ResultSet.CONCUR_READ_ONLY).executeQuery("SELECT * FROM " + tableType.toString());	               
		    }
			catch (SQLException e)
			{
		      e.printStackTrace();
		    }
		    
		    return result;

		  } 
}
