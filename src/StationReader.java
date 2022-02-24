import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Class responsible for reading in stations from the provided file
 */
public class StationReader {
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

    private static Station createStationFromString(String line) {
        String[] data = line.split("\\s+");

        int index = Integer.parseInt(data[0]);
        String name = data[1];
        Set<Line> lines = new HashSet<>();
        Set<Integer> connections = new HashSet<>();

        for (int current = 2; current < data.length; current += 3) {
            String metroLineString = data[current];
            Line metroLine = Line.valueOf(metroLineString.toUpperCase());
            lines.add(metroLine);
            var conn1 = Integer.parseInt(data[current + 1]);
            var conn2 = Integer.parseInt(data[current + 2]);
            connections.add(conn1);
            connections.add(conn2);
        }

        return new Station(index, name, lines, connections);
    }
}
