package com.assessment.bt.model;

public class Link {

    private String name;
    private int cost;
    private NetworkElement from;
    private NetworkElement to;


    public Link(String name, int cost) {
        this.name = name;
        this.cost = cost;

    }

    public Link(String name, int cost, NetworkElement from, NetworkElement to) {
        this.name = name;
        this.cost = cost;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NetworkElement getFrom() {
        return from;
    }

    public void setFrom(NetworkElement from) {
        this.from = from;
    }

    public NetworkElement getTo() {
        return to;
    }

    public void setTo(NetworkElement to) {
        this.to = to;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Link{" +
                "name='" + name + '\'' +
                ", cost=" + cost +
                ", from=" + from.getName() +
                ", to=" + to.getName() +
                '}';
    }
}
