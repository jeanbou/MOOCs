package com.garage.cars;

@SuppressWarnings("serial")
public class A300B extends Vehicule {
	final private static Marque carMark = Marque.PEGEUT;
	final private static String carName = "A330B";
	
	public A300B() {
		super(carMark,carName);
	}
	
	public Marque getCarMark() {
		return carMark;
	}
	
	public String getCarName() {
		return carName;
	}
}
