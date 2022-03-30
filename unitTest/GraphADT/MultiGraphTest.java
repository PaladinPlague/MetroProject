package GraphADT;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class MultiGraphTest {

    MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>> graph;

    @BeforeEach
    public void setUpEach() {
        graph = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();
    }

    @Test
    public void whenGraphDoesNotHaveVertices_isEmptyReturnsTrue() {
        assertTrue(graph.isEmpty());
    }

    @Test
    public void whenGraphHasVertices_isEmptyReturnsFalse() {
        graph.addVertex(1);

        assertFalse(graph.isEmpty());
    }

    @Test
    public void whenGraphHasAnEdge_hasEdgeReturnsTrueForThatEdge() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        graph.addEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue"));

        assertTrue(graph.hasEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue")));
    }

    @Test
    public void whenGraphDoesNotHaveAnEdge_hasEdgeReturnsFalseForThatEdge() {
        Set<Integer> nodes = new HashSet<>();
        nodes.add(1);
        nodes.add(2);
        graph.addEdge(new UndirectedUnweightedColouredEdge<>(nodes, "blue"));

        Set<Integer> nodes2 = new HashSet<>();
        nodes2.add(1);
        nodes2.add(3);
        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        assertFalse(graph.hasEdge(edge2));
    }

    @Test
    public void addEdgeTest() {
        Set<Integer> nodes = new HashSet<>();

        assertEquals(0, graph.getAllEdges().size());

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        graph.addEdge(edge);

        assertEquals(Set.of(edge), graph.getAllEdges());
    }

    @Test
    public void whenGraphDoesntHaveAVertex_hasVertexReturnsFalseForThatVertex() {
        assertFalse(graph.hasVertex(1));
    }

    @Test
    public void whenGraphHasAVertex_hasVertexReturnsTrueForThatVertex() {
        graph.addVertex(1);

        assertTrue(graph.hasVertex(1));
    }

    @Test
    public void addVertexTest() {
        assertEquals(0, graph.getAllVertices().size());

        graph.addVertex(1);

        assertEquals(Set.of(1), graph.getAllVertices());
    }

    @Test
    public void removeVertexTest() {
        graph.addVertex(1);
        graph.addVertex(2);

        assertEquals(Set.of(1, 2), graph.getAllVertices());

        graph.removeVertex(1);

        assertEquals(Set.of(2), graph.getAllVertices());
    }

    @Test
    public void getEdgesOfTest() {
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

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "orange");

        graph.addEdge(edge3);


        assertEquals(Set.of(edge1, edge2), graph.getEdgesOf(2));
    }


    @Test
    public void getAllVerticesTest() {
        graph.addVertex(1);
        graph.addVertex(2);
        graph.addVertex(3);

        assertEquals(Set.of(1, 2, 3), graph.getAllVertices());
    }


    @Test
    public void getAllEdgesTest() {
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

        assertEquals(Set.of(edge1, edge2), graph.getAllEdges());
    }

    @Test
    public void whenGraphHasAnEdge_removeEdgeTestsRemovesThatEdge() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        assertFalse(graph.hasEdge(edge1));
        assertFalse(graph.hasVertex(1));
        assertFalse(graph.hasVertex(2));

        graph.addEdge(edge1);

        assertTrue(graph.hasEdge(edge1));
        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));

        assertTrue(graph.removeEdge(edge1));
        assertFalse(graph.hasEdge(edge1));
        assertTrue(graph.hasVertex(1));
        assertTrue(graph.hasVertex(2));
    }

    @Test
    public void whenGraphDoesNotHaveAnEdge_removeEdgeTestsDoesNotRemoveThatEdge() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        assertFalse(graph.hasEdge(edge1));
        assertFalse(graph.hasVertex(1));
        assertFalse(graph.hasVertex(2));

        assertFalse(graph.removeEdge(edge1));

        assertFalse(graph.hasEdge(edge1));
        assertFalse(graph.hasVertex(1));
        assertFalse(graph.hasVertex(2));
    }

    @Test
    public void getOutGoingEdgesTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(Set.of(edge1, edge2, edge3), graph.getOutgoingEdgesOf(1));
    }

    @Test
    public void getInGoingEdgesTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(Set.of(edge1, edge2, edge3), graph.getIngoingEdgesOf(1));
    }

    @Test
    public void whenEdgeDoesNotExistInTheGraph_getEdgesOfTestThrows() {
        assertThrows(NoSuchElementException.class, () -> graph.getEdgesOf(1));
    }

    @Test
    public void getOutgoingNeighboursOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(Set.of(2, 3, 4), graph.getOutgoingNeighboursOf(1));
    }

    @Test
    public void getIngoingNeighboursOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(Set.of(2, 3, 4), graph.getIngoingNeighboursOf(1));
    }

    @Test
    public void getNeighboursOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(Set.of(2, 3, 4), graph.getNeighboursOf(1));
    }

    @Test
    public void getDegreeOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(3, graph.degreeOf(1));
    }

    @Test
    public void getInDegreeOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(3, graph.inDegreeOf(1));
    }

    @Test
    public void getOutDegreeOfTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(4);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "blue");

        graph.addEdge(edge3);

        assertEquals(3, graph.outDegreeOf(1));
    }

    @Test
    public void getEdgesBetweenTest() {
        Set<Integer> nodes1 = new HashSet<>();

        nodes1.add(1);
        nodes1.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge1 = new UndirectedUnweightedColouredEdge<>(nodes1, "blue");

        graph.addEdge(edge1);

        Set<Integer> nodes2 = new HashSet<>();

        nodes2.add(1);
        nodes2.add(3);

        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes2, "blue");

        graph.addEdge(edge2);

        Set<Integer> nodes3 = new HashSet<>();

        nodes3.add(2);
        nodes3.add(1);

        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes3, "red");

        graph.addEdge(edge3);

        assertEquals(Set.of(edge1, edge3), graph.getEdgesBetween(1,2));
    }
}
