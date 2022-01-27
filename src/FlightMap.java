import java.util.*;

public class FlightMap {

    // CHANGES:
    // JUST PASSTHROUGH ALL THE POSSIBLE NODES INSTEAD
    // OF STORING connectedToOrigin nodes
    // It's not necessary!!

    Map<Character, String> incoming = new HashMap<>();
    Map<Character, String> outgoing = new HashMap<>();
    Map<String, Integer> prices = new HashMap<>();

    Set<Character> connectedToOrigin = new HashSet<>(); // Set of all nodes that have a path leading to the origin node

    public FlightMap(char origin) {
        initEdge(origin);
        connectToOrigin(origin);
    }

    // INFO: Add an edge to the FlightMap
    public void addForwardEdge(String edge, int cost) {
        if(edge.length() != 2) return;

        prices.put(edge, cost);

        char source = edge.charAt(0);
        char destination = edge.charAt(1);

        if(connectedToOrigin.contains(source)) {
            connectedToOrigin.add(destination);
        }

        initEdge(source);
        initEdge(destination);

        outgoing.put(source, outgoing.get(source).concat(Character.toString(destination)));
        incoming.put(destination, incoming.get(destination).concat(Character.toString(source)));
    }

    public void initEdge(char c) {
        if(!incoming.containsKey(c)){
            incoming.put(c, "");
        }

        if(!outgoing.containsKey(c)){
            outgoing.put(c, "");
        }
    }

    public void connectToOrigin(char c) {
        Set<Character> visitedNodes = new HashSet<>();
        connect(c, visitedNodes);
    }

    public void connect(char c, Set<Character> visitedNodes) {
        visitedNodes.add(c);
        connectedToOrigin.add(c);
        if(outgoing.get(c).compareTo("") == 0 || visitedNodes.contains(c)) {
            return;
        }

        String outgoings = outgoing.get(c);

        for(int i = 0; i < outgoings.length(); i++) {
            connect(outgoings.charAt(i), visitedNodes);
        }

    }


    public String toString() {
        String s = "Incoming: " + incoming.size() + "\n" +
                   "Outgoing: " + outgoing.size() + "\n" +
                   "Prices: " + prices.size() + "\n";
        return s;
     }


}
