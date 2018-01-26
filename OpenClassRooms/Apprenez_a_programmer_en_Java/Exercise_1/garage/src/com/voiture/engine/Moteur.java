package com.voiture.engine;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Moteur implements Serializable {
	
	private TypeMoteur moteurType;
	private String cylinder;
	private Double prix;

	// Customized constructor + always with engine.
	public Moteur(TypeMoteur moteurType, String cylinder, Double prix) {
		this.moteurType = moteurType;
		this.cylinder = cylinder;
		this.prix = prix;
	}

	// geter & seter for prix
	public Double getPrix() {
		return prix;
	}

	public void setPrix(Double prix) {
		this.prix = prix;
	}

	// geter & seter for engine type (sounds crazy, to change type of engine without change of price and cylinder, but well it will be handle by developer)
	public TypeMoteur getMoteurType() {
		return moteurType;
	}

	public void setMoteurType(TypeMoteur moteurType) {
		this.moteurType = moteurType;
	}

	// geter & seter for engine cylinder
	public String getCylinder() {
		return cylinder;
	}

	public void setCylinder(String cylinder) {
		this.cylinder = cylinder;
	}

	// To customize further for specific engine
	public abstract String toString();
	
}
