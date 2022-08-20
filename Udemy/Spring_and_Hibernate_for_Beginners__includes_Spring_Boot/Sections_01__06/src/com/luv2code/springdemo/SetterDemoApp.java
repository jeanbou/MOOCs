package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;


public class SetterDemoApp {

    public static void main(String[] args) {
     // load the spring configuration file
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        // retrieve bean
        CricetCoach theCoach = context.getBean("myCricetCoach",CricetCoach.class);
        // call it
        System.out.println(theCoach.getDailyWorkout());
        System.out.println(theCoach.getDailyFortune());
        System.out.println("Email "+ theCoach.getEmailAddress());
        System.out.println("Team "+ theCoach.getTeam());
        System.out.println(theCoach.getFortuneRandome());
        // close it
        context.close();

    }

}
