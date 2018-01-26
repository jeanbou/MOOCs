package com.voiture.devices;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Climatisation implements Option, Serializable {
	// Constant price of this device
	final private Double prix = 347.3;
	final private String optionName = "Climatisation";
	
	@Override
	public Double getPrix() {		
		return prix;
	}
	
	public String getOptionName() {		
		return optionName;
	}

}
