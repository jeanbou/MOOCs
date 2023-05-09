package com.luv2code.springdemo;

public class TrackCoach implements Coash {

    private FortuneService fortuneService;
    
    public TrackCoach() {
    }
    
    public TrackCoach(FortuneService ftService) {
        this.fortuneService = ftService;
    }
    
    @Override
    public String getDailyWorkout() {
        return "Run a hard 5km";
    }

    @Override
    public String getDailyFortune() {
        return "Just Do it" + fortuneService.getFortune();
    }
    
    @Override
    public String getFortuneRandome() {
        return "Just Do it for TrackCoash with a luck of " + fortuneService.getFortuneRandome();
    }
    
    public void trackCoachInit() {
        System.out.println("trackCoach :: Init");
    }

    public void trackCoachDestroy() {
        System.out.println("trackCoach :: Destroy");
    }

}
