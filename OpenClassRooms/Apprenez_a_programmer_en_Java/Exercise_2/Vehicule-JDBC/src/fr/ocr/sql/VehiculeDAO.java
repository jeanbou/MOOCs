package fr.ocr.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import voiture.Marque;
import voiture.moteur.Moteur;
import voiture.option.Option;
import voiture.Vehicule;


public class VehiculeDAO extends DAO<Vehicule> {
	
	public VehiculeDAO(Connection conn) {
	    super(conn);
	  }

	  public boolean update(Vehicule objVehicule) {
		  
		  boolean isTransactionAccompliched = false;
		
		  // IB: As Prof. asked to do ... here is I am citing On démarre notre transction
		  try {
			  this.connect.setAutoCommit(false);
			  ResultSet nextID = this.connect.prepareStatement("CALL NEXT VALUE FOR seq_vehicule_id").executeQuery();
			  if (nextID.next()) {
				  int uniqueNextIDForNewRecordInsertion = nextID.getInt(1);
				  String aSQLInsertionRequest = "INSERT INTO VEHICULE "+
						  						" (MARQUE, MOTEUR, PRIX, NOM, ID) VALUES "+
						  							" (?,?,?,?,?)"; // IB: 5 Params as in Prof's course, starting from 1
				  PreparedStatement aPreparedStatement = this.connect.prepareStatement(aSQLInsertionRequest);
				  // IB: Here we can't automate the things, because 1) DB Structure (no one knows in advanced from your code) that's why DAOFactory exists 2) No one knows the type of your objects that must be casted in advance, once more that's why DAoFactory way of organizing the things exists
				  aPreparedStatement.setInt(1,objVehicule.getMarque().getId());
				  aPreparedStatement.setInt(2,objVehicule.getMoteur().getId());
				  aPreparedStatement.setDouble(3,objVehicule.getPrix()); // IB: Basic prices
				  aPreparedStatement.setString(4,objVehicule.getNom());
				  aPreparedStatement.setInt(5,uniqueNextIDForNewRecordInsertion);
				  aPreparedStatement.executeUpdate();
				  // IB: Now we need to save the options in the DB at OPTION table associated with vehicule
				  if (!objVehicule.getOptions().isEmpty() ) { // IB: We have something to store
					  List<Option> aCarsOptionsList = new ArrayList<Option>();
					  aCarsOptionsList = objVehicule.getOptions();
					  for (Option anOption : aCarsOptionsList) {
						   String aSQLInsertionOptionRequest = "INSERT INTO VEHICULE_OPTION "+
			  													" (ID_VEHICULE, ID_OPTION) VALUES "+
			  													" (?,?)";
						  PreparedStatement aPreparedOptInstStatement = this.connect.prepareStatement(aSQLInsertionOptionRequest);
						  aPreparedOptInstStatement.setInt(1,uniqueNextIDForNewRecordInsertion);
						  aPreparedOptInstStatement.setInt(2,anOption.getId());
						  aPreparedOptInstStatement.executeUpdate();						  
					  }
				  } // IB: The end of option insertion
			  }
			  this.connect.commit();
			  isTransactionAccompliched = true; // IB: Flag that everything is fine
		  }
		  catch (SQLException e) {
			  e.printStackTrace();
			  isTransactionAccompliched = false; // IB: Oops definitely we have an error
		  }
		  
		  
	    return isTransactionAccompliched;
	  }

	  public boolean delete(Vehicule objVehicule) {
		  
		  boolean isCarDelete = false;
		  
		  int principalID = objVehicule.getId(); // IB: That the only ID that we need to delete all data
		  try {
			  this.connect.setAutoCommit(false);
			  // IB: Delete from VEHICULE_OPTION table first, as requested by Prof.
			  String aSQLDeleteOptRequest = "DELETE FROM VEHICULE_OPTION WHERE ID_VEHICULE = ? ";
			  PreparedStatement aPreparedStatement = this.connect.prepareStatement(aSQLDeleteOptRequest);
			  aPreparedStatement.setInt(1,principalID);
			  aPreparedStatement.executeUpdate();
			  // IB: Delete from main VEHICULE
			  String aSQLDeleteCarMainRequest = "DELETE FROM VEHICULE WHERE ID = ?";
			  PreparedStatement aPreparedMainDelStatement = this.connect.prepareStatement(aSQLDeleteCarMainRequest);
			  aPreparedMainDelStatement.setInt(1,principalID);
			  aPreparedMainDelStatement.executeUpdate();
			  // Two transactions passed, so we considered it deleted
			  isCarDelete = true;
			  this.connect.commit();
		  }
		  catch (SQLException e) {
			isCarDelete = false;
			e.printStackTrace();
		  }
		  
		  return isCarDelete;
	  }
	   
	  public boolean create(Vehicule obj) {
	    return false;
	  }
	   
	  public Vehicule find(int id) { // IB: No options, here we use the Vehicule constructor that doesn't touch option
		  final int indexOfCarOptionsColumn = 2;
		  Vehicule vehicule = new Vehicule();      
		  
		  try {
			  	ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
			  													ResultSet.CONCUR_READ_ONLY).
			  													executeQuery("SELECT * FROM VEHICULE WHERE ID = " + id);
			  	
			  	if(result.first())
			  	{
			  		// IB: Vehicule contains of two objects, so we need to construct them = need to get them;
			  		
			  		// IB: Obtain marque object
			  		String marqueIDStr = result.getObject("MARQUE").toString();			  		
			  		Marque marque = (Marque) DAOFactory.getMarqueDAO().find(Integer.parseInt(marqueIDStr.trim()));
			  		
			  		// IB: Obtain Motor object
			  		String moteurIDStr = result.getObject("MOTEUR").toString();			  		
			  		Moteur moteur = (Moteur) DAOFactory.getMoteurDAO().find(Integer.parseInt(moteurIDStr.trim()));
			  		
			  		// IB: Obtain The List of Options, the problem is that table vehicule_option contains of ids of all options used by car, the goal is to extract it and construct the list
			  		List<Option> listOptions = new ArrayList<>();
			  		// IB: Need a separate request not related to Option
			  		ResultSet resultlistOfOptions = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
																					ResultSet.CONCUR_READ_ONLY).
																					executeQuery("SELECT * FROM VEHICULE_OPTION WHERE ID_VEHICULE = " + id);
			  		while(resultlistOfOptions.next()){
			  			String idOptionStr = resultlistOfOptions.getObject(indexOfCarOptionsColumn).toString().trim();
			  			int idOptionInt = Integer.parseInt(idOptionStr);
			  			// IB: and now only now we can use our OptionDAO to get more info about option property, set the complete object Option and Add it to ListOfOption
			  			Option anOption = (Option) DAOFactory.getOptionDAO().find(idOptionInt);
			  			listOptions.add(anOption);
			  		}
			  		// IB: list is constructed. Correct exit
			  		resultlistOfOptions.close();
			  		
			  		// IB: Now we can construct complex Vehicule object
			  		vehicule = new Vehicule(id,result.getString("NOM").toString(),
			  									marque,moteur,listOptions,
			  									result.getDouble("PRIX"));
	    			
		  		}
			  	// IB: Close connection to DB, correct work
			  	result.close();
			  	
		  }
		  catch (SQLException e) {
			  e.printStackTrace();
		  }
		  
		  return vehicule;
	  }

}
