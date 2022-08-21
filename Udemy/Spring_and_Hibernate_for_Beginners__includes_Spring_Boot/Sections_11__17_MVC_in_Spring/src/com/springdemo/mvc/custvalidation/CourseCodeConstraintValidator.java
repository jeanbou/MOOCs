package com.springdemo.mvc.custvalidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CourseCodeConstraintValidator 
    implements ConstraintValidator<CourseCode, String>  {

    private String[] courseCodePrefixes;
    
    @Override
    public void initialize(CourseCode aCourseCode) {
        this.courseCodePrefixes = aCourseCode.value();
    }

    @Override
    public boolean isValid(String aCode, ConstraintValidatorContext aValidatorContext) {
        
        if (aCode != null && !aCode.trim().isEmpty()) {
            for (String aCourseCodePref : courseCodePrefixes) {
                if (aCode.trim().startsWith(aCourseCodePref)) {
                    return true;
                }
            }
            return false;
        }
        return true;
    }
    
}
