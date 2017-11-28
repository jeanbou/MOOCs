package com.garage.test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.garage.cars.*;
import com.voiture.devices.*;
import com.voiture.engine.*;

public class TestGarage {
	public static void main(String[] args) {
	   	 Garage garage = new Garage();
	   	 // IB: do slightly different in more sure-way instead of proposed code // System.out.println(garage); // replaced by
	   	 System.out.println(garage.toString());
	   	 
	   	 // IB: Requirement of the exercises :: Read a stored data garage file
	   	 try {
	   		 ObjectInputStream ois = new ObjectInputStream(
	   			 						new BufferedInputStream(
	   			 								new FileInputStream(
	   			 										new File("garage.bin"))));
	            
	   		 try {
	   			 System.out.println("****************************");
	   			 System.out.println("*  Garage OpenClassRooms   *");
	   			 System.out.println("****************************\n");
	   			 Object garageDeserialized = ois.readObject();
	   			 System.out.println(garageDeserialized.toString());
	        
	   		 } catch (ClassNotFoundException e) {
	   			 e.printStackTrace();
	   		 }
		
	   		 ois.close();
	   	 }
	   	 catch (FileNotFoundException e) {
	   		 System.err.println("Aucune voiture sauvagardée !\nError: " + e.getMessage());
	   		 System.out.println("\n****************************");
  			 System.out.println("*  Garage OpenClassRooms   *");
  			 System.out.println("****************************\n");
	         //e.printStackTrace();
	     }
	   	 catch (IOException e) {
	       e.printStackTrace();
	     }
	   	 
	   	 // IB: The given code
	   	 Vehicule lag1 = new Lagouna();
	   	 lag1.setMoteur(new MoteurEssence("150 Chevaux", 10256d));
	   	 lag1.addOption(new GPS());
	   	 lag1.addOption(new SiegeChauffant());
	   	 lag1.addOption(new VitreElectrique());
	   	 garage.addVoiture(lag1);
	   		 
	   	 Vehicule A300B_2 = new A300B();
	   	 A300B_2.setMoteur(new MoteurElectrique("1500 W", 1234d));
	   	 A300B_2.addOption(new Climatisation());
	   	 A300B_2.addOption(new BarreDeToit());
	   	 A300B_2.addOption(new SiegeChauffant());
	   	 garage.addVoiture(A300B_2);
	   	 
	   	 Vehicule d4_1 = new D4();
	   	 d4_1.setMoteur(new MoteurDiesel("200 Hdi", 25684.80d));
	   	 d4_1.addOption(new BarreDeToit());
	   	 d4_1.addOption(new Climatisation());
	   	 d4_1.addOption(new GPS());
	   	 garage.addVoiture(d4_1);   	 
	   	 
	   	 Vehicule lag2 = new Lagouna();
	   	 lag2.setMoteur(new MoteurDiesel("500 Hdi", 456987d));
	   	 garage.addVoiture(lag2);
	   	 
	   	 Vehicule A300B_1 = new A300B();
	   	 //A300B_1.setMoteur(new MoteurHybride("ESSENCE/Electrique", 12345.95d));
	   	 // IB: Note, for hybrid engine I do slightly different constructor, here is the logic
	   	 // 1) Hybrid engine is the only engine that "contains of" two engines
	   	 // 2) One of this two engine is electric, thus you cannot have diesel & essence
	   	 // 3) No need (purely logical) each time to say ESSENCE and ... of course electrics ; c'mon you use Hybrid!
	   	 A300B_1.setMoteur(new MoteurHybride(TypeMoteur.ESSENCE, 12345.95d));
	   	 A300B_1.addOption(new VitreElectrique());
	   	 A300B_1.addOption(new BarreDeToit());
	   	 garage.addVoiture(A300B_1);
	   	 
	   	 Vehicule d4_2 = new D4();
	   	 d4_2.setMoteur(new MoteurElectrique("100 KW", 1224d));
	   	 d4_2.addOption(new SiegeChauffant());
	   	 d4_2.addOption(new BarreDeToit());
	   	 d4_2.addOption(new Climatisation());
	   	 d4_2.addOption(new GPS());
	   	 d4_2.addOption(new VitreElectrique());
	   	 garage.addVoiture(d4_2);
	   	 
	   	 // IB: The same, "more intelligent". Done & left for debugging purpose  
	   	 //System.out.println(garage.toString());
	   	 
	   	 //IB: Main work, put the data into the file via serialization. ADDED after the given test main code
	   	 //IB: declaration as in course example
	     
	     ObjectOutputStream oos;
	     try {
	       oos = new ObjectOutputStream(
	               new BufferedOutputStream(
	                 new FileOutputStream(
	                   new File("garage.bin"))));
	         	
	       // IB: writing object to file and closing it
	       oos.writeObject(garage);	       
	       oos.close();
	     }
	     catch (IOException e) {
	       e.printStackTrace();
	     } 
	   	 
	    }

}
