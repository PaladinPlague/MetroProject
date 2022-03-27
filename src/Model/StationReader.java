package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class responsible for reading in stations from the provided file
 */
class StationReader {
    public static Map<Integer, String> readStations(String filename) throws FileNotFoundException, IndexOutOfBoundsException, NumberFormatException {
        Map<Integer, String> stations = new HashMap<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                String[] data = line.split("\\s+");

                int index = Integer.parseInt(data[0]);
                String name = data[1];
                stations.put(index, name);
            }
        }
        return stations;
    }

    public static Map<Set<Integer>, List<String>> readAdjacencies(String filename) throws FileNotFoundException, IndexOutOfBoundsException, NumberFormatException {
        Map<Set<Integer>, List<String>> stations = new HashMap<>();
        File file = new File(filename);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                String[] data = line.split("\\s+");
                int index = Integer.parseInt(data[0]);
                getAdjacencyList(index, data, stations);
            }
        }
        return stations;
    }

    private static void getAdjacencyList(Integer from, String[] data, Map<Set<Integer>, List<String>> map) throws IndexOutOfBoundsException, NumberFormatException {
        for (int current = 2; current < data.length; current += 3) {
            String metroLineString = data[current].toUpperCase();

            var conn1 = Integer.parseInt(data[current + 1]);
            addLine(from, map, metroLineString, conn1);

            var conn2 = Integer.parseInt(data[current + 2]);
            addLine(from, map, metroLineString, conn2);
        }
    }

    private static void addLine(Integer from, Map<Set<Integer>, List<String>> map, String metroLineString, int to) {
        if (to != 0) {
            Set<Integer> key = Set.of(from, to);
            if (!map.containsKey(key)) {
                List<String> list = new ArrayList<>();
                map.put(key, list);
            }
            map.get(key).add(metroLineString);
        }
    }
}
