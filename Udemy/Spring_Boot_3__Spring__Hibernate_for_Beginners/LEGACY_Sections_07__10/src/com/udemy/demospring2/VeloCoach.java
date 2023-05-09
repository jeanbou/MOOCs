package com.udemy.demospring2;

import org.springframework.stereotype.Component;

@Component
public class VeloCoach implements Coach {

    @Override
    public String getDailyWorkout() {
       return "You are too heavy for bike!";
    }

    @Override
    public String getDailyFortune() {
        // TODO Auto-generated method stub
        return null;
    }

}
