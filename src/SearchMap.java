import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class SearchMap {

    public static void main(String[] args) throws IOException {
        if(args.length < 2) {
            System.out.println("Too few args!");
            return;
        }

        String inputFilename = args[0];
        String outputFilename = args[1];

        FlightMap map = parseFile(inputFilename);
        generateOutputFile(map,outputFilename);


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

    private static void generateOutputFile(FlightMap flightmap, String filename) {
        String output = flightmap.toString();
        try (PrintWriter out = new PrintWriter(filename)){
            out.println(output);
        }catch(FileNotFoundException e) {
            System.out.println("ERROR: Was not able to generate output file!");
        }

    }

}
