package com.garage.cars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.voiture.devices.Option;
import com.voiture.engine.*;

@SuppressWarnings("serial")
public class Vehicule implements Serializable {
	private Marque nomMarque;
	private List<Option> options; 
	private String nom;
	private Double prix;
	private Moteur carEngine;
	
	// Constructor, you need to set the name of the car & its' mark
	public Vehicule(Marque aCarMark, String aCarName) {
		this.options = new ArrayList<Option>();
		this.setNomMarque(aCarMark);
		this.setNom(aCarName);
	}
	
	public Marque getNomMarque() {
		return nomMarque;
	}
	public void setNomMarque(Marque nomMarque) {
		this.nomMarque = nomMarque;
	}
	
	public List<Option> getOptions() {
		return options;
	}
	
	public void setOptions(List<Option> options) {
		this.options = options;
	}
	
	public void addOption(Option option) {
		this.getOptions().add(option);
	}
	
	public Double getAllOptionsPrice() {
		Double price = 0.0d;
		for(Option option : this.getOptions()) {
			price += option.getPrix(); 
		}
		setPrix(price);
		
		return price;
	}
	
	public void setPrix(Double aGivenPrice) {
		this.prix = aGivenPrice;
	}
	
	public String getAllOptions() {
		
		if (this.getOptions().isEmpty()) // IB: no need to conduct string construction and total price estimation
		{
			this.setPrix(0.0d);
			return "";
		}
		
		String aListOfAllOptionsStr = new String("[");
		for(Option option : this.getOptions()) {
			aListOfAllOptionsStr += (" "+option.getOptionName()+" ("+option.getPrix().toString()+"ˆ),"); 
		}
		// The last characters will be comma and space, so we need to remove them in order to make the line nice
		aListOfAllOptionsStr = aListOfAllOptionsStr.substring(0, aListOfAllOptionsStr.length()-1);
		aListOfAllOptionsStr += "]";

		return aListOfAllOptionsStr;
	}
		
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	private void updatePrice() {
		this.prix = this.getAllOptionsPrice()+this.getMoteur().getPrix();
	}
	
	public Double getPrix() {
		updatePrice();
		return prix;
	}
		
	public Moteur getMoteur() {
		return carEngine;
	}
	public void setMoteur(Moteur carEngine) {
		this.carEngine = carEngine;
	}
	
	// IB: it give a detailed description of each car
	public String toString() {
		Double totalPrice = this.getPrix();

		// IB: to form the string of detailed car description
		String aResultStr = new String("\n+ Voiture");
		// Basics of the car: name, type, engine
		aResultStr += (" "+this.getNomMarque().toString());
		aResultStr += (" : "+this.getNom());
		aResultStr += (" : "+this.getMoteur().toString());
		// Options of the car
		aResultStr += this.getAllOptions();
		// Total price
		aResultStr += " d'une valeur totale de ";
		aResultStr += (totalPrice.toString()+ " ˆ");
		
		return aResultStr; 
	}		
}
