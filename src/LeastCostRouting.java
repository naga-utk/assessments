import com.assessment.bt.model.*;
import com.assessment.bt.util.CsvFileReader;

import java.io.IOException;
import java.util.*;


public class LeastCostRouting {

    private List<Person> persons = new ArrayList<>();
    public void loadDataFromCsvFiles(String elementsFile, String linksFile, String personsFile, String networksFile) throws IOException {
        Map<String, Exchange> exchangeMap = new HashMap<>();
        Map<String, NetworkElement> elementMap = CsvFileReader.readNetworkElements(elementsFile, exchangeMap);
        Map<String, Link> linksMap = CsvFileReader.readLinks(linksFile, elementMap);
        CsvFileReader.readNetworks(networksFile, linksMap, elementMap);
        persons = CsvFileReader.readPersons(personsFile, exchangeMap);
       // System.out.println(elementMap);
    }

    public Person findPersonByName(String name) {
        return persons.stream().filter(p -> p.getName().equals(name)).findFirst().orElse(null);
    }

    public Route findLeastCostRoute(Person sourcePerson, Person destinationPerson) {
        return findLeastCostRoute(sourcePerson.getNearestExchange(), destinationPerson.getNearestExchange());
    }

    private Route findLeastCostRoute(Exchange source, Exchange destination) {
        PriorityQueue<Route> pq = new PriorityQueue<>(Comparator.comparingInt(Route::getTotalRouteCost));
        Map<NetworkElement, Integer> minCostMap = new HashMap<>();

//        System.out.println(source.getElements());
//        System.out.println(destination.getElements());
        for (NetworkElement sourceElement : source.getElements()) {
            pq.add(new Route(List.of(sourceElement), sourceElement.getProcessingTime(), 0));
            minCostMap.put(sourceElement, 0);
        }

        while (!pq.isEmpty()) {
            Route currentRoute = pq.poll();
            NetworkElement currentElement = currentRoute.getElements().get(currentRoute.getElements().size()-1);

            if (destination.getElements().contains(currentElement)) {
                return currentRoute;
            }

            for (Link link : currentElement.getLinks()) {
                NetworkElement nextElement = link.getTo();
                int newProcessingTime = currentRoute.getTotalProcessingTime() + nextElement.getProcessingTime();
                int newCost = currentRoute.getTotalCost() + link.getCost();
                int totalNewCost = (5 * newProcessingTime) + (2 * newCost);

                if (!minCostMap.containsKey(nextElement) || totalNewCost < minCostMap.get(nextElement)) {
                    minCostMap.put(nextElement, totalNewCost);
                    List<NetworkElement> newPath = new ArrayList<>(currentRoute.getElements());
                    newPath.add(nextElement);
                    Route newRoute = new Route(newPath, newProcessingTime, newCost);
                    pq.add(newRoute);
                }
            }
        }

        return null;
    }

    public static void main(String[] args) {
        LeastCostRouting routing = new LeastCostRouting();
        try {

            String elementsFileAbsolutePath = "network_elements1.2.csv";
            String linksFileAbsolutePath = "links1.2.csv";
            String personsFileAbsolutePath = "persons1.2.csv";
            String networksFileAbsolutePath = "network1.2.csv";

//            String elementsFileAbsolutePath = "C:/Users/network_elements1.1.csv";
//            String linksFileAbsolutePath = "C:/Users/links1.1.csv";
//            String personsFileAbsolutePath = "C:/Users/persons1.1.csv";
//            String networksFileAbsolutePath = "C:/Users/network1.1.csv";

            routing.loadDataFromCsvFiles(elementsFileAbsolutePath, linksFileAbsolutePath, personsFileAbsolutePath, networksFileAbsolutePath);

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter Source Person:");
            String sourceName = scanner.nextLine();
            System.out.println("Enter Destination Person:");
            String destinationName = scanner.nextLine();

            Person sourcePerson = routing.findPersonByName(sourceName);
            Person destinationPerson = routing.findPersonByName(destinationName);

            if (sourcePerson != null && destinationPerson != null) {
                Route route = routing.findLeastCostRoute(sourcePerson, destinationPerson);
                if (route != null) {
                    System.out.println("Least cost route found with cost: " + route.getTotalRouteCost());
                    System.out.println("Least cost route is : " + route);
                } else {
                    System.out.println("No route found between the specified persons.");
                }
            } else {
                System.out.println("Invalid source or destination person.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}