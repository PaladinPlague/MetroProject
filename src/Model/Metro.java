package Model;

import GraphADT.*;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Facade for the backend layer over the graphADT classes
 * Here configuration of the backend should take place - initialisation of anything that the backend will need
 */
public class Metro {
    final static String FILENAME = "bostonmetro.txt";
    /**
     * Graph representing the system
     */
    final private MultiGraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph;

    /**
     * The algorithm which finds the shortest paths in the graph
     */
    final private MultiGraphSearchAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm;

    /**
     * A map from index to station - does not hold state.
     * If the station exists in a graph, it should exist here, but if a station exists here it does not have to exist in the graph
     */
    final private Map<Integer, Station> stations;

    /**
     * Provide a graph to the system in this constructor to have control over the initial state of the system
     *
     * @param graph           the initial state of the systems graph, TODO should be empty / move instantiation here
     * @param searchAlgorithm the algorithm used to find all shortest paths in this multigraph
     */
    public Metro(MultiGraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph, MultiGraphSearchAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm) {
        this.graph = graph;
        this.searchAlgorithm = searchAlgorithm;
        this.stations = new HashMap<>();
    }

    /**
     * Fills in the graph with stations from the file bostonmetro.txt
     *
     * @throws FileNotFoundException when file does not exist
     */
    public void init() throws FileNotFoundException {
        // read in the stations
        final List<Station> stations = StationReader.readStations(FILENAME);
        final Map<Integer, Station> map = stations.stream().collect(Collectors.toMap(Station::getIndex, s -> s));
        final Map<Integer, Map<Integer, String>> adjacencies = StationReader.readAdjacencies(FILENAME);
        this.stations.putAll(map);

        // init graph with those stations
        init(adjacencies);
    }

    /**
     * Fills in the graph with data provided by the parameter
     * Modifies the graph so that after the call it will contain all the stations and edges between those stations
     */
    public void init(Map<Integer, Map<Integer, String>> adjacencies) {
        adjacencies.forEach((stationFromIndex, stationFromEdges) -> {
            stationFromEdges.forEach((stationToIndex, line) -> {
                Set<Integer> nodes = new HashSet<>();
                nodes.add(stationFromIndex);
                nodes.add(stationToIndex);
                UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, line);
                graph.addEdge(edge);
            });
        });
    }

    /**
     * Use to look up a station in the system by its index
     * Returns the station from the system, searching by its index.
     * If no station with provided id exists, the method throws NoSuchElementException.
     *
     * @param index id of the StationDiff
     * @return StationDiff with that id from the graph
     * @throws java.util.NoSuchElementException when station with that id doesn't exist
     */
    private Station getStationByIndex(int index) throws NoSuchElementException {
//        return graph.getVerticesIf(stationIndex -> stationIndex == index).stream().findFirst().orElseThrow();
        return stations.get(index);
    }

    /**
     * Use to retrieve name of the station with given id in the system
     *
     * @param index id of the station
     * @return Name of stations in the system with given id
     * @throws java.util.NoSuchElementException when station with that id doesn't exist
     */

    public String getStationNameByIndex(Integer index) throws NoSuchElementException {
        return getStationByIndex(index).getName();
    }

    /**
     * Use to retrieve names of all the stations in the system
     *
     * @return Map from index to the name of station of all stations in the system
     */
    public Map<Integer, String> getStationsNames() {
        return graph.getAllVertices()
                .stream().collect(Collectors.toMap(Function.identity(), index -> stations.get(index).getName()));
//        return stations.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, s->s.getValue().getName()));
    }

    /**
     * Use to retrieve lines of all the stations in the system
     *
     * @return Map from index to the set of lines of all the stations in the system
     */
    public Map<Integer, Set<String>> getStationsLines() {
        return graph.getAllVertices().stream()
                .collect(Collectors.toMap(
                        Function.identity(),
                        index -> graph.getEdgesOf(index).stream()
                                .map(UndirectedUnweightedColouredEdge::getColour)
                                .collect(Collectors.toSet())));
    }

    /**
     * Use to retrieve lines of a station
     *
     * @param index id of the station in the system
     * @return A set of lines of this station
     * @throws java.util.NoSuchElementException when station with that id doesn't exist
     */
    public Set<String> getLinesByIndex(Integer index) {
        return graph.getEdgesOf(index).stream()
                .map(UndirectedUnweightedColouredEdge::getColour)
                .collect(Collectors.toSet());
    }

    /**
     * Use to find path between two stations
     * If either or both of the station cannot be found by getStationByIndex,
     * method will throw NoSuchElementException
     *
     * @param from Starting station name
     * @param to   Destination station name
     * @return List of Model.StationDiff starting with from, and ending with to, representing the shortest path from "from" to "to" in the system.
     * returned Stations are in order. If no path exists null is returned
     * @throws java.util.NoSuchElementException when station with either id doesn't exist
     */
    public Set<List<Integer>> getShortestPaths(int from, int to) {

        // find all shortest paths
        final Set<List<Integer>> paths = searchAlgorithm.searchIn(graph, from, to);

        // filter those with the least amount of line changes
        final int fewestLines = paths.stream().map(path -> getNumberOfLines(this, path)).min(Comparator.naturalOrder()).orElse(0);
        return paths.stream().filter(path -> getNumberOfLines(this, path) == fewestLines).collect(Collectors.toSet());
    }

    // there should be no lines out of order e.g  A -> red, B-> redX, C-> red
    static int getNumberOfLines(Metro metro, List<Integer> path) {

        // if we have 0 or 1 nodes, return the length
        if (path.size() < 2) return path.size();

        // result array
        List<String> allLines = new ArrayList<>();

        // for every pair of nodes,
        for (int index = 1; index < path.size(); index++) {
            var prev = path.get(index - 1);
            var curr = path.get(index);
//            var prevLines = metro.getLinesByIndex(prev.getIndex());
//            var currLines = metro.getLinesByIndex();


            Set<String> intersection = new HashSet<>();
//            intersection.retainAll(currLines);

            System.out.println(intersection);

            // if no nodes share no lines
            if (intersection.size() == 1) {
                // if they share one line, add that
                allLines.add(intersection.stream().findFirst().orElseThrow());
            }
            // if they share more than one, wait
        }

        Set<String> result = new HashSet<>(allLines);
        System.out.println(result);
        return result.size();
    }
}
