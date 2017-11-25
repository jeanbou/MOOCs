package com.voiture.devices;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GPS implements Option, Serializable  {
	// Constant price of this device
	final private Double prix = 113.5;
	final private String optionName = "GPS";
	
	@Override
	public Double getPrix() {		
		return prix;
	}
	
	public String getOptionName() {		
		return optionName;
	}

}
