package com.subtitlor.dao;


public class DAOConfigurationException extends Exception {

	private static final long serialVersionUID = 1L;

	public DAOConfigurationException(String message) {
        super(message);
   }
	
	public DAOConfigurationException (String messag, Exception exception) {
		super();
	}

}
