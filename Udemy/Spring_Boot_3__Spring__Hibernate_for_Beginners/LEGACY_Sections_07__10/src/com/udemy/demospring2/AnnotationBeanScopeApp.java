package com.udemy.demospring2;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class AnnotationBeanScopeApp {

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        Coach mCoach = context.getBean("tenisCoach",Coach.class);
        Coach betaCoach = context.getBean("tenisCoach",Coach.class);
        
        boolean isEqual = (mCoach == betaCoach);
        
        System.out.println("isEqual : "+isEqual);
        //.out.println(mCoach.getDailyFortune());
        
        System.out.println("Memory mCoach add : "+mCoach);
        System.out.println("Memory betaCoach add : "+betaCoach);
        context.close();

    }

}
