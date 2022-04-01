package Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class responsible for reading in stations from the provided file
 */
class StationReader {
    /**
     * retrieve stations from the source file
     * @param filename the file in which the stations are stored. The file has to have structure of [station_index][whitespace][stations_name][line_colour][neighbour1_index][neighbour2_index].
     *                 Last three parts can be repeated multiple times, but always together. If station is starting/terminal either neighbour_index must be 0.
     * @return Map from index to station name of stations
     * @throws NumberFormatException when file does not exist
     * @throws IndexOutOfBoundsException when file format is incorrect
     * @throws NumberFormatException when file format is incorrect
     */
    public static Map<Integer, String> readStations(String filename) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException {
        Map<Integer, String> stations = new HashMap<>();
        try (Scanner scanner = new Scanner(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename)))) {
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

    /**
     * retrieve adjacencies between stations from the source file
     * @param filename the file in which the stations are stored. The file has to have structure of [station_index][whitespace][stations_name][line_colour][neighbour1_index][neighbour2_index].
     *                 Last three parts can be repeated multiple times, but always together. If station is starting/terminal either neighbour_index must be 0.
     * @return Map from a set (pair) of indexes to a set of lines that exist between the stations
     * @throws NumberFormatException when file does not exist
     * @throws IndexOutOfBoundsException when file format is incorrect
     * @throws NumberFormatException when file format is incorrect
     */
    public static Map<Set<Integer>, Set<String>> readAdjacencies(String filename) throws NullPointerException, IndexOutOfBoundsException, NumberFormatException {
        Map<Set<Integer>, Set<String>> stations = new HashMap<>();

        try (Scanner scanner = new Scanner(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream(filename)))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().strip();
                String[] data = line.split("\\s+");
                int index = Integer.parseInt(data[0]);
                getAdjacencyList(index, data, stations);
            }
        }
        return stations;
    }

    private static void getAdjacencyList(Integer from, String[] data, Map<Set<Integer>, Set<String>> map) throws IndexOutOfBoundsException, NumberFormatException {
        for (int current = 2; current < data.length; current += 3) {
            String metroLineString = data[current].toUpperCase();

            var conn1 = Integer.parseInt(data[current + 1]);
            addLine(from, map, metroLineString, conn1);

            var conn2 = Integer.parseInt(data[current + 2]);
            addLine(from, map, metroLineString, conn2);
        }
    }

    private static void addLine(Integer from, Map<Set<Integer>, Set<String>> map, String metroLineString, int to) {
        if (to != 0) {
            Set<Integer> key = Set.of(from, to);
            if (!map.containsKey(key)) {
                Set<String> list = new HashSet<>();
                map.put(key, list);
            }
            map.get(key).add(metroLineString);
        }
    }
}
