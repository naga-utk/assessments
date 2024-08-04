package com.assessment.bt.model;

import java.util.List;

public class Exchange {
    private String name;
    private List<NetworkElement> elements;

    public Exchange(String name, List<NetworkElement> elements) {
        this.name = name;
        this.elements = elements;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NetworkElement> getElements() {
        return elements;
    }

    public void setElements(List<NetworkElement> elements) {
        this.elements = elements;
    }

    public void addElements(NetworkElement ne) {
        this.elements.add(ne);
    }

    @Override
    public String toString() {
        return "Exchange{" +
                "name='" + name + '\'' +
                '}';
    }
}
