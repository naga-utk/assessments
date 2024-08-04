package com.assessment.bt.util;

import com.assessment.bt.model.Exchange;
import com.assessment.bt.model.Link;
import com.assessment.bt.model.NetworkElement;
import com.assessment.bt.model.Person;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class CsvFileReader {

    public static Map<String, NetworkElement> readNetworkElements(String filePath, Map<String, Exchange> exchangeMap) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1) // Skip header
                .map(line -> getNetworkElement(exchangeMap, line))
                .collect(Collectors.toMap(NetworkElement::getName, ne->ne));
    }

    private static NetworkElement getNetworkElement(Map<String, Exchange> exchangeMap, String line) {
        String[] parts = line.split(",");
        NetworkElement ne;
        if(parts.length == 3) {
           if(!exchangeMap.containsKey(parts[2])){
               Exchange exchange = new Exchange(parts[2], new ArrayList<>());
               ne = new NetworkElement(parts[0], Integer.parseInt(parts[1]), exchange);
               exchange.addElements(ne);
               exchangeMap.put(parts[2], exchange);
           } else {
               ne = new NetworkElement(parts[0], Integer.parseInt(parts[1]), exchangeMap.get(parts[2]));
               exchangeMap.get(parts[2]).addElements(ne);
           }
        } else {
            ne = new NetworkElement(parts[0], Integer.parseInt(parts[1]), null);
        }
        return ne;
    }

    public static Map<String, Link> readLinks(String filePath, Map<String, NetworkElement> elementMap) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Link(parts[0], Integer.parseInt(parts[1]));
                })
                .collect(Collectors.toMap(Link::getName, link->link));
    }

    public static List<Person> readPersons(String filePath, Map<String, Exchange> exchangeMap) throws IOException {
        return Files.lines(Paths.get(filePath))
                .skip(1)
                .map(line -> {
                    String[] parts = line.split(",");
                    return new Person(parts[0], exchangeMap.get(parts[1]));
                })
                .collect(Collectors.toList());
    }

    public static void readNetworks(String filePath, Map<String, Link> linksMap, Map<String, NetworkElement> elementMap) throws IOException {
        Files.lines(Paths.get(filePath))
                .skip(1)
                .forEach(line -> {
                    mapLinksAndNetworkElements(linksMap, elementMap, line);
                });
    }

    private static void mapLinksAndNetworkElements(Map<String, Link> linksMap, Map<String, NetworkElement> elementMap, String line) {
        String[] parts = line.split(",");
        Link link = linksMap.get(parts[1]);
        link.setFrom(elementMap.get(parts[0]));
        link.setTo(elementMap.get(parts[2]));
        elementMap.get(parts[0]).addLink(link);

        String reverseLinkName = "Link"+parts[2].substring(2)+parts[2].substring(2);
        Link reverseLink = new Link(reverseLinkName, link.getCost(), elementMap.get(parts[2]), elementMap.get(parts[0]));
        linksMap.put(reverseLinkName, reverseLink);
        elementMap.get(parts[2]).addLink(reverseLink);
    }
}

