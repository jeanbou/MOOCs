package com.voiture.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoteurEssence extends Moteur implements Serializable {

	// Constructor's specificity
	public MoteurEssence(String cylinder, Double prix) {
		super(TypeMoteur.ESSENCE, cylinder, prix);
	}
	// IB: Another constructor without cylinder, because in example we have no cylinder on the screen
	public MoteurEssence(Double prix) {
		// Set the type of the principal engine and then run abstract class constructor
		super(TypeMoteur.ESSENCE, "NO INFO ON Cylinders", prix);		
	}

	@Override
	// More simple in comparison to hybrid engine, but still specificity to implement
	public String toString() {
		return (TypeMoteur.ESSENCE.toString()+" "+ // Than's why we use enum
					this.getCylinder()+ " ("+
					this.getPrix().toString()+"ˆ) ");
	}
	
}
