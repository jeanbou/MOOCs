package com.udemy.demospring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class CofigJavaBasedDemoApp {

    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
        
        // Ex context.getBean("tenis",Coach.class); as the change of configuration
        Coach mCoach = context.getBean("swimCoach",SwimCoach.class);
        
        System.out.println(mCoach.getDailyWorkout());
        System.out.println(mCoach.getDailyFortune());
        
        context.close();
    }

}
