package com.assessment.bt.model;

import java.util.List;
import java.util.stream.Collectors;

public class Route {
    private List<NetworkElement> elements;
    private int totalProcessingTime;
    private int totalCost;

    public Route(List<NetworkElement> elements, int totalProcessingTime, int totalCost) {
        this.elements = elements;
        this.totalProcessingTime = totalProcessingTime;
        this.totalCost = totalCost;
    }

    public List<NetworkElement> getElements() {
        return elements;
    }

    public void setElements(List<NetworkElement> elements) {
        this.elements = elements;
    }

    public int getTotalProcessingTime() {
        return totalProcessingTime;
    }

    public void setTotalProcessingTime(int totalProcessingTime) {
        this.totalProcessingTime = totalProcessingTime;
    }

    public int getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(int totalCost) {
        this.totalCost = totalCost;
    }

    public int getTotalRouteCost() {
        return (5 * totalProcessingTime) + (2 * totalCost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Route route = (Route) o;

        if (totalProcessingTime != route.totalProcessingTime) return false;
        if (totalCost != route.totalCost) return false;
        return elements.equals(route.elements);
    }

    @Override
    public int hashCode() {
        int result = elements.hashCode();
        result = 31 * result + totalProcessingTime;
        result = 31 * result + totalCost;
        return result;
    }

    @Override
    public String toString() {
        return elements.stream().map(NetworkElement::getName).collect(Collectors.joining("->"));
    }
}
