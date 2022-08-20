package com.udemy.demospring2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach mCoach = context.getBean("tenisCoach",Coach.class);
        
        System.out.println(mCoach.getDailyWorkout());
        System.out.println(mCoach.getDailyFortune());
        
        context.close();
    }

}
