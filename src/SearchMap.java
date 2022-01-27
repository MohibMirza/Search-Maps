import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SearchMap {

    public static void main(String[] args) throws IOException {
//        if(args.length < 2) {
//            System.out.println("Too few args!");
//            return;
//        }

//        String inputFilename = args[0];
//        String outputFilename = args[1];

         String inputFilename = "input.txt";


        FlightMap map = parseFile(inputFilename);

        System.out.println(map.toString());

        System.out.println("Connected to Origin: " + map.connectedToOrigin.size());

        for (Character character : map.connectedToOrigin) {
            System.out.println( character );
        }
    }

    public static FlightMap parseFile(String filename) throws IOException {

        File file = new File(filename);
        Scanner inputFile = new Scanner(file);

        char origin = inputFile.nextLine().charAt(0);
        FlightMap fmap = new FlightMap(origin);

        while(inputFile.hasNext()) {
            String line = inputFile.nextLine();

            char source = line.charAt(0);
            char destination = line.charAt(2);
            String edge = Character.toString(source) + Character.toString(destination);

            int price = Integer.valueOf(line.substring(4));

            fmap.addForwardEdge(edge, price);

        }

        return fmap;
    }



}
