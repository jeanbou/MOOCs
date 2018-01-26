package fr.ocr.ihm;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

@SuppressWarnings("serial")
public class EmptyFieldException extends Exception {
	
	@SuppressWarnings("unused")
	final private String byDefaultString = "Champs obligatoire manquant"; // IB: Just to store proposed by Prof by default value
	@SuppressWarnings("unused")
	private String aConstructorsStr = new String("");
	
	// IB: Because all message
	public EmptyFieldException(String err) { // IB: Because the reality of development the product requires high variability of pop-ups, it's quite doubtful that you have some holy ideal constructor that will work for all, so I left it empty and develop 3 of mine
		super(err);
		this.aConstructorsStr = err; // IB: Just to store it if one day we will need a general error message for all; never know!
	}
	
	@SuppressWarnings("static-access")
	public void infoMsgWindow(String anInfoMesStr) {
		JOptionPane jop = new JOptionPane();
		ImageIcon img = new ImageIcon("hsqldb/doc/images/db/tip.png");
		jop.setBackground(Color.white);
		jop.showMessageDialog(null, anInfoMesStr, "Information",JOptionPane.INFORMATION_MESSAGE, img);			
	}
	
	@SuppressWarnings("static-access")
	public void errorMsgWindow(String anErroMesStr) {
		JOptionPane jop = new JOptionPane();
		ImageIcon img = new ImageIcon("hsqldb/doc/images/db/warning.png");
		jop.setBackground(Color.white);
		jop.showMessageDialog(null, anErroMesStr, "Erreur",JOptionPane.ERROR_MESSAGE, img);
	}
	
	@SuppressWarnings("static-access")
	public void warningMsgWindow(String anWarningMesStr) {
		JOptionPane jop = new JOptionPane();
		ImageIcon img = new ImageIcon("hsqldb/doc/images/db/important.png");
		jop.setBackground(Color.white);
		jop.showMessageDialog(null, anWarningMesStr, "Information",JOptionPane.WARNING_MESSAGE, img);			
	}
}
