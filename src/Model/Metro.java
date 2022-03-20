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
    /**
     * Graph representing the system
     */
    final private GraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph;

    /**
     * The algorithm which finds the shortest paths in the graph
     */
    final private ShortestPathsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm;

    /**
     * A map from index to station - does not hold state.
     * If the station exists in a graph, it should exist here, but if a station exists here it does not have to exist in the graph
     */
    final private Map<Integer, String> stations;

    /**
     * Provide a graph to the system in this constructor to have control over the initial state of the system
     *
     * @param graph           the initial state of the systems graph, TODO should be empty / move instantiation here
     * @param searchAlgorithm the algorithm used to find all shortest paths in this multigraph
     */
    public Metro(GraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph, ShortestPathsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm) {
        this.graph = graph;
        this.searchAlgorithm = searchAlgorithm;
        this.stations = new HashMap<>();
    }

    /**
     * Fills in the graph with stations from the file bostonmetro.txt
     *
     * @throws FileNotFoundException when file does not exist
     */
    public void init(String filename) throws FileNotFoundException {
        // read in the stations - map from index to name
        Map<Integer, String> map = StationReader.readStations(filename);
        // init graph with those stations
        Map<Set<Integer>, List<String>> adjacencies = StationReader.readAdjacencies(filename);
        init(map, adjacencies);
    }

    /**
     * Fills in the graph with data provided by the parameter
     * Modifies the graph so that after the call it will contain all the stations and edges between those stations
     */
    public void init(Map<Integer, String> map, Map<Set<Integer>, List<String>> adjacencies) {
        // TODO: assert integrity
        stations.putAll(map);
        adjacencies.forEach(
                (nodes, lines) -> lines.forEach(line -> {
                    UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, line);
                    graph.addEdge(edge);
                }));
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
    private String getStationByIndex(int index) throws NoSuchElementException {
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
        return getStationByIndex(index);
    }

    /**
     * Use to retrieve names of all the stations in the system
     *
     * @return Map from index to the name of station of all stations in the system
     */
    public Map<Integer, String> getStationsNames() {
        return graph.getAllVertices()
                .stream().collect(Collectors.toMap(Function.identity(), stations::get));
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
    public List<List<Integer>> getShortestPaths(int from, int to) {

        // find all shortest paths
        final List<List<UndirectedUnweightedColouredEdge<Integer>>> edgePaths = searchAlgorithm.searchIn(graph, from, to);

        // filter those with the least amount of line changes
        final int fewestLines = edgePaths.stream()
                .map(this::getNumberOfLines)
                .min(Comparator.naturalOrder()).orElse(0);

        final List<List<UndirectedUnweightedColouredEdge<Integer>>> filtered =
                edgePaths.stream()
                        .filter(path -> getNumberOfLines(path) == fewestLines)
                        .collect(Collectors.toList());

        final List<List<Integer>> nodePaths = new ArrayList<>();

        for (List<UndirectedUnweightedColouredEdge<Integer>> path : filtered) {
            final List<Integer> resultPath = new ArrayList<>();
            resultPath.add(from);
            Integer last = from;
            for (UndirectedUnweightedColouredEdge<Integer> integerUndirectedUnweightedColouredEdge : path) {
                Integer newLast = integerUndirectedUnweightedColouredEdge.getOther(last);
                resultPath.add(newLast);
                last = newLast;
            }
            nodePaths.add(resultPath);
        }
        return nodePaths;

    }

    // there should be no lines out of order e.g  A -> red, B-> redX, C-> red
    int getNumberOfLines(List<UndirectedUnweightedColouredEdge<Integer>> path) {
        return path.stream().map(UndirectedUnweightedColouredEdge<Integer>::getColour).collect(Collectors.toSet()).size();
    }
}
