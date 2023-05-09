package com.springdemo.mvc;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.springdemo.mvc.custvalidation.CourseCode;

public class Customer {

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstNameString) {
        this.firstName = firstNameString;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastNameString) {
        this.lastName = lastNameString;
    }

    private String firstName;
    
    @NotNull(message="is required")
    @Size(min=1, message="is required")
    private String lastName;
    
    @NotNull(message="is required")
    @Max(value=10, message="must be less than 11")
    @Min(value=0, message="must be not negative")
    private Integer freePasses;

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePassCounter) {
        this.freePasses = freePassCounter;
    }
    
    // https://stackoverflow.com/questions/43298661/french-regex-zipcode
    // https://fr.wikipedia.org/wiki/Code_postal_en_France
    @Pattern(regexp="(?:0[1-9]|[13-8][0-9]|2[ab1-9]|9[0-5])(?:[0-9]{3})?|9[78][1-9](?:[0-9]{2})?", message="It should French postal code format")
    private String postalCode;

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    
    @CourseCode(message="prefix must start with LIV or TOP wich is defined in the list")
    private String courseCode;

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
    
}
