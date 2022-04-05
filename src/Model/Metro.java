package Model;

import GraphADT.*;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * The backend class for the metro system
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
     * A map from index to station name - it does not hold state of the system. Used to map indexes to station names.
     * If the station exists in a graph, it should exist here, but if a station exists here it does not have to exist in the graph
     */
    final private Map<Integer, String> stations;

    /**
     * Provide a graph to the system in this constructor to have control over the initial state of the system
     *
     * @param graph           the graph class used that will be used to find paths between stations,
     * @param searchAlgorithm the algorithm used to find all shortest paths in the graph
     */
    public Metro(GraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> graph, ShortestPathsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm) {
        this.graph = graph;
        this.searchAlgorithm = searchAlgorithm;
        this.stations = new HashMap<>();
    }

    /**
     * Fills in the graph with stations from the provided file
     *
     * @throws NullPointerException     when file does not exist
     * @throws IndexOutOfBoundsException when file format is incorrect
     * @throws NumberFormatException     when file format is incorrect
     */
    public void init(String filename) throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException {
        // read in the stations - map from index to name
        Map<Integer, String> map = StationReader.readStations(filename);
        // read in the adjacencies between stations
        Map<Set<Integer>, Set<String>> adjacencies = StationReader.readAdjacencies(filename);
        // init graph with those stations
        init(map, adjacencies);
    }

    /**
     * Fills in the graph with data provided by the parameters
     * Modifies the graph so that after the call it will contain all the stations and edges between those stations
     *
     * @throws IllegalArgumentException when any adjacency does not contain exactly two vertices
     */
    public void init(Map<Integer, String> map, Map<Set<Integer>, Set<String>> adjacencies) throws IllegalArgumentException {
        stations.putAll(map);
        map.keySet().forEach(graph::addVertex);
        adjacencies.forEach(
                (nodes, lines) -> lines.forEach(
                        line -> {
                            UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, line);
                            graph.addEdge(edge);
                        }));
    }

    /**
     * Use to look up a station in the system by its index
     * Returns the station from the system, searching by its index.
     * If no station with provided id exists, the method throws NoSuchElementException.
     *
     * @param index id of the station
     * @return Name of the station with that id from the graph
     * @throws java.util.NoSuchElementException when station with that id doesn't exist
     */
    public String getStationByIndex(Integer index) throws NoSuchElementException {
        Integer station = graph.getAllVertices().stream()
                .filter(index::equals)
                .findFirst().orElseThrow();
        return stations.get(station);
    }

    /**
     * Use to retrieve names of all the stations in the system
     *
     * @return Map from index to the name of station of all stations in the system
     */
    public Map<Integer, String> getStationsNames() {
        return graph.getAllVertices().stream()
                .collect(Collectors.toMap(Function.identity(), stations::get));
    }

    /**
     * Use to retrieve stations, the names of which contain a filter substring
     *
     * @param filterString Substring to be matched in the station's name
     * @return Map from index to the name of station of the filtered stations
     */
    public Map<Integer, String> filterStationsNames(String filterString) {
        return getStationsNames().entrySet().stream()
                .filter(s -> s.getValue().toLowerCase().contains(filterString.toLowerCase()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
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
     * Retrieve lines of a station
     *
     * @param index id of the station in the system
     * @return A set of lines of this station
     * @throws java.util.NoSuchElementException when the index is not an index of a station in the graph
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
     * @throws java.util.NoSuchElementException when either index is not an index of a station in the graph
     */
    public Set<List<Integer>> getShortestPaths(int from, int to) {

        // find all shortest paths
        final List<List<UndirectedUnweightedColouredEdge<Integer>>> edgePaths = searchAlgorithm.searchIn(graph, from, to);

        // filter those with the least amount of line changes
        final List<List<UndirectedUnweightedColouredEdge<Integer>>> filtered = filterLeastAmountOfLines(edgePaths);

        // convert from edges to nodes
        return convertToVertices(from, filtered);

    }

    private List<List<UndirectedUnweightedColouredEdge<Integer>>> filterLeastAmountOfLines(List<List<UndirectedUnweightedColouredEdge<Integer>>> edgePaths) {
        final int fewestLines = edgePaths.stream()
                .map(this::getNumberOfLines)
                .min(Comparator.naturalOrder()).orElseThrow();

        return edgePaths.stream()
                .filter(path -> getNumberOfLines(path) == fewestLines)
                .collect(Collectors.toList());
    }

    private Set<List<Integer>> convertToVertices(int from, List<List<UndirectedUnweightedColouredEdge<Integer>>> filtered) {
        final Set<List<Integer>> nodePaths = new HashSet<>();

        for (List<UndirectedUnweightedColouredEdge<Integer>> path : filtered) {
            final List<Integer> resultPath = new ArrayList<>();
            resultPath.add(from);
            Integer last = from;
            for (UndirectedUnweightedColouredEdge<Integer> edge : path) {
                Integer newLastVertex = edge.getOther(last);
                resultPath.add(newLastVertex);
                last = newLastVertex;
            }
            nodePaths.add(resultPath);
        }
        return nodePaths;
    }

    private int getNumberOfLines(List<UndirectedUnweightedColouredEdge<Integer>> path) {
        return path.stream().map(UndirectedUnweightedColouredEdge<Integer>::getColour).collect(Collectors.toSet()).size();
    }
}
