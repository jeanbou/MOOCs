package com.springdemo.mvc;

import java.util.LinkedHashMap;

public class Student {
    
    private String favoriteLanguage;
    
    public String getFavoriteLanguage() {
        return favoriteLanguage;
    }

    public void setFavoriteLanguage(String favoriteLanguage) {
        this.favoriteLanguage = favoriteLanguage;
    }

    public Student() {
        // Disabled as the list read from country.properties
        /*
        countryOptions = new LinkedHashMap();
        countryOptions.put("BR","Brazil");
        countryOptions.put("FR","France");
        countryOptions.put("UA","Ukraine");
        */
        
        favoriteLanguageOptions = new LinkedHashMap();
        favoriteLanguageOptions.put("Java","Java");
        favoriteLanguageOptions.put("C","C");
        favoriteLanguageOptions.put("C++","C++");
        
    }
    
    private String [] operatingSystems;
    
    public String [] getOperatingSystems() {
        return operatingSystems;
    }

    public void setOperatingSystems(String [] operatingSystems) {
        this.operatingSystems = operatingSystems;
    }

    private String firstName;
    
    private String lastName;
    
    private String country;
    
    private LinkedHashMap<String, String> countryOptions;
    
    private LinkedHashMap<String, String> favoriteLanguageOptions;
    
    public LinkedHashMap<String, String> getCountryOptions() {
        return countryOptions;
    }
    
    public LinkedHashMap<String, String> getFavoriteLanguageOptions() {
        return favoriteLanguageOptions;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    


}
