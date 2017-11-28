package com.subtitlor.dao;

import java.util.ArrayList;
import java.util.List;

import com.subtitlor.bean.TradFileRecord;
import com.subtitlor.utilities.CustomizedString;

public interface FileDao {

	boolean ajouter(ArrayList<TradFileRecord> listRecords) throws DaoException;

	ArrayList<TradFileRecord> lister() throws DaoException;

	public void deleteTable() throws DaoException;
	
	public void deleteTable(String filename) throws DaoException;
	
	public void updateTradFileRecord(List<TradFileRecord> list) throws DaoException;
	
	public int recupererNbRecords() throws DaoException; 
	
	public ArrayList<CustomizedString> retrieveDbSubtitlesFiles() throws DaoException;

	ArrayList<TradFileRecord> lister(String filename) throws DaoException;

}
