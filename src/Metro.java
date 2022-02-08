
import java.util.Collection;
import java.util.List;

/**
 * Controller class for the system
 */
public class Metro implements Runnable {
    final private ADTGraph<Station> graph;
    final private View view;

    @Override
    public void run() {
        List<Station> stations = StationReader.readStations();
        initGraph(stations);
        printGraph();
    }

    public Metro() {
        this.graph = new MyUndirectedGraph<>();
        this.view = new View();
    }

    void initGraph(Collection<Station> stations) {
        // do two next operations separately to ensure that all vertices are inserted before inserting edges

        // add vertex to the graph.txt
        stations.forEach(graph::addVertex);

        stations.forEach(station -> {
            // add edges
            Collection<Station> edges = station.findNeighboursIn(stations);
            edges.forEach(edge -> graph.addEdge(station, edge));
        });
    }

    void printGraph() {
        System.out.println(graph);
    }

    Station getStationById() {
//        TODO: custom exception for when no stations with Id exists
        return graph.getVerticesIf(station -> station.getIndex() == 20).toArray(new Station[]{})[0];
    }


}
