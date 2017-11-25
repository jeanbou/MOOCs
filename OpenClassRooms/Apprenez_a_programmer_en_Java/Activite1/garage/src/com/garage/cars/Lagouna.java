package com.garage.cars;

@SuppressWarnings("serial")
public class Lagouna extends Vehicule {

	final private static Marque carMark = Marque.RENAULT;
	final private static String carName = "Lagouna";
	
	public Lagouna() {
		super(carMark,carName);
	}
	
	public Marque getCarMark() {
		return carMark;
	}
	
	public String getCarName() {
		return carName;
	}
			
}
