package com.udemy.demospring2;

public class SadFortuneService implements FortuneService {

    @Override
    public String getFortune() {
        return "Today is Sad Fortune Service Day";
    }
}
