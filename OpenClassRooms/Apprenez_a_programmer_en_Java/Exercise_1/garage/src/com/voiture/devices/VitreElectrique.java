package com.voiture.devices;

import java.io.Serializable;

@SuppressWarnings("serial")
public class VitreElectrique implements Option, Serializable  {
	// Constant price of this device
	final private Double prix = 212.35;  
	final private String optionName = "Vitre electrique";
	
	@Override
	public Double getPrix() {		
		return prix;
	}
	
	public String getOptionName() {		
		return optionName;
	}
}
