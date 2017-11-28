package com.garage.cars;

public enum Marque {

	// Objects directly constructed	
	RENAULT("Marque RENAULT"),
	PEGEUT("Marque PEGEUT"),
	CITROEN("Marque CITROEN");
	
	private String marqueNom = "";
	
	// Constructor	
	Marque(String marqueNom){
		this.marqueNom = marqueNom;
	}
	
	// Convert to string
	public String toString(){
		return marqueNom;
	}
}
