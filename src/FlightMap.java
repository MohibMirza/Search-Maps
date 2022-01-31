import java.util.*;

public class FlightMap {

    // CHANGES:
    // JUST PASSTHROUGH ALL THE POSSIBLE NODES INSTEAD
    // OF STORING connectedToOrigin nodes
    // It's not necessary!!

    Map<Character, String> outgoing = new HashMap<>();
    Map<String, Integer> prices = new HashMap<>();


    public FlightMap(char origin) {
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

    public boolean isReachable(char source, char destination) {
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

    public String findPath(char source, char destination) {
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

    public String toString() {
        String s = "Outgoing: " + outgoing.size() + "\n" +
                   "Prices: " + prices.size() + "\n";
        return s;
     }


}
