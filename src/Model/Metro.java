package Model;

import GraphADT.ADTGraph;
import GraphADT.SearchAlgo;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Facade for the backend layer over the graphADT classes
 * Here configuration of the backend should take place - initialisation of anything that the backend will need
 */
public class Metro {
    final static String FILENAME = "bostonmetro.txt";
    final private ADTGraph<Station> graph;

    /**
     * No argument constructor
     * By calling this version of the constructor the system will not contain any stations
     * and will use (default) A* algorithm
     * To fill the system with stations use one init method's overrides
     */
//    public Metro() {
//        this.graph = new StationsGraph();
//    }

    /**
     * Provide a graph to the system in this constructor to have control over the initial state of the system
     *
     * @param graph the initial state of the systems graph
     */
    public Metro(ADTGraph<Station> graph) {
        this.graph = graph;
    }

    /**
     * Fills in the graph with stations from the file bostonmetro.txt
     *
     * @throws FileNotFoundException when file does not exist
     */
    public void init() throws FileNotFoundException {
        // read in the stations
        final List<Station> stations = StationReader.readStations(FILENAME);
        // init graph with those stations
        init(stations);
    }

    /**
     * Fills in the graph with data provided by the parameter
     * Modifies the graph so that after the call it will contain all the stations and edges between those stations
     */
    public void init(List<Station> stations) {
        // do two next operations separately to ensure that all vertices are inserted before inserting edges
        // add vertices to the graph
        stations.forEach(graph::addVertex);

        stations.forEach(station -> {
            // add edges
            Collection<Station> neighbours = station.findNeighboursIn(stations);
            neighbours.forEach(neighbour -> graph.addEdge(station, neighbour));
        });
    }

    /**
     * Use to look up a station in the system by its name
     * Returns the station from the system, searching by its name. Case is ignored.
     * If no station with provided name exists, the method throws NoSuchElementException.
     *
     * @param index index of the Model.Station
     * @return Model.Station With that name from the graph
     * @throws java.util.NoSuchElementException when station with that name doesn't exist
     */
    private Station getStationByIndex(int index) throws NoSuchElementException {
        // TODO: handle more than one
        return graph.getVerticesIf(station -> station.getIndex() == index).stream().findFirst().orElseThrow();
    }

    /**
     * Use to retrieve name of the station with given id in the system
     *
     * @param index id of the station
     * @return Name of stations in the system with given id
     */

    public String getStationNameByIndex(Integer index) throws NoSuchElementException {
        return getStationByIndex(index).getName();
    }

    /**
     * Use to retrieve names of the stations in the system
     *
     * @return Map from index to the name of station of all stations in the system
     */
    public Map<Integer, String> getStationsNames() {
        return graph.getAllVertices()
                .stream().collect(Collectors.toMap(Station::getIndex, Station::getName));
    }

    public Map<Integer, Set<String>> getStationsLines() {
        return graph.getAllVertices()
                .stream().collect(Collectors.toMap(Station::getIndex, Station::getLines));
    }

    public Set<String> getLinesByIndex(Integer index) {
        return getStationByIndex(index).getLines();
    }

    /**
     * Use to find path between two stations
     * If either or both of the station cannot be found by getStationByName,
     * method will throw NoSuchElementException
     *
     * @param from Starting station name
     * @param to   Destination station name
     * @return List of Model.Station starting with from, and ending with to, representing the shortest path from "from" to "to" in the system.
     * returned Stations are in order. If no path exists null is returned
     */
    public List<Integer> getPath(int from, int to) {
        Station stationFrom = getStationByIndex(from);
        Station stationTo = getStationByIndex(to);
//        TODO make it return an object that tells you which lines to take or when to change the line
//        TODO: filter lines that are relevant to the path

        final List<Station> path = graph.findPath(stationFrom, stationTo);
        return path.stream().map(Station::getIndex).collect(Collectors.toList());
    }
}
