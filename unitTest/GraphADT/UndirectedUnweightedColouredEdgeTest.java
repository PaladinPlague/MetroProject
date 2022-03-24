package GraphADT;

import org.junit.jupiter.api.Test;

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
    void ifGetOtherIsCalledWithOneOfTheNodesOfAnEdge_thenItReturnsTheOtherOne(){
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);
        Integer actual1 = edge.getOther(1);
        Integer actual2 = edge.getOther(2);

        assertEquals(2, actual1);
        assertEquals(1, actual2);
    }

    @Test
    void ifGetOtherIsCalledWithANodeThatDoesNotExistInThisEdge_thenItReturnsTheOtherOne(){
        Set<Integer> inputNodes = Set.of(1, 2);
        String color = "blue";

        UndirectedUnweightedColouredEdge<Integer> edge = new UndirectedUnweightedColouredEdge<>(inputNodes, color);

        assertThrows(IllegalArgumentException.class, ()->edge.getOther(3));
    }
}