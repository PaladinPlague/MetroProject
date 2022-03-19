package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class responsible for reading in stations from the provided file
 */
class StationReader {
    public static List<Station> readStations(String filename) throws FileNotFoundException {
        List<Station> stations = new ArrayList<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                Station station = createStationFromString(line);
                stations.add(station);
            }
        }
        return stations;
    }

    public static Map<Integer, Map<Integer, String>> readAdjacencies(String filename) throws FileNotFoundException {
        Map<Integer, Map<Integer, String>> stations = new HashMap<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                String[] data = line.split("\\s+");
                int index = Integer.parseInt(data[0]);
                Map<Integer, String> adjacencyList = getAdjacencyList(data);
                stations.put(index, adjacencyList);
            }
        }
        return stations;
    }

    private static Map<Integer, String> getAdjacencyList(String[] data) {

        Map<Integer, String> adjacencies = new HashMap<>();

        for (int current = 2; current < data.length; current += 3) {
            String metroLineString = data[current].toUpperCase();
            var conn1 = Integer.parseInt(data[current + 1]);
            var conn2 = Integer.parseInt(data[current + 2]);

            if (conn1 != 0) {
                adjacencies.put(conn1, metroLineString);

            }
            if (conn2 != 0) {
                adjacencies.put(conn2, metroLineString);
            }
        }

        return adjacencies;
    }

    private static Station createStationFromString(String line) {
        String[] data = line.split("\\s+");

        int index = Integer.parseInt(data[0]);
        String name = data[1];

        return new Station(index, name);
    }
}
