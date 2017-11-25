package com.voiture.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MoteurHybride extends Moteur implements Serializable {

	// Because all Hybrid Engines is Electric + something, so electric part is always present in Hybride type as const. 
	final private TypeMoteur supMoteurType = TypeMoteur.ELECTRIQUE;	
	
	public MoteurHybride(TypeMoteur principMoteurType, String cylinder, Double prix) {
		// Set the type of the principal engine and then run abstract class constructor
		super(principMoteurType, cylinder, prix);		
	}
	// IB: Another constructor without cylinder, because in example we have no cylinder on the screen
	public MoteurHybride(TypeMoteur principMoteurType, Double prix) {
		// Set the type of the principal engine and then run abstract class constructor
		super(principMoteurType, "NO INFO ON Cylinders", prix);		
	}
	
	// Principal engine get, actually it is what was taken from abstract class moteur
	// More abstract organization of the code
	public TypeMoteur getPrincipMoteurType() {
		return super.getMoteurType();
	}
	
	// Similar we set (if needed) it via abstract class
	// Such organization allow us to be more abstract and flexible if it needs a change
	public void setPrincipMoteurType(TypeMoteur principMoteurType) {
		super.setMoteurType(principMoteurType);
	}
	
	// however it is constant, such way of organization can us be more flexible if new changes come
	// We can easy then change the  sup engine if it not electric
	public TypeMoteur getSupMoteurType() {
		return supMoteurType;
	}

	@Override
	// Because for HYBRID we have specific toString method, because of the 2 engines, but thanks to abstract class, we expect to define it in unique way
	public String toString() {
		return (TypeMoteur.HYBRIDE.toString()+" "+ // Than's why we use enum
					this.getPrincipMoteurType().toString()+"/"+this.getSupMoteurType().toString()+ // set two types of hybrid moteur
					" ("+this.getPrix().toString()+"ˆ) "); // Note: no power (because for hybrid it is so, see example in requirements) However, we have this data for principal engine
	}

}
