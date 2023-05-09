package com.udemy.demospring2;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
//@ComponentScan("com.udemy.demospring2")
@PropertySource("classpath:sport.properties")
public class SportConfig {
    
 @Bean
 public FortuneService sadFortuneService() {
     return new SadFortuneService();
 }
 
 @Bean
 public Coach swimCoach() {
     return new SwimCoach(sadFortuneService());
 }

}
