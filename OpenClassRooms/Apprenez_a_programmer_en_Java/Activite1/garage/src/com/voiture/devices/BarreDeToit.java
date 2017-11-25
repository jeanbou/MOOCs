package com.voiture.devices;

import java.io.Serializable;

@SuppressWarnings("serial")
public class BarreDeToit implements Option, Serializable {
	// Constant price of this device
	final private Double prix = 29.9; 
	final private String optionName = "Barre de toit";
	
	@Override
	public Double getPrix() {		
		return prix;
	}
	
	public String getOptionName() {		
		return optionName;
	}

}