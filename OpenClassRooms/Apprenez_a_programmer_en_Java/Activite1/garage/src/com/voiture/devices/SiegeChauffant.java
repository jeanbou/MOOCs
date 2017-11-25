package com.voiture.devices;

import java.io.Serializable;

@SuppressWarnings("serial")
public class SiegeChauffant implements Option, Serializable  {
	// Constant price of this device
	final private Double prix = 562.9; 
	final private String optionName = "Siege chauffant";
	
	@Override
	public Double getPrix() {		
		return prix;
	}
	
	public String getOptionName() {		
		return optionName;
	}
}
