import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SearchMapJUnit1 {
    FlightMap flightMap;

    @Test
    public void testParseFile() throws IOException {
        flightMap = SearchMap.parseFile("input.txt");

        // all these test methods are to make
        assertEquals(flightMap.calculateCost("PW"), 200);
        assertEquals(flightMap.calculateCost("PR"), 300);
        assertEquals(flightMap.calculateCost("RX"), 200);
        assertEquals(flightMap.calculateCost("QX"), 375);
        assertEquals(flightMap.calculateCost("WS"), 250);
        assertEquals(flightMap.calculateCost("ST"), 300);

        // distant routes cost test
        assertEquals(flightMap.calculateCost("PWY"), 700);
        assertEquals(flightMap.calculateCost("PWYZ"), 1150);
        assertEquals(flightMap.calculateCost("PWYRX"), 1500);
        assertEquals(flightMap.calculateCost("PWSTWST"), 1650);

        String PW = flightMap.findPath('W');
        assertTrue(PW.compareTo("PW") == 0);

        String PR = flightMap.findPath('R');
        assertTrue(PR.compareTo("PR") == 0 || PR.compareTo("PWYR") == 0);

        String PS = flightMap.findPath('S');
        assertTrue(PS.compareTo("PWS") == 0 );

        String PT = flightMap.findPath('T');
        assertTrue(PT.compareTo("PWST") == 0);


        assertEquals(flightMap.isReachable('Q'), false);
        assertEquals(flightMap.isReachable('S'), true);
        assertEquals(flightMap.isReachable('T'), true);

        // adding unreachable edges
        flightMap.addForwardEdge("AS", 200);
        flightMap.addForwardEdge("BP", 50);

        assertEquals(flightMap.isReachable('A'), false);
        assertEquals(flightMap.isReachable('S'), true);
        assertEquals(flightMap.isReachable('B'), false);

        flightMap.addForwardEdge("PO", 200);
        flightMap.addForwardEdge("PA", 200);
        flightMap.addForwardEdge("P1", 200);
        flightMap.addForwardEdge("12", 200);
        flightMap.addForwardEdge("23", 200);
        flightMap.addForwardEdge("34", 200);
        flightMap.addForwardEdge("45", 200);
        flightMap.addForwardEdge("56", 200);
        flightMap.addForwardEdge("67", 200);
        flightMap.addForwardEdge("78", 200);
        flightMap.addForwardEdge("89", 200);
        flightMap.addForwardEdge("9L", 200);

        assertEquals(flightMap.reachableNodes().size(), 21);
    }


}
