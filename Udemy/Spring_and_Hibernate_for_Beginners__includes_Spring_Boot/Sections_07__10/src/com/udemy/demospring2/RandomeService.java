package com.udemy.demospring2;

import org.springframework.stereotype.Component;

@Component
public class RandomeService implements FortuneService {

    @Override
    public String getFortune() {
        return "Randome Service Fortune";
    }

}
