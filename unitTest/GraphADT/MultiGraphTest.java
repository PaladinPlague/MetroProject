package GraphADT;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;
import java.util.Set;

public class MultiGraphTest {

    @Test
    public void whenGraphDoesNotHaveVertices_isEmptyReturnsTrue() {

        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Assertions.assertTrue(graph.isEmpty());
    }

    @Test
    public void whenGraphHasVertices_isEmptyReturnsFalse() {

        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        graph.addVertex(1);

        Assertions.assertFalse(graph.isEmpty());
    }

    @Test
    public void whenGraphHasAnEdge_hasEdgeReturnsTrueForThatEdge() {

        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        graph.addEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue"));

        Assertions.assertTrue(graph.hasEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue")));
    }

    @Test
    public void whenGraphDoesNotHaveAnEdge_hasEdgeReturnsFalseForThatEdge() {

        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes = new HashSet<>();
        nodes.add(1);
        nodes.add(2);
        graph.addEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue"));

        Set<Integer> nodes2 = new HashSet<>();
        nodes2.add(1);
        nodes2.add(3);
        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        Assertions.assertFalse(graph.hasEdge(edge2));
    }

    @Test
    public void addEdgeTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes = new HashSet<>();

        Assertions.assertEquals(0, graph.getAllEdges().size());

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        graph.addEdge(edge);

        Assertions.assertEquals(Set.of(edge), graph.getAllEdges());
    }

    @Test
    public void whenGraphDoesntHaveAVertex_hasVertexReturnsFalseForThatVertex() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Assertions.assertFalse(graph.hasVertex(1));
    }

    @Test
    public void whenGraphHasAVertex_hasVertexReturnsTrueForThatVertex() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        graph.addVertex(1);

        Assertions.assertTrue(graph.hasVertex(1));
    }

    @Test
    public void addVertexTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Assertions.assertEquals(0, graph.getAllVertices().size());

        graph.addVertex(1);

        Assertions.assertEquals(Set.of(1), graph.getAllVertices());
    }

    @Test
    public void removeVertexTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        graph.addVertex(1);
        graph.addVertex(2);

        Assertions.assertEquals(Set.of(1, 2), graph.getAllVertices());

        graph.removeVertex(1);

        Assertions.assertEquals(Set.of(2), graph.getAllVertices());
    }

    @Test
    public void getEdgesOfTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(2);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "orange");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(3);
        nodes3.add(4);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes2, "orange");

        graph.addEdge(edge3);


        Assertions.assertEquals(Set.of(edge1, edge2), graph.getEdgesOf(2));
    }


    @Test
    public void getAllVerticesTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        Assertions.assertEquals(Set.of(1, 2, 3), graph.getAllVertices());
    }


    @Test
    public void getAllEdgesTest() {
        MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(2);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "orange");

        graph.addEdge(edge2);

        Assertions.assertEquals(Set.of(edge1, edge2), graph.getAllEdges());
    }
}
