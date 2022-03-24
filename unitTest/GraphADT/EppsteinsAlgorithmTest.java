package GraphADT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
}
