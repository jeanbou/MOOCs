package com.voiture.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoteurElectrique extends Moteur implements Serializable {

	// Constructor's specificity
	public MoteurElectrique(String cylinder, Double prix) {
		super(TypeMoteur.ELECTRIQUE, cylinder, prix);
	}
	
	public MoteurElectrique(Double prix) {
		// Set the type of the principal engine and then run abstract class constructor
		super(TypeMoteur.ELECTRIQUE, "NO INFO ON Cylinders", prix);		
	}
	
	@Override
	// More simple in comparison to hybrid engine, but still specificity to implement
	public String toString() {
		return (TypeMoteur.ELECTRIQUE.toString()+" "+ // Than's why we use enum
					this.getCylinder()+ " ("+
					this.getPrix().toString()+"ˆ) ");
	}
}
