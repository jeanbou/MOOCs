package com.garage.cars;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Garage implements Serializable {
	
	private List<Vehicule> listVehicule; 
	
	public Garage() {
		this.listVehicule = new ArrayList<Vehicule>();
	}
	
	public List<Vehicule> getListVehicule() {
		return listVehicule;
	}

	public void setListVehicule(List<Vehicule> listVehicule) {
		this.listVehicule = listVehicule;
	}

	public String toString() {
		// IB: to form the string of results
		String aResultStr = new String();
		
		for(Vehicule vehicule : listVehicule) {
			aResultStr += " "+vehicule.toString(); 
		}
		
		return aResultStr;
	}

	public void addVoiture(Vehicule aVehicule) {
		this.getListVehicule().add(aVehicule);	
	}

}
