package fr.ocr.sql;

import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class DAOException extends Exception {

	// IB: Here I left the original constructor from Prof. that I am not going to use, I prefer mine, text one for the cases of SQLData (DAO! in Fact, agreed?) related error
	@SuppressWarnings("static-access")
	public DAOException(String mess) {
		super(mess);
		JOptionPane jop = new JOptionPane();
		jop.showMessageDialog(null, mess, "Erreur de recherche dans le DAO",JOptionPane.ERROR_MESSAGE);
	}
	
	// IB: Empty constructor that I wanna use
	public DAOException() {
	}
	
	public void console(String errorMsgStr) {
		System.err.println("DAOExceltion error:\n\t\t"+errorMsgStr);
	}

}
