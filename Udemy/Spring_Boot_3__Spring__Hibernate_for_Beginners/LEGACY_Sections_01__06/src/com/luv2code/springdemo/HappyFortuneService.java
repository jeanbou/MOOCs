package com.luv2code.springdemo;

import java.util.Random;

public class HappyFortuneService implements FortuneService {
    
    @Override
    public String getFortune() {
        return "Today ia your lucky day";
    }
    
    @Override
    public String getFortuneRandome() {
        final String[] proper_noun = {"Fortune Fred No Fortune At All", "Jane Fortune Luck Sure", "Richard Nixon like Fortune", "Miss America Dream Fortune"};
        Random random = new Random();
        int index = random.nextInt(proper_noun.length);
        return proper_noun[index];
    }

}
