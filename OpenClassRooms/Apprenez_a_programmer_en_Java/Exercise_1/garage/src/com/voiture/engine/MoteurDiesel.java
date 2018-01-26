package com.voiture.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoteurDiesel extends Moteur implements Serializable {

	// Constructor's specificity, because we implement Diesel engine
	public MoteurDiesel(String cylinder, Double prix) {
		super(TypeMoteur.DIESEL, cylinder, prix);
	}
	public MoteurDiesel(Double prix) {
		// Set the type of the principal engine and then run abstract class constructor
		super(TypeMoteur.DIESEL, "NO INFO ON Cylinders", prix);		
	}

	@Override
	// More simple in comparison to hybrid engine, but still specificity to implement
	public String toString() {
		return (TypeMoteur.DIESEL.toString()+" "+ // Than's why we use enum
					this.getCylinder()+ " ("+
					this.getPrix().toString()+"ˆ) ");
	}

}
