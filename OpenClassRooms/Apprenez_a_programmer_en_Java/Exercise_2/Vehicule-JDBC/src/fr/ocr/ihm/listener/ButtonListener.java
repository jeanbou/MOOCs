package fr.ocr.ihm.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;

import fr.ocr.ihm.EmptyFieldException;
import fr.ocr.sql.DAOFactory;
import voiture.Vehicule;

//Notre listener pour le bouton
public class ButtonListener implements ActionListener {
	protected int column, row, id;
	protected JTable table;
	
	private static int dbIDForDataDeliting = 0;
	final private static int indexVehiculeIDColumn = 4;
	private static final String infoMsgOK = "La voiture a été bien supprimé!\nAppuyez Ctrt+Maj(Shift)+V SVP pour actualiser la table VEHICULE ou utlisez SVP le menu\nMerci!";
	private static final String infoMsgKO = "La voiture n\'été pas supprimé\nMalheuresement, c\'est developeur qui doit regler le probleme!";
	
	public void setColumn(int col) {
		this.column = col;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent event) {
		// IB: Here we get the id of vehicule from VEHICULE table using specificity of pressed button and the organization of the table
		dbIDForDataDeliting = Integer.parseInt(table.getValueAt(((JButton) event.getSource()).getY()/((JButton) event.getSource()).getHeight(), indexVehiculeIDColumn).toString().trim());
    	Vehicule aCar2Delete = (Vehicule) DAOFactory.getVehiculeDAO().find(dbIDForDataDeliting);
    	if (DAOFactory.getVehiculeDAO().delete(aCar2Delete)) // IB: Delete and based on the result inform user
    	{
    		EmptyFieldException aErrWindExp = new EmptyFieldException("");
    		aErrWindExp.infoMsgWindow(infoMsgOK);
    	}
    	else
    	{
    		EmptyFieldException aInfoWindExp = new EmptyFieldException("");
    		aInfoWindExp.errorMsgWindow(infoMsgKO);
    	}			
	}	
	
}