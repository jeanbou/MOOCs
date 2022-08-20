package com.udemy.demospring2;

import org.springframework.stereotype.Component;

@Component
public class DBService implements FortuneService {

    @Override
    public String getFortune() {
        return "DB Service";
    }

}
