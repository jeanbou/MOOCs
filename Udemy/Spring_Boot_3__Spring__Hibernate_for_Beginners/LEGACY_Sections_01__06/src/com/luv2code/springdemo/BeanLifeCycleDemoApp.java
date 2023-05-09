package com.luv2code.springdemo;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BeanLifeCycleDemoApp {

    public static void main(String[] args) {
        // Load Spring conf
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beanLifeCycle-applicationContext2.xml");
        
        // retrieve bean from container
        Coash justCoach = context.getBean("myCoash",Coash.class);
        // Check if it is the same bean
        // boolean result = (justCoach == sameJustCoach);
     // System.out.println(" Result : "+result);
     // System.out.println(" Memory addr : "+justCoach);
     // System.out.println(" Memory addr : "+sameJustCoach);
        
        System.out.println(justCoach.getDailyWorkout());
        
        context.close();

    }

}
