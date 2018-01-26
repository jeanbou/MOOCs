package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import fr.ocr.ihm.EmptyFieldException;
import fr.ocr.sql.DAOException;
import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.DatabaseTable;

import voiture.Marque;
import voiture.Vehicule;
import voiture.moteur.Moteur;
import voiture.moteur.TypeMoteur;
import voiture.option.Option;

public class NewVehiculeListener implements ActionListener {

	private JFrame frame;
	
	public NewVehiculeListener(JFrame frame) {
		this.frame = frame;
	}		
			
	@SuppressWarnings("serial")
	private class YDialog extends JDialog {
		
		private final static String allMarqueTypeDBColumnAssesor = "NOM";  
		private final static String allTypeMoteurDBColumnAssesor = "DESCRIPTION";
		private final static String allTypeMoteurCyliderDBColumnAssesor = "CYLINDRE";
		private final static String allTypeMoteurCyliderDBIDColumnAssesor = "MOTEUR";
		private final static String allOptionDBColumnAssesor = "DESCRIPTION";

		// IB: Regular expression solution to detect Double String Field without dropping an exception
		private static final String regexDecimal = "^-?\\d*\\.\\d+$";
		private static final String regexInteger = "^-?\\d+$";
		private static final String decimalPattern = regexDecimal + "|" + regexInteger;
		// IB: Error messages requested by Prof.
		private static final String nomErrorMessegeStr = "Le champ \'nom\' est obligatoire!";
		private static final String prixFormatErrorMessegeStr = "Le champ \'prix\' est mal saisir!";
		private static final String prixErrorMessegeStr = "Le champ \'prix\' est obligatoire!";
		private static final String infoMsgOK = "Vos précieuses données sont bien sauvegardes dans notre base de données\nAppuyez Ctrt+Maj(Shift)+V SVP pour actualiser la table VEHICULE ou utlisez SVP le menu\nMerci!";
		private static final String infoMsgKO = "Impossible d\'enregistrer l\'objet Véhicule dans notre base de données!";
		
		private JTextField nomDuVehicule;
		@SuppressWarnings("rawtypes")
		private JComboBox comboMarque;
		private ArrayList<String> allMarkCarList = new ArrayList<String>();
		@SuppressWarnings("rawtypes")
		private JComboBox comboMarqueType; 
		private ArrayList<String> allTypeMoteurList = new ArrayList<String>();
		private ArrayList<String> allTypeMoteurCyliderList = new ArrayList<String>();
		private JTextField prixSanOption;
		private ArrayList<JCheckBox> allCarOptionCheckBoxList = new ArrayList<JCheckBox>();

		private DAOException DAOException() {
			return null;
		}
		
		// IB: I create a separate validation method in order to be ready in case if the name of the car must be more complex that what you see
		// IB: This method has nothing common with exception, or of one wants beauty structure, so the class of validators must be created and then.... You know, the same things as with except
		private boolean validateCarName(String aCarName) {
			
			boolean isCarNameValide = false;
			
			// IB: Simple rule, but probably one day it becomes complex
			isCarNameValide = ( (aCarName != null) && (!aCarName.isEmpty()) && (aCarName.length() > 0) ); // IB: One day the Prof. will ask to increase the size of name of car, so here to change
			
			return isCarNameValide;
		}
				
		@SuppressWarnings("unchecked")
		private void processDialogEnteredDataForming() {
			
			Vehicule aCarFilledByUser = new Vehicule();
			
			// IB: Car name
			String aCarNameStr = nomDuVehicule.getText().trim();
			boolean isCarNameOK = validateCarName(aCarNameStr);
									
			if (isCarNameOK) {
				aCarFilledByUser.setNom(aCarNameStr.toUpperCase()); // IB: In case if user put it in low case, I convert it to more common upper case, in order to follow given standard (You noted that it's all Uper case right?
			}
			else
			{
				nomDuVehicule.setText(""); // IB: If some smart guy put spaces or other dirty stuff instead of right text, clean his "smartness" out!
				EmptyFieldException aWarningExp = new EmptyFieldException("");
				aWarningExp.warningMsgWindow(nomErrorMessegeStr); // IB: Not valid show modal message error
			}
			
			// IB: Car type (mark)
			String aCarMarkChossenStr= comboMarque.getSelectedItem().toString().trim();
			// IB: Idea is simple because in the list is form from table MARQUE and because each mark corresponds to Id starting from zero, such simple solution works
			int idOfCarMark = 0;
			for (; idOfCarMark < allMarkCarList.size(); idOfCarMark++) {
				if (allMarkCarList.get(idOfCarMark).equals(aCarMarkChossenStr)) {
					break; // IB: id has found, no need to continue run the cycle, be efficient!
				}
			}
			// IB: Now we have the id, we can construct the object Marque
			Marque aCarMarque = new Marque(idOfCarMark,aCarMarkChossenStr);
			aCarFilledByUser.setMarque(aCarMarque);
			
			// IB: engine type (mark)
			// IB: Same idea for getting the TypeMotor ID that corresponds to ID of TYPE_MOTEUR table
			String aComplexEngineTypeChossenStr= comboMarqueType.getSelectedItem().toString().trim();
			int idOfEngineType = 0;
			for (; idOfEngineType < allMarkCarList.size(); idOfEngineType++) {
				if ( aComplexEngineTypeChossenStr.contains(allTypeMoteurList.get(idOfEngineType))) {
					break; // IB: id has found, no need to continue run the cycle, be efficient!
				}
			}
			// IB: Now we have the id, we can construct type engine
			TypeMoteur aTypeMot = new TypeMoteur(idOfEngineType,allTypeMoteurList.get(idOfEngineType));
			int idOfEngine = 0;
			for (; idOfEngine < allTypeMoteurCyliderList.size(); idOfEngine++) {
				if ( aComplexEngineTypeChossenStr.contains(allTypeMoteurCyliderList.get(idOfEngine))) {
					break; // IB: id has found, no need to continue run the cycle, be efficient!
				}
			}
			Moteur foundPrototyprOfMoteur = (Moteur) DAOFactory.getMoteurDAO().find(idOfEngine);
			// IB: foundPrototyprOfMoteur.getCylindre() is identical to allTypeMoteurCyliderList.get(idOfEngine), but in a future one can easily replace it
			// IB: Note, that here we set the price as a search result (in order to stay consistent) from the table MOTEUR, but having such constructor and constructing in such way we can in a future set new price
			Moteur mot = new Moteur(idOfEngine,aTypeMot,foundPrototyprOfMoteur.getCylindre(),foundPrototyprOfMoteur.getPrix());
			aCarFilledByUser.setMoteur(mot);
			
			// IB: Set inserted basic price
			String aBasicCarPriceStr = prixSanOption.getText().trim();
			boolean isBasicCarPriceOK = validateCarName(aBasicCarPriceStr);
			// IB: and now the trick, I can use the same method for car to check that price field is field with something!
			if (isBasicCarPriceOK) {
				isBasicCarPriceOK = Pattern.matches(decimalPattern, aBasicCarPriceStr);
				if (isBasicCarPriceOK) { // IB: A solution to detect correct double value as string without dropping an exception
					// IB: Now check the converting Str to Double is possible
					aCarFilledByUser.setPrix(Double.parseDouble(aBasicCarPriceStr));
				}
				else
				{
					prixSanOption.setText(""); // IB: Clean out from dirty stuff
					EmptyFieldException aErrWindExp = new EmptyFieldException("");
					aErrWindExp.errorMsgWindow(prixFormatErrorMessegeStr);
				}				
			}
			else
			{
				if (isCarNameOK) { // IB: No need to show the error message when the problem is still with the car name
					prixSanOption.setText(""); // IB: clean out from spaces
					EmptyFieldException aWarnWindExp = new EmptyFieldException("");
					aWarnWindExp.warningMsgWindow(prixErrorMessegeStr); // IB: Trick, use the same method to show another error text
				}
			}
			
			// IB: Option list, the same trick: we constructed the list from DB SQL request for VEHICULE_OPTION table so now we easy know the id of each option 
			List<Option> listOfOptions = new ArrayList<Option>(); // If Car doesn't have option, it will be indicator
			boolean isAtLeastOneOptionAdded = false;
			for (int i = 0; i < allCarOptionCheckBoxList.size(); i++) {
				if (allCarOptionCheckBoxList.get(i).isSelected() ) {
					// IB: The same approach as for the complex Moteur type object, assuming that the table is source somehow const table, but in a future if one wants to add more functionality, he has easy possibility to scale such solution
					Option foundPrototyprOfOptionI = (Option) DAOFactory.getOptionDAO().find(i);
					// IB: if you want to increase functionality then use the constructor Option anOption = ...
					isAtLeastOneOptionAdded = true;
					// IB: Otherwise here you have an option
					listOfOptions.add(foundPrototyprOfOptionI);
				}
			}
			if (isAtLeastOneOptionAdded) { // if an one option exist to add, so add it, otherwise do nothing
				aCarFilledByUser.setListOptions(listOfOptions);				
			}
			
			// IB: The Car Object is Filled, try to saved it in DB & close the window
			if (isCarNameOK && isBasicCarPriceOK && DAOFactory.getVehiculeDAO().update(aCarFilledByUser)) {
				dispose(); // IB: Everything fine, close the add vehicule window
				EmptyFieldException anInfoPopUp = new EmptyFieldException(""); 
				anInfoPopUp.infoMsgWindow(infoMsgOK);
			}	
			else
			{
				if (isCarNameOK && isBasicCarPriceOK) { // IB: We show DB saving error only if all data given by user is OK, otherwise no need to provide extra pop-up
					EmptyFieldException anErrorPopUp = new EmptyFieldException("");
					anErrorPopUp.errorMsgWindow(infoMsgKO);
				}
			}			
		}		
	
		public YDialog(JFrame parent, String title, boolean modal) {			
			super(parent, title, modal);
			this.setSize(750, 340);
			this.setLocationRelativeTo(null);
			this.setResizable(false);
			this.initComponent();
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void formCarMarkDropListFromSQLData() {
			comboMarque = new JComboBox();
			comboMarque.setPreferredSize(new Dimension(100, 20));
			ResultSet allMarques = (ResultSet) DAOFactory.getTable(DatabaseTable.MARQUE);
			try {
				while ( allMarques.next() ) {
					String aCarMarStr = allMarques.getString(allMarqueTypeDBColumnAssesor).toString().trim();
					comboMarque.addItem(aCarMarStr);
					allMarkCarList.add(aCarMarStr);
				}
			}
			catch (SQLException eSQL) {
				DAOException().console("For SQLData of Drop List Forming");
				eSQL.printStackTrace();
			}
			allMarkCarList.trimToSize();
		}
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		private void formEgineTypeWithSubOptionCominationFromSQLData() {
			comboMarqueType = new JComboBox();
			comboMarqueType.setPreferredSize(new Dimension(200, 20));
			ResultSet allTypeMoteur = (ResultSet) DAOFactory.getTable(DatabaseTable.TYPEMOTEUR);
			ResultSet allTypeMoteurCylinder = (ResultSet) DAOFactory.getTable(DatabaseTable.MOTEUR);
			try {
				while ( allTypeMoteur.next() ) {
					String aTypeMotorStr = allTypeMoteur.getString(allTypeMoteurDBColumnAssesor).toString().trim();
					allTypeMoteurList.add(aTypeMotorStr); // IB: Store the list of possible options locally
				}
				allTypeMoteurList.trimToSize();
			
				while ( allTypeMoteurCylinder.next() ) {
					String aTypeMotorCylinderStr = allTypeMoteurCylinder.getString(allTypeMoteurCyliderDBColumnAssesor).toString().trim();
					Integer aTypeMotorCylinderIDInteger = allTypeMoteurCylinder.getInt(allTypeMoteurCyliderDBIDColumnAssesor);
					allTypeMoteurCyliderList.add(aTypeMotorCylinderStr);
					String aFormOfComboOptionStr = allTypeMoteurList.get(aTypeMotorCylinderIDInteger.intValue()).toString().trim()+" "+aTypeMotorCylinderStr;
					comboMarqueType.addItem(aFormOfComboOptionStr);
				}
				allTypeMoteurCyliderList.trimToSize();				
			}
			catch (SQLException eSQL) {
				DAOException().console("For SQLData set for Options");
				eSQL.printStackTrace();				
			}
		}
			
		private void formCarOptionListFromSQLDate() {
			ResultSet allCarExtraOptions = (ResultSet) DAOFactory.getTable(DatabaseTable.OPTION);
			try {
				while ( allCarExtraOptions.next() ) {
					String anOptionForCarStr = allCarExtraOptions.getString(allOptionDBColumnAssesor).toString().trim(); 
					allCarOptionCheckBoxList.add(new JCheckBox(anOptionForCarStr));
				}
			}
			catch (SQLException eSQL) {
				DAOException().console("For SQLData set for Options (Main List)");
				eSQL.printStackTrace();
			}
			allCarOptionCheckBoxList.trimToSize();
		}
		
		private void initComponent() {		
			
			// Car			
			JPanel vehicule = new JPanel();
			vehicule.setBackground(Color.white);
			vehicule.setPreferredSize(new Dimension(330, 60));
			nomDuVehicule = new JTextField();
			nomDuVehicule.setPreferredSize(new Dimension(150, 25));
			nomDuVehicule.setText("");
			nomDuVehicule.setEditable(true);			
			JLabel nomDuVehiculeLabel = new JLabel("Saisir un nom :");
			vehicule.setBorder(BorderFactory.createTitledBorder("Nom du véhicule"));
			vehicule.add(nomDuVehiculeLabel);
			vehicule.add(nomDuVehicule);
			
			// Marque
			JPanel marqueDuVehicule = new JPanel();
			marqueDuVehicule.setBackground(Color.white);
			marqueDuVehicule.setPreferredSize(new Dimension(330, 60));	
			JLabel marqueLabel = new JLabel("Marque :");
			formCarMarkDropListFromSQLData();
			marqueDuVehicule.setBorder(BorderFactory.createTitledBorder("Marque du véhicule"));
			marqueDuVehicule.add(marqueLabel);
			marqueDuVehicule.add(comboMarque);
			
			// Type of the engine with all possible sub-option in one sublist
			JPanel typeDeMoteur = new JPanel();
			typeDeMoteur.setBackground(Color.white);
			typeDeMoteur.setPreferredSize(new Dimension(500, 60));
			formEgineTypeWithSubOptionCominationFromSQLData();
			typeDeMoteur.setBorder(BorderFactory.createTitledBorder("Type de moteur du véhicule"));
			JLabel marqueTypeLabel = new JLabel("Marque :");
			typeDeMoteur.add(marqueTypeLabel);
			typeDeMoteur.add(comboMarqueType);
			
			// Price without options (Basic car price)
			JPanel voitureNetPrix = new JPanel();
			voitureNetPrix.setBackground(Color.white);
			voitureNetPrix.setPreferredSize(new Dimension(330, 60));
			prixSanOption = new JTextField();
			prixSanOption.setPreferredSize(new Dimension(200, 25));
			prixSanOption.setText("");
			prixSanOption.setEditable(true);
			voitureNetPrix.setBorder(BorderFactory.createTitledBorder("Prix du véhicule"));
			JLabel prixLabel = new JLabel("Prix :");
			voitureNetPrix.add(prixLabel);
			voitureNetPrix.add(prixSanOption);
			
			// IB: List of options			
			JPanel optionsList = new JPanel();
			optionsList.setBackground(Color.white);
			optionsList.setPreferredSize(new Dimension(720, 60));
			optionsList.setBorder(BorderFactory.createTitledBorder("Options Dinsponibles"));
			formCarOptionListFromSQLDate();
			// IB: now we know the size and can construct the list of options (checkboxes);
			for (int i = 0; i < allCarOptionCheckBoxList.size(); i++) {
				optionsList.add(allCarOptionCheckBoxList.get(i));
			}
			
			// IB: Panel with two button OK & Cancel & Two click classic embedded methods			
			JPanel buttonPanel = new JPanel();			
			JButton buttonOk = new JButton("OK");
			buttonOk.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			    	processDialogEnteredDataForming();
			    }			   
			});			
			
			// IB: button with simple action of closing
			JButton buttonCancel = new JButton("Annuler");
			buttonCancel.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			    	dispose();
			    }			   
			});
			
			buttonPanel.setBackground(Color.white);
			buttonPanel.setPreferredSize(new Dimension(600, 60));
			buttonPanel.add(buttonOk);
			buttonPanel.add(buttonCancel);			
			
			JPanel content = new JPanel();
			content.setBackground(Color.white);
			content.add(vehicule);
			content.add(marqueDuVehicule);
			content.add(typeDeMoteur);
			content.add(voitureNetPrix);
			content.add(optionsList);			
			content.add(buttonPanel);
			
			this.getContentPane().add(content, BorderLayout.CENTER);
		}
	}

	
	public void actionPerformed(ActionEvent e) {
		YDialog yd = new YDialog(this.frame, "Ajouter un nouveau véhicule", true);
		yd.setVisible(true);
	}
	
}
