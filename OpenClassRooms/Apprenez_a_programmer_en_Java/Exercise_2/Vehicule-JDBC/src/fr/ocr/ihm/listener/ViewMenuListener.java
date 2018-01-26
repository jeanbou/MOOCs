package fr.ocr.ihm.listener;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import fr.ocr.sql.DAOFactory;
import fr.ocr.sql.DatabaseTable;
import fr.ocr.sql.ResultSet2JTableConverter;

public class ViewMenuListener implements ActionListener {

	private ResultSet table;
	private DatabaseTable tableType;
	private JFrame frame;
	
	private DatabaseTable getDataType() {
		return this.tableType;
	}
	
	private void setTable(DatabaseTable tableType) {
		this.table = DAOFactory.getTable(tableType);
	}

	public ViewMenuListener(JFrame frame, DatabaseTable tableType) {
		this.tableType = tableType;
		this.frame = frame;
		setTable(tableType);	
	}
	
	public void actionPerformed(ActionEvent e) {
		// IB: Call via DAO the very last table and update it
		setTable(getDataType());
		frame.getContentPane().removeAll();
		frame.getContentPane().add(	new JScrollPane(ResultSet2JTableConverter.getJTable(table,tableType)),BorderLayout.CENTER);
		frame.getContentPane().revalidate();		
	}

}
