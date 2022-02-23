import java.util.*;

/**
 * Facade for the backend layer over the graphADT classs
 */
public class Metro {
    final private StationsGraph graph;

    /**
     * Constructor
     * Reads the stations in from the file and fills in the graph
     */
    public Metro() {
        this.graph = new StationsGraph();
    }

    /**
     * fills in the graph with stations provided by the user
     * @param stations stations that should fill the graph
     */
    public void init(Collection<Station> stations) {
        // do two next operations separately to ensure that all vertices are inserted before inserting edges

        // add vertex to the graph.txt
        stations.forEach(graph::addVertex);

        stations.forEach(station -> {
            // add edges
            Collection<Station> edges = station.findNeighboursIn(stations);
            edges.forEach(edge -> graph.addEdge(station, edge));
        });
    }

    /**
     * Use to look up a station in the system by its name
     * Returns the station from the system, searching by its name. Case is ignored.
     * If no station with provided name exists, the method throws NoSuchElementException.
     * @param name Name of the Station
     * @return Station With that name from the graph
     * @throws java.util.NoSuchElementException when station with that name doesn't exist
     */
    public Station getStationByName(String name) throws NoSuchElementException {
        return graph.getVerticesIf(station -> station.getName().equalsIgnoreCase(name)).stream().findFirst().orElseThrow();
    }

    /**
     * Use to retrieve all stations in the system
     * @return Collection of stations in the system
     */
    public Collection<Station> getStations() {
        return graph.getAllVertices();
    }

    /**
     * Use to find path between two stations
     * If either or both of the station cannot be found by getStationByName,
     * method will throw NoSuchElementException
     * @param from Starting station name
     * @param to Destination station name
     * @return List of Station starting with from, and ending with to, representing the shortest path from "from" to "to" in the system.
     * returned Stations are in order. If no path exists null is returned
     */
    public List<Station> getPath(String from, String to) {
        Station stationFrom = getStationByName(from);
        Station stationTo = getStationByName(to);
//        TODO make it return an object that tells you which lines to take or when to change the line
//        TODO: filter lines that are relevant to the path
        var path = graph.findPath(stationFrom, stationTo);
        Collections.reverse(path);
        return path;

    }
}
