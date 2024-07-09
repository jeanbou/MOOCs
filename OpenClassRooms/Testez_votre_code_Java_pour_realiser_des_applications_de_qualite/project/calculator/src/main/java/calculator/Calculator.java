package calculator;

import java.util.HashSet;
import java.util.Set;

public class Calculator {

	public int add(int a, int b) {
		
		return (a+b);
	}

	public int multiply(int a, int b) {
		
		return (a*b);
	}
	
	public double add(double a, double b) {
		return a + b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public void longCalculation() {
		try {
		    // Attendre 0.5 secondes
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

	public Set<Integer> digitsSet(int number) {
		 
		Set<Integer> integersSet = new HashSet<Integer>();
		String numberString = String.valueOf(number);
		
		for (int i = 0; i < numberString.length(); i++) {
			integersSet.add(Integer.parseInt(numberString, i, i+1, 10));
		}
		
		return integersSet;		
	}

}
