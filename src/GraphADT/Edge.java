package GraphADT;

import java.util.List;

/**
 * This class represents an edge in a GraphADT
 * Implementations of this class can add additional properties e.g. colouring of the edge, or direction
 *
 * @param <T> Type of the node that this edge connects
 */
public interface Edge<T> {
    /**
     * @return A list of nodes of this edge. Length of this list should always be 2.
     * If the edge is directed, the first node should be 'from' and the second node should be 'to'.
     */
    List<T> getNodes();

    /**
     * @param node the node which is checked to exist in this edge
     * @return true if the one of the nodes of this edge is the provided node
     */
    boolean contains(T node);

    /**
     * Get the other of the two nodes of this edge
     * e.g. when this edge has two nodes: "a", "b", getOther("a") will return "b", and getOther("b") will return "a"
     *
     * @param node one of the two nodes of this edge
     * @return returns the other node of this edge
     * @throws IllegalArgumentException when the edge does not contain the provided node
     */
    T getOther(T node) throws IllegalArgumentException;

    /**
     *
     * @return The weight of this edge. Should return 1 if the edge is unweighted.
     */
    int getWeight();
}
