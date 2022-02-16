import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class Station {
    /**
     * index of the station in the file
     */
    final private int index;

    /**
     * The name of the station
     */
    final private String name;

    /**
     * Set of line that this station lies on/belongs to
     */
    final private Set<Line> lines;

    /**
     * set of adjacent Stations
     */
    final private Set<Integer> connections;

    public Station(int index, String name, Set<Line> lines, Set<Integer> connections) {
        this.index = index;
        this.name = name;
        this.lines = lines;
        this.connections = connections;
    }

    @Override
    public String toString() {
        return "Station {" + "\n index=" + index + ",\n name='" + name + '\'' + ",\n lines=" + lines + "\n}";
    }

    public Collection<Station> findNeighboursIn(Collection<Station> stations) {
        return connections.stream()
                .filter((Integer connection) -> connection != 0) // filter the 0's out - starting stations
                .map((Integer connection) -> findStationByIndex(connection, stations)) // map id's to stations
                .collect(Collectors.toCollection(ArrayList::new));
    }

    private Station findStationByIndex(Integer index, Collection<Station> stations) {
        return stations.stream().filter(
                station -> station.index == index
        ).findFirst().orElseThrow();
    }

    public boolean isLine(Line line) {
        return lines.contains(line);
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }
}

enum Line {
    GREEN, RED, ORANGE, BLUE, GREENB, GREENC, GREEND, GREENE, MATTAPAN, REDA, REDB
}