package fr.ocr.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import fr.ocr.sql.DAOException;

public class DAOFactory {
	  
	  protected static final Connection conn = HsqldbConnection.getInstance();   
	   
	  @SuppressWarnings("rawtypes")
	  public static DAO getMarqueDAO(){
	    return new MarqueDAO(conn);
	  }

	  @SuppressWarnings("rawtypes")
	  public static DAO getVehiculeDAO(){
	    return new VehiculeDAO(conn);
	  }

	  @SuppressWarnings("rawtypes")
	  public static DAO getMoteurDAO(){
	    return new MoteurDAO(conn);
	  }
	  
	  @SuppressWarnings("rawtypes")
	  public static DAO getOptionDAO(){
	    return new OptionDAO(conn);
	  }
	  
	  @SuppressWarnings("rawtypes")
	  public static DAO getMoteurTypeDAO(){
		    return new TypeMoteurDAO(conn);
	 }
	  	  
	  public static ResultSet getTable(DatabaseTable tableType) {
		  
			ResultSet result = null;   
		    
			try {
				// IB: Here I use by default profs request without changes, yes it is possible to do in a better way, please check mine request for saving and delete data
		    	result = DAOFactory.conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
		    												// IB: Thanks to the Prof. who organized DatabaseTable enum is a way that its' string value is identical to table name, so we can use the following generalization
		    												ResultSet.CONCUR_READ_ONLY).
		    												executeQuery("SELECT * FROM " + tableType.toString());	               
		    }
			catch (SQLException e)
			{
				DAOException().console("Trouble with SQL retriving request for the table : "+tableType.toString());
				e.printStackTrace();
		    }
		    
		    return result;

		  } 
	  
	private static DAOException DAOException() {
		return null;
	}

}
