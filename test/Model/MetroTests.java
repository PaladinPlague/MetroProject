package Model;

import GraphADT.MultiGraphADT;
import GraphADT.UndirectedUnweightedColouredEdge;
import GraphADT.UndirectedUnweightedMultiGraph;
import org.junit.jupiter.api.Test;

import java.util.Set;


public class MetroTests {

    @Test
    public void getStationByNameTest() {

    }

    @Test
    public void getPathTest() {

    }

    @Test
    public void getNumberOfLinesTest() {
        var stationZ = new Station(0, "Z");
        var stationA = new Station(1, "A");
        var stationB = new Station(2, "B");
        var stationC = new Station(3, "C");
        var stationD = new Station(4, "D");
        var stationE = new Station(5, "E");
        var stationF = new Station(6, "F");
        var stationG = new Station(7, "G");
        var stationH = new Station(8, "H");
        var stationI = new Station(9, "I");
        var stationJ = new Station(10, "J");

        UndirectedUnweightedColouredEdge<Station> ZA = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> AB = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> BC = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> CD = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> DE = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> EF = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> FG = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");
        UndirectedUnweightedColouredEdge<Station> GZ = new UndirectedUnweightedColouredEdge<>(Set.of(stationA, stationZ), "r");

        MultiGraphADT<Station, UndirectedUnweightedColouredEdge<Station>> graph = new UndirectedUnweightedMultiGraph<>();
//        Metro metro = new Metro(graph, searchAlgorithm);


//        var res = Metro.getNumberOfLines(metro, stationList2);
//        System.out.println(res);
    }

}
