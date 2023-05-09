package com.udemy.demospring2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SwimCofigJavaBasedDemoApp {

    public static void main(String[] args) {
        
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SportConfig.class);
        
        SwimCoach mCoach = context.getBean("swimCoach",SwimCoach.class);
        
        System.out.println(mCoach.getDailyWorkout());
        System.out.println(mCoach.getDailyFortune());
        // New property injected to check
        System.out.print(mCoach.getTeam());
        System.out.println(" with email "+mCoach.getEmail());
        
        context.close();
    }

}
