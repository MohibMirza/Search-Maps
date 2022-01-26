import java.util.Map;

public class FlightMap {

    Map<Character, String> incoming;
    Map<Character, String> outgoing;
    Map<String, Integer> price;

    Map<String, String> route;

    public FlightMap(Map<Character, String> incoming, Map<Character, String> outgoing, Map<String, Integer> price, Map<String, String> route) {
        this.incoming = incoming;
        this.outgoing = outgoing;
        this.price = price;
        this.route = route;
    }


}
