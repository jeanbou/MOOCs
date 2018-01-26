package fr.ocr.sql;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import javax.swing.JCheckBox;
import javax.swing.JTable;

import fr.ocr.sql.DAOException;
import fr.ocr.ihm.ButtonEditor;
import fr.ocr.ihm.ButtonRenderer;
import fr.ocr.ihm.listener.ViewDetailVehiculeListener;

public class ResultSet2JTableConverter {
	
	public static int dbID = 0;
	final private static int indexVehiculeIDColumn = 4;
	
	public static JTable getJTable(ResultSet tableDataResult, DatabaseTable tableType) {		
				
		ResultSetMetaData resultMeta;
		try {
			resultMeta = tableDataResult.getMetaData();
			int nbreColumn = resultMeta.getColumnCount();
			
			tableDataResult.last();
			int nbreRow = tableDataResult.getRow();
			
			tableDataResult.beforeFirst();

			String[] title = new String[nbreColumn];
			Object[][] data = new Object[nbreRow][nbreColumn];

			// IB: to add extra column for Vehicule table, special treatment proposed by prof., no changes here
			if (tableType.equals(DatabaseTable.VEHICULE)) {
				title = new String[nbreColumn + 2];
				title[nbreColumn] = "DETAIL";
				title[nbreColumn + 1] = "ACTION";

				data = new Object[nbreRow][nbreColumn + 2];
				for (Object[] rows : data) {
					rows[nbreColumn] = "DETAIL";
					rows[nbreColumn + 1] = "SUPPRIMER";
				}
			}
			
			for (int i = 0; i < nbreColumn; i++) {
					title[i] = resultMeta.getColumnName(i + 1).toUpperCase();
			}
			
			int nbreLine = 0;
				
			while (tableDataResult.next()) {
				for (int i = 0; i < nbreColumn; i++) {
					data[nbreLine][i] = tableDataResult.getObject(i + 1).toString();
				}
				nbreLine++;
			}
			
			final JTable tab = new JTable(data, title);
			
			// IB: To show delete button only for Vehicule table, original code from prof. No changes here
			if (tableType.equals(DatabaseTable.VEHICULE)) {
				tab.getColumn("ACTION").setCellRenderer(new ButtonRenderer("SUPPRIMER"));
				tab.getColumn("ACTION").setCellEditor(new ButtonEditor(new JCheckBox(), "SUPPRIMER"));
				tab.getColumn("DETAIL").setCellRenderer(new ButtonRenderer("DETAIL"));
				
				tab.addMouseListener(new java.awt.event.MouseAdapter() {
				    @Override
				    public void mousePressed(java.awt.event.MouseEvent evt) {
				    	// IB: Obtain Vehicule ID from main table when you click on button
				    	dbID = Integer.parseInt(tab.getValueAt(tab.rowAtPoint(evt.getPoint()), indexVehiculeIDColumn).toString().trim());
				    	tab.getColumn("DETAIL").setCellEditor(new ButtonEditor(new JCheckBox(), "DETAIL", new ViewDetailVehiculeListener()));
				    }			   
				});					
			}
			
			tab.setRowHeight(30);
			
			return tab;
			
		}
		catch (SQLException e) {
			DAOException().console("Error with the SQLData obtained by ResultSet2TableConverted to convert it\nTable cannot be created");
			e.printStackTrace();
			return null;
		}		
		
	}
	
	private static DAOException DAOException() {
		return null;
	}

}
