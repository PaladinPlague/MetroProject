package Model;

import GraphADT.MultiGraph;
import GraphADT.EppsteinsAlgorithm;
import GraphADT.UndirectedUnweightedColouredEdge;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MetroTest {

    Metro metro;

    @BeforeEach
    void setUp() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> algorithm = new EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        metro = new Metro(graph, algorithm);
    }

    @Test
    void ifGraphHasTwoShortestPaths_metroFindAllOfThem() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d"
        );
        Map<Set<Integer>, Set<String>> edges = Map.of(
                Set.of(1, 2), Set.of("red"),
                Set.of(2, 3), Set.of("red"),
                Set.of(3, 4), Set.of("red"),
                Set.of(2, 4), Set.of("red"),
                Set.of(1, 3), Set.of("red")
        );

        metro.init(map, edges);

        final List<List<Integer>> result = metro.getShortestPaths(1, 4);
        final List<List<Integer>> expected = List.of(
                List.of(1, 3, 4),
                List.of(1, 2, 4)
        );

        assertTrue(result.containsAll(expected));
    }

    @Test
    @DisplayName("If there are multiple shortest paths but some of them have less amount of metro lines, then Metro::getShortestPaths will return the ones with least amount of lines")
    void ifGraphHasTwoShortestPathWithDifferentAmountLines_metroFindsTheOneWithLeastAmountOfLines() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d"
        );
        Map<Set<Integer>, Set<String>> edges = Map.of(
                Set.of(1, 2), Set.of("red"),
                Set.of(2, 3), Set.of("red"),
                Set.of(3, 4), Set.of("red"),
                Set.of(2, 4), Set.of("red"),
                Set.of(1, 3), Set.of("blue")
        );

        metro.init(map, edges);

        final List<List<Integer>> result = metro.getShortestPaths(1, 4);
        final List<List<Integer>> expected = List.of(
                List.of(1, 2, 4)
        );

        assertTrue(result.containsAll(expected));
    }

    @Test
    void ifGetStationByIndexIsCalledWithAnIdThatExistsInTheGraph_thenItReturnsThatStationsName() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d"
        );

        metro.init(map, Map.of());

        String actual = metro.getStationByIndex(1);
        String expected = "a";

        assertEquals(expected, actual);
    }

    @Test
    void ifGetStationByIndexIsCalledWithAnIdThatDoesNotExistInTheGraph_thenItThrows() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d"
        );

        metro.init(map, Map.of());

        assertThrows(NoSuchElementException.class, () -> metro.getStationByIndex(5));
    }

    @Test
    void ifGetLinesByIndexIsCalledWithAnIdThatExistsInTheGraph_thenItReturnsAllTheLinesOfThatStation() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d",
                5, "e"
        );
        Map<Set<Integer>, Set<String>> edges = Map.of(
                Set.of(1, 2), Set.of("red"),
                Set.of(1, 3), Set.of("green"),
                Set.of(1, 4), Set.of("blue"),
                Set.of(4, 5), Set.of("blue")
        );

        metro.init(map, edges);

        final Set<String> expected1 = Set.of("red", "green", "blue");
        final Set<String> actual1 = metro.getLinesByIndex(1);

        final Set<String> expected4 = Set.of("blue");
        final Set<String> actual4 = metro.getLinesByIndex(4);

        assertEquals(expected1, actual1);
        assertEquals(expected4, actual4);
    }

    @Test
    void ifGetStationsNamesIsCalled_thenItReturnsAllStationsNames() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d",
                5, "e"
        );
        metro.init(map, Map.of());
        Map<Integer, String> actual = metro.getStationsNames();
        assertEquals(map, actual);
    }

    @Test
    void ifGetStationsLinesIsCalled_thenItReturnsAllStationsLines() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d",
                5, "e"
        );
        Map<Set<Integer>, Set<String>> edges = Map.of(
                Set.of(1, 2), Set.of("red"),
                Set.of(1, 3), Set.of("green"),
                Set.of(1, 4), Set.of("blue"),
                Set.of(4, 5), Set.of("blue")
        );

        metro.init(map, edges);

        final Map<Integer, Set<String>> expected = Map.of(
                1, Set.of("red", "green", "blue"),
                2, Set.of("red"),
                3, Set.of("green"),
                4, Set.of("blue"),
                5, Set.of("blue")
        );
        final Map<Integer, Set<String>> actual = metro.getStationsLines();

        assertEquals(expected, actual);

    }

    @Test
    void testInit() {
        Map<Integer, String> map = Map.of(
                1, "a",
                2, "b",
                3, "c",
                4, "d"
        );
        Map<Set<Integer>, Set<String>> edges = Map.of(
                Set.of(1, 2), Set.of("red", "blue"),
                Set.of(2, 3), Set.of("blue"),
                Set.of(3, 4), Set.of("green"),
                Set.of(2, 4), Set.of("green"),
                Set.of(1, 3), Set.of("yellow")
        );

        metro.init(map, edges);

        Map<Integer, String> actualNames = metro.getStationsNames();
        Map<Integer, String> expectedNames = map;

        Map<Integer, Set<String>> actualLines = metro.getStationsLines();
        Map<Integer, Set<String>> expectedLines = Map.of(
                1, Set.of("red", "blue", "yellow"),
                2, Set.of("red", "blue", "green"),
                3, Set.of("blue", "green", "yellow"),
                4, Set.of("green")
        );
        assertEquals(expectedNames, actualNames);
        assertEquals(expectedLines, actualLines);
    }
}