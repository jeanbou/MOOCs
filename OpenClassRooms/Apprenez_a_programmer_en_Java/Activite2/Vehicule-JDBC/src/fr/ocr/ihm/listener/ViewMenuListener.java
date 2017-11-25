package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.TableFactory;

public class ViewMenuListener implements ActionListener {

	private ResultSet table;
	private DatabaseTable tableType;
	private JFrame frame;	

	public ViewMenuListener(JFrame frame, DatabaseTable tableType) {
		this.tableType = tableType;
		this.frame = frame;
		this.table = DAOFactory.getTable(tableType);		
	}
	
	public void actionPerformed(ActionEvent e) {
		
		try {
			frame.getContentPane().removeAll();
			frame.getContentPane().add(	new JScrollPane(TableFactory.getJTable(table,tableType)),BorderLayout.CENTER);
			frame.getContentPane().revalidate();
		}
		catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}	

	}

}
