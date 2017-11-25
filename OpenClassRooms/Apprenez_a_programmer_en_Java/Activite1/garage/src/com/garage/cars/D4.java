package com.garage.cars;

@SuppressWarnings("serial")
public class D4 extends Vehicule {
	
	final private static Marque carMark = Marque.CITROEN;
	final private static String carName = "D4";
	
	public D4() {
		super(carMark,carName);
	}
	
	public Marque getCarMark() {
		return carMark;
	}
	
	public String getCarName() {
		return carName;
	}

}
