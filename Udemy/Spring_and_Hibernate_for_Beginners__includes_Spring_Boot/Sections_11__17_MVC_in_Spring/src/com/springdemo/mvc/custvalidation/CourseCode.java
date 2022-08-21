package com.springdemo.mvc.custvalidation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // default course code
    public String[] value() default {"LUV","TOP"};
    
    // error message
    public String message() default "must starts with predefined list of prefixes prefix";
    
    // group default
    public Class<?>[] groups() default {};
    
    // Payload
    public Class<? extends Payload>[] payload() default {};
    
}
