package com.luv2code.springdemo;

public class BaseballCoach implements Coash {
    
    private FortuneService fortuneService;
    
    public  BaseballCoach(FortuneService ftService) {
        this.fortuneService = ftService;
    }
    
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes somewhere";
    }

    @Override
    public String getDailyFortune() {
        return fortuneService.getFortune();
    }
    
    @Override
    public String getFortuneRandome() {
        return fortuneService.getFortuneRandome();
    }

}
