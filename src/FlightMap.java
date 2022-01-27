import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class FlightMap {

    Map<Character, String> incoming = new HashMap<>();
    Map<Character, String> outgoing = new HashMap<>();
    Map<String, Integer> prices = new HashMap<>();

    // Map<String, String> routes = new HashMap<>();

    public FlightMap(char origin) {
        initEdge(origin);
    }

    public void addForwardEdge(String edge, int cost) {
        if(edge.length() != 2) return;

        prices.put(edge, cost);

        char source = edge.charAt(0);
        char destination = edge.charAt(1);

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


}
