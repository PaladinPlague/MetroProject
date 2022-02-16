
import java.util.Collection;
import java.util.List;

/**
 * Backend Layer over the graphADT class
 */
public class Metro {
    final private ADTGraph<Station> graph;

    public Metro() {
        this.graph = new UndirectedUnweightedGraph<>();
        List<Station> stations = StationReader.readStations();
        initGraph(stations);
    }

    private void initGraph(Collection<Station> stations) {
        // do two next operations separately to ensure that all vertices are inserted before inserting edges

        // add vertex to the graph.txt
        stations.forEach(graph::addVertex);

        stations.forEach(station -> {
            // add edges
            Collection<Station> edges = station.findNeighboursIn(stations);
            edges.forEach(edge -> graph.addEdge(station, edge));
        });
    }

//    void printGraph() {
//        System.out.println(graph);
//    }
//
//    private Station getStationById() {
//        return graph.getVerticesIf(station -> station.getIndex() == 20).toArray(new Station[]{})[0];
//    }

    public Station getStationByName(String name) {
////        TODO: custom exception for when no stations with name exists
        return graph.getVerticesIf(station -> station.getName().equals(name)).toArray(new Station[]{})[0];
    }

    public Collection<Station> getStations() {
        return graph.getVerticesIf(station -> true);
    }

    // ? Path object instead of list of strings
    public List<Station> getPath(String from, String to) {
        Station stationFrom = getStationByName(from);
        Station stationTo = getStationByName(to);
//        TODO make it return an object that tells you which lines to take or when to change the line
        return graph.findPath(stationFrom, stationTo);
    }


}
