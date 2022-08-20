package com.udemy.demospring2;

import org.springframework.beans.factory.annotation.Value;

public class SwimCoach implements Coach {

    private FortuneService fortuneService;
    
    @Value("${sportteam.email}")
    private String email;
    
    @Value("${sportteam.team}")
    private String team;
    
    public SwimCoach(FortuneService theFortuneService) {
        fortuneService = theFortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1km";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }
    

}
