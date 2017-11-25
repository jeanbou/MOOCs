package com.voiture.engine;

public enum TypeMoteur {
	
	// Objects directly constructed	
	DIESEL ("Moteur DIESEL"),
	ESSENCE ("Moteur ESSENCE"),
	HYBRIDE ("Moteur HYBRIDE"),
	ELECTRIQUE ("Moteur ELECTRIQUE");
	
	private String typeNom = "";
	
	// Constructor	
	TypeMoteur(String typeNom){
		this.typeNom = typeNom;
	}
	
	// Convert to string
	public String toString(){
		return typeNom;
	}
}
