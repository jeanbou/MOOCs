package com.luv2code.springdemo;

public class CricetCoach implements Coash {

    private FortuneService fortuneService;
    private String emailAddress;
    private String team;
    
    public String getEmailAddress() {
        return emailAddress;
    }

    public String getTeam() {
        return team;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public CricetCoach() {
        System.out.println("We are inside the constructor CricetCoach");
    }
    
    public void setFortuneService(FortuneService fortuneService) {
        //System.out.println("We are inside setFortuneService");
        this.fortuneService = fortuneService;
    }

    @Override
    public String getDailyWorkout() {
        return "Fast bowling";
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
