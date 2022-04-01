package GraphADT;

import java.util.*;

/**
 * Class representing an edge between two nodes of type T, that is unweighted, undirected and has a colour.
 * This edge is undirected which means that getNodes doesn't care about the ordering of the nodes.
 * This edge is unweighted which means that getWeight will always return 1
 *
 * @param <T> type of nodes that this edge is connecting
 */
public class UndirectedUnweightedColouredEdge<T> implements Edge<T> {
    final private String colour;
    final private Set<T> nodes;

    /**
     * Constructor for this class.
     *
     * @param nodes  Set of nodes of the edge. Has to have length of 2.
     * @param colour The colour of this edge
     * @throws IllegalArgumentException if nodes does not have length 2.
     */
    public UndirectedUnweightedColouredEdge(Set<T> nodes, String colour) throws IllegalArgumentException {
        if (nodes.size() != 2) throw new IllegalArgumentException();
        this.colour = colour;
        this.nodes = nodes;
    }

    /**
     * @return list of nodes of this edge. Since this edge is undirected, the ordering does not reflect the direction of the edge
     */
    @Override
    public List<T> getNodes() {
        return new ArrayList<>(nodes);
    }

    /**
     * @param node the node which is checked to be in this edge
     * @return true if the one of the nodes of this edge is the provided node
     */
    @Override
    public boolean contains(T node) {
        return nodes.contains(node);
    }

    /**
     * Get the other of the two nodes of this edge
     * e.g. when this edge has two nodes: "a", "b", getOther("a") will return "b", and getOther("b") will return "a"
     *
     * @param node one of the two nodes of this edge
     * @return returns the other node of this edge
     * @throws IllegalArgumentException when the edge does not contain the provided node
     */
    @Override
    public T getOther(T node) throws IllegalArgumentException {
        if (!this.contains(node)) throw new IllegalArgumentException();
        return nodes.stream().filter(e -> e != node).findFirst().orElseThrow();
    }

    /**
     * @return The weight of this edge. Since this edge is unweighted, it always returns 1.
     */
    @Override
    public int getWeight() {
        return 1;
    }

    /**
     * get the colour of this edge
     * @return the colour of this edge
     */
    public String getColour() {
        return colour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UndirectedUnweightedColouredEdge<?> that = (UndirectedUnweightedColouredEdge<?>) o;
        return colour.equals(that.colour) && nodes.equals(that.nodes);
    }

    /**
     * Check if the two nodes are between the same two nodes
     *
     * @param other the other node to compare against
     * @return true if the nodes is between the same nodes
     */
    public boolean stationsMatch(Edge<T> other) {
        return this.getNodes().equals(other.getNodes());
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, nodes);
    }
}
