package com.udemy.demospring2;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RestFortuneService implements FortuneService {

    private String[] data = {
            "Random A1", "Random A2", "Random A3"
    };
    
    private Random myRandome = new Random();
    
    @Override
    public String getFortune() {
        int index = myRandome.nextInt(data.length);
        return "Rest Service Fortune" + data[index];
    }

}
