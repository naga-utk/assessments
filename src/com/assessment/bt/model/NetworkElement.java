package com.assessment.bt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class NetworkElement {
    private String name;
    private int processingTime;
    private Exchange exchange;
    private List<Link> links = new ArrayList<>();

    public NetworkElement(String name, int processingTime, Exchange exchange) {
        this.name = name;
        this.processingTime = processingTime;
        this.exchange = exchange;
    }

    public Exchange getExchange() {
        return exchange;
    }

    public void setExchange(Exchange exchange) {
        this.exchange = exchange;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(int processingTime) {
        this.processingTime = processingTime;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    public void addLink(Link link) {
        links.add(link);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NetworkElement that = (NetworkElement) o;

        if (processingTime != that.processingTime) return false;
        if (!name.equals(that.name)) return false;
        if (!Objects.equals(exchange, that.exchange)) return false;
        return links.equals(that.links);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + processingTime;
        result = 31 * result + (exchange != null ? exchange.hashCode() : 0);
        result = 31 * result + links.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "NetworkElement{" +
                "name='" + name + '\'' +
                ", processingTime=" + processingTime +
                ", links=" + links +
                '}';
    }
}
