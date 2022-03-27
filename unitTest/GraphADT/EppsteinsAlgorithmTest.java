package GraphADT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EppsteinsAlgorithmTest {

    GraphADT<Integer, Edge<Integer>> graph;
    EppsteinsAlgorithm<Integer, Edge<Integer>> algorithm;

    @BeforeEach
    void setUpEach() {
        graph = new MultiGraph<Integer, Edge<Integer>>();
        algorithm = new EppsteinsAlgorithm<Integer, Edge<Integer>>();
    }

    @Test
    void ifThereAreNoPaths_thenSearchInReturnsEmptyList() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Set<Integer> vertices2 = Set.of(1, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Set<Integer> vertices3 = Set.of(2, 4);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Set<Integer> vertices4 = Set.of(5, 6);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");
        Set<Integer> vertices5 = Set.of(6, 7);
        Edge<Integer> edge5 = new UndirectedUnweightedColouredEdge<>(vertices5, "");

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);

        List<List<Edge<Integer>>> expected = List.of();

        List<List<Edge<Integer>>> actual = algorithm.searchIn(graph, 1, 7);

        assertEquals(expected, actual);
    }

    @Test
    void ifThereIsOneShortestPath_thenSearchInReturnsThatPath() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Set<Integer> vertices2 = Set.of(1, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Set<Integer> vertices3 = Set.of(1, 4);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Set<Integer> vertices4 = Set.of(2, 4);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");
        Set<Integer> vertices5 = Set.of(4, 5);
        Edge<Integer> edge5 = new UndirectedUnweightedColouredEdge<>(vertices5, "");

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);

        List<Edge<Integer>> expected1 = List.of(edge3, edge5);

        List<List<Edge<Integer>>> expected = List.of(expected1);

        List<List<Edge<Integer>>> actual = algorithm.searchIn(graph, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    void ifThereAreTwoShortestPaths_thenSearchInReturnsBoth() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Set<Integer> vertices2 = Set.of(2, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Set<Integer> vertices3 = Set.of(2, 4);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Set<Integer> vertices4 = Set.of(3, 5);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");
        Set<Integer> vertices5 = Set.of(4, 5);
        Edge<Integer> edge5 = new UndirectedUnweightedColouredEdge<>(vertices5, "");

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);
        graph.addEdge(edge5);

        List<Edge<Integer>> expected1 = List.of(edge1, edge2, edge4);
        List<Edge<Integer>> expected2 = List.of(edge1, edge3, edge5);

        List<List<Edge<Integer>>> expected = List.of(expected1, expected2);

        List<List<Edge<Integer>>> actual = algorithm.searchIn(graph, 1, 5);

        assertEquals(expected, actual);
    }

    @Test
    void ifThereAreTwoPathsButOneIsShorter_thenSearchInReturnsTheShorterPath() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Set<Integer> vertices2 = Set.of(1, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Set<Integer> vertices3 = Set.of(3, 2);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Set<Integer> vertices4 = Set.of(2, 4);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");

        graph.addEdge(edge1);
        graph.addEdge(edge2);
        graph.addEdge(edge3);
        graph.addEdge(edge4);


        List<Edge<Integer>> expected1 = List.of(edge1, edge4);

        List<List<Edge<Integer>>> expected = List.of(expected1);

        List<List<Edge<Integer>>> actual = algorithm.searchIn(graph, 1, 4);

        assertEquals(expected, actual);
    }

    @Test
    void ifThereAreNoNodes_thenSearchInThrows() {
        assertThrows(IllegalArgumentException.class, () -> algorithm.searchIn(graph, 1, 4));
    }

    @Test
    void ifFromAndToAreTheSame_thenSearchInThrows() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");

        graph.addEdge(edge1);

        assertThrows(IllegalArgumentException.class, () -> algorithm.searchIn(graph, 1, 1));
    }

    @Test
    void ifThereIsNoPathBetweenFromAndTo_thenSearchInReturnsEmptyPath() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Set<Integer> vertices2 = Set.of(3, 4);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");

        graph.addEdge(edge1);
        graph.addEdge(edge2);

        List<List<Edge<Integer>>> expected = List.of();
        List<List<Edge<Integer>>> actual = algorithm.searchIn(graph, 1, 4);

        assertEquals(expected, actual);
    }

    @Test
    void whenPathHasOneNode_itWillReturnTheOtherNode() {
        Set<Integer> vertices = Set.of(1, 2);
        Edge<Integer> edge = new UndirectedUnweightedColouredEdge<>(vertices, "");
        Node<Edge<Integer>> edgeNode = new Node<>(edge, 1);
        List<Node<Edge<Integer>>> path = List.of(edgeNode);
        Integer actual = algorithm.getLastNode(path, 1).orElseThrow();

        assertEquals(2, actual);
    }

    @Test
    void whenPathHasZeroNodes_itWillReturnTheOtherNode() {
        List<Node<Edge<Integer>>> path = List.of();
        assertThrows(IllegalArgumentException.class, () -> algorithm.getLastNode(path, 1));
    }

    @Test
    void whenPathHasMoreThanOneNode_andPathIsContinuous_itWillReturnTheLastOne() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Node<Edge<Integer>> edgeNode1 = new Node<>(edge1, 1);

        Set<Integer> vertices2 = Set.of(2, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Node<Edge<Integer>> edgeNode2 = new Node<>(edge2, 1);

        Set<Integer> vertices3 = Set.of(4, 3);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Node<Edge<Integer>> edgeNode3 = new Node<>(edge3, 1);

        Set<Integer> vertices4 = Set.of(4, 5);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");
        Node<Edge<Integer>> edgeNode4 = new Node<>(edge4, 1);

        List<Node<Edge<Integer>>> path = List.of(edgeNode1, edgeNode2, edgeNode3, edgeNode4);
        Integer actual = algorithm.getLastNode(path, 1).orElse(null);

        assertEquals(5, actual);
    }

    @Test
    void whenPathHasMoreThanOneNode_andPathIsNotContinuous_itWillReturnTheLastOne() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Node<Edge<Integer>> edgeNode1 = new Node<>(edge1, 1);

        Set<Integer> vertices2 = Set.of(2, 0);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Node<Edge<Integer>> edgeNode2 = new Node<>(edge2, 1);

        Set<Integer> vertices3 = Set.of(4, 3);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Node<Edge<Integer>> edgeNode3 = new Node<>(edge3, 1);

        Set<Integer> vertices4 = Set.of(4, 5);
        Edge<Integer> edge4 = new UndirectedUnweightedColouredEdge<>(vertices4, "");
        Node<Edge<Integer>> edgeNode4 = new Node<>(edge4, 1);

        List<Node<Edge<Integer>>> path = List.of(edgeNode1, edgeNode2, edgeNode3, edgeNode4);
        assertThrows(IllegalStateException.class, () -> algorithm.getLastNode(path, 1));
    }

    @Test
    void whenPathHasMoreThanOneNode_andPathIsALoop_itWillReturnTheLastOne() {
        Set<Integer> vertices1 = Set.of(1, 2);
        Edge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(vertices1, "");
        Node<Edge<Integer>> edgeNode1 = new Node<>(edge1, 1);

        Set<Integer> vertices2 = Set.of(2, 3);
        Edge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(vertices2, "");
        Node<Edge<Integer>> edgeNode2 = new Node<>(edge2, 1);

        Set<Integer> vertices3 = Set.of(1, 3);
        Edge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(vertices3, "");
        Node<Edge<Integer>> edgeNode3 = new Node<>(edge3, 1);

        List<Node<Edge<Integer>>> path = List.of(edgeNode1, edgeNode2, edgeNode3);

        Integer last = algorithm.getLastNode(path, 1).orElse(null);
        assertNull(last);
    }

    @Test
    void EppsteinsAlogrithmWorks() {

        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        var edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");
        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(2);
        nodes2.add(3);

        var edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "orange");
        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(2);
        nodes3.add(4);

        var edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "red");
        graph.addEdge(edge3);

        Set<Integer> nodes4 = new HashSet<>();

        nodes4.add(3);
        nodes4.add(4);

        var edge4 = new UndirectedUnweightedColouredEdge<>(nodes4, "green");
        graph.addEdge(edge4);


        EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> algorithm = new EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        List<List<UndirectedUnweightedColouredEdge<Integer>>> result = algorithm.searchIn(graph, 1, 4);

        Assertions.assertEquals(List.of(List.of(edge1, edge3)), result);

    }
}
