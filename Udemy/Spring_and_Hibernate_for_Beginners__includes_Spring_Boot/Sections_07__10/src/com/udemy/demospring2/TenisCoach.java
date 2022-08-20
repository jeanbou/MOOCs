package com.udemy.demospring2;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope("prototype")
public class TenisCoach implements Coach {

    @Autowired
    @Qualifier("restFortuneService")
    private FortuneService fServ;
    
    //@Autowired
    //public TenisCoach(FortuneService fServ) {
    //    this.setFortuneService(fServ);
    //}
    
    public TenisCoach() {
        System.out.println("Constructor... Tenis Coash... debugger");
    }
    
    @PostConstruct
    public void doMyOnStart() {
        System.out.println(" doMyOnStart");
    }
    
    @PreDestroy
    public void doMyOnExit() {
        System.out.println(" doMy Exit");
    }
    
    /*
    @Autowired
    public void doSettingsForFortuneService(FortuneService fServ) {
        System.out.println("inside doSettingsFortuneService... debugger");
        this.fServ = fServ;
    }
    
    @Autowired
    public void setFortuneService(FortuneService fServ) {
        System.out.println("inside setFortuneService... debugger");
        this.fServ = fServ;
    }
    */
    
    @Override
    public String getDailyWorkout() {
        return "Practise you tenis hand regularly";
    }

    @Override
    public String getDailyFortune() {
        return fServ.getFortune();
    }

}
