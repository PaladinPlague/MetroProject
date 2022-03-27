package GraphADT;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UndirectedUnweightedColouredEdgeTest {

    @Test
    void whenCreatingNewEdge_ifEdgeHasTwoNodes_thenDoesNotThrow() {
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        assertDoesNotThrow(() -> {
            new UndirectedUnweightedColouredEdge<>(inputNodes, color);
        });
    }

    @Test
    void whenCreatingNewEdge_ifEdgeDoesNotHaveTwoNodes_thenThrows() {
        Set<Integer> inputNodes_lessNode = Set.of(1);
        Set<Integer> inputNodes_moreNodes = Set.of(1, 2, 3);
        String color = "blue";

        assertThrows(IllegalArgumentException.class, () -> new UndirectedUnweightedColouredEdge<>(inputNodes_lessNode, color));
        assertThrows(IllegalArgumentException.class, () -> new UndirectedUnweightedColouredEdge<>(inputNodes_moreNodes, color));
    }

    @Test
    void whenEdgeExists_ifEdgeHasTwoNodes_thenGetNodesReturnsBothOfThem() {
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);
        List<Integer> returnNodes = edge.getNodes();

        assertTrue(returnNodes.containsAll(inputNodes));

    }

    @Test
    void whenEdgeExists_getColourReturnsColor() {
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);
        String returnColor = edge.getColour();

        assertEquals(returnColor, color);
    }

    @Test
    void ifGetOtherIsCalledWithOneOfTheNodesOfAnEdge_thenItReturnsTheOtherOne() {
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);
        Integer actual1 = edge.getOther(1);
        Integer actual2 = edge.getOther(2);

        assertEquals(2, actual1);
        assertEquals(1, actual2);
    }

    @Test
    void ifGetOtherIsCalledWithANodeThatDoesNotExistInThisEdge_thenItReturnsTheOtherOne() {
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);

        assertThrows(IllegalArgumentException.class, () -> edge.getOther(3));
    }

    @Test
    public void getNodesTest() {

        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        Assertions.assertTrue(edge.getNodes().containsAll(Set.of(1, 2)));
    }

    @Test
    public void containsTest() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        Assertions.assertTrue(edge.contains(1));
        Assertions.assertTrue(edge.contains(2));
    }

    @Test
    public void getOtherTest() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        Assertions.assertEquals(2, edge.getOther(1));
        Assertions.assertEquals(1, edge.getOther(2));
    }

    @Test
    public void getWeightTest() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        Assertions.assertEquals(1, edge.getWeight());
    }

    @Test
    public void getColourTest() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        Assertions.assertEquals("blue", edge.getColour());
    }

    @Test
    public void equalsTest() {
        Set<Integer> nodes = new HashSet<>();

        nodes.add(1);
        nodes.add(2);

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(nodes, "blue");
        UndirectedUnweightedColouredEdge<Integer> edge2 = new UndirectedUnweightedColouredEdge<>(nodes, "red");
        UndirectedUnweightedColouredEdge<Integer> edge3 = new UndirectedUnweightedColouredEdge<>(nodes, "blue");

        assertNotEquals(edge, edge2);
        assertEquals(edge, edge3);
    }
}