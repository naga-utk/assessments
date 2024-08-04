package com.assessment.bt.model;

public class Person {
    private String name;
    private Exchange nearestExchange;

    public Person(String name, Exchange nearestExchange) {
        this.name = name;
        this.nearestExchange = nearestExchange;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Exchange getNearestExchange() {
        return nearestExchange;
    }

    public void setNearestExchange(Exchange nearestExchange) {
        this.nearestExchange = nearestExchange;
    }
}
