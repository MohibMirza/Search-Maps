import javax.print.attribute.standard.PrinterResolution;
import java.util.*;

public class FlightMap {

    // CHANGES:
    // JUST PASSTHROUGH ALL THE POSSIBLE NODES INSTEAD
    // OF STORING connectedToOrigin nodes
    // It's not necessary!!

    Map<Character, String> outgoing = new HashMap<>();
    Map<String, Integer> prices = new HashMap<>();
    char origin;


    public FlightMap(char origin) {
        this.origin = origin;
        initEdge(origin);
    }

    // INFO: Add an edge to the FlightMap
    public void addForwardEdge(String edge, int cost) {
        if(edge.length() != 2) return;

        prices.put(edge, cost);

        char source = edge.charAt(0);
        char destination = edge.charAt(1);

        initEdge(source);
        initEdge(destination);

        outgoing.put(source, outgoing.get(source).concat(Character.toString(destination)));
    }

    public boolean isReachable(char destination) {
        char source = origin;
        Set<Character> visited = new HashSet<>();

        Queue<Character> queue = new PriorityQueue<>();
        queue.add(source);
        visited.add(source);

        while(!queue.isEmpty()) {
            char c = queue.remove();
            String children = outgoing.get(c);

            for(int i = 0; i < children.length(); i++) {
                char curr = children.charAt(i);
                if(curr == destination) {
                    return true;
                }else if(!visited.contains(curr)) {
                    queue.add(curr);
                    visited.add(curr);

                }

            }
        }

        return false;

    }

    public String findPath(char destination) {
        char source = origin;
        Vector<String> paths = new Vector<>();
        Set<Character> visited = new HashSet<>();

        findPathUtil(source, destination, "", visited, paths);

        if(!paths.isEmpty()) return paths.get(0);
        return "";
    }

    private void findPathUtil(char curr, char destination, String path, Set<Character> visited, Vector<String> paths) {
        path += curr;
        if(curr == destination) {
            paths.add(path);
            return;
        }else {
            String children = outgoing.get(curr);
            for(int i = 0; i < children.length(); i++) {
                char child = children.charAt(i);

                if(!visited.contains(child)) {
                    visited.add(child);
                    findPathUtil(child, destination, path, visited, paths);
                }

            }
        }


    }

    public int calculateCost(String path) {
        int cost = 0;
        for(int i=1; i < path.length(); i++) {
            char lastCity = path.charAt(i-1);
            char currentCity = path.charAt(i);
            String route = String.valueOf(lastCity) + String.valueOf(currentCity);
            if(prices.containsKey(route)) {
                cost += prices.get(route);
            }
        }
        return cost;
    }

    private void initEdge(char c) {

        if(!outgoing.containsKey(c)){
            outgoing.put(c, "");
        }
    }

    public Vector<Character> reachableNodes() {
        Vector<Character> visited = new Vector<>();
        Queue<Character> queue = new PriorityQueue<>();
        queue.add(origin);
        visited.add(origin);
        while(!queue.isEmpty()) {
            char front = queue.remove();
            String children = outgoing.get(front);
            for(int i = 0; i < children.length(); i++) {
                char child = children.charAt(i);
                if(!visited.contains(child)) {
                    visited.add(child);
                    queue.add(child);
                }
            }
        }
        visited.remove(0);

        return visited;
    }



    public String toString() {
        Vector<Character> reachableNodes = reachableNodes();
        Vector<String> formattedPaths = new Vector<>();
        Vector<String> pathCosts = new Vector<String>();

        String label1 = "Destination";
        String label2 = "Flight Route from " + origin;
        String label3 = "Total Cost";

        int spacing = 3;
        int size1 = label1.length() + spacing;
        int size2 = label2.length() + spacing;
        int size3 = label3.length() + spacing;

        for(int i = 0; i < reachableNodes.size(); i++) {
            char destination = reachableNodes.get(i);
            String path = findPath(destination);
            String pathFormatted = formatPath(path);
            if(pathFormatted.length() > size2) {
                size2 = pathFormatted.length() + spacing;
            }
            formattedPaths.add(pathFormatted);
            pathCosts.add("$" + calculateCost(path));
        }


        String format = "%-" + size1 + "s%-" + size2 + "s%-" + size3 + "s\n";

        String s = String.format(format, label1, label2, label3);

        for(int i =0; i < reachableNodes.size(); i++) {
            s += String.format(format, reachableNodes.get(i),
                    formattedPaths.get(i), pathCosts.get(i));
        }

        return s;
     }

     private static String formatPath(String path) {
        String formatted = "" + path.charAt(0) + ",";
        for(int i = 1; i < path.length(); i++) {
            char c = path.charAt(i);
            formatted += " " + c;
            if(i != path.length()-1) {
                formatted += ",";
            }
        }
        return formatted;

     }


}
