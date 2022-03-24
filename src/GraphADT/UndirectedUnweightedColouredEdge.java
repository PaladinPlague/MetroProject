package GraphADT;

import java.util.*;

/**
 * Class representing an edge between two nodes of type T, that is unweighted, undirected and has a colour.
 * This edge is undirected which means that getNodes doesn't care about the ordering of the nodes.
 * This edge is unweighted which means that getWeight will always return 1
 * @param <T> type of nodes that this edge is connecting
 */
public class UndirectedUnweightedColouredEdge<T> implements Edge<T> {
    final private String colour;
    final private Set<T> nodes;

    /**
     * Constructor for this class.
     * @param nodes Set of nodes of the edge. Has to have length of 2.
     * @param colour The colour of this edge
     * @throws IllegalArgumentException if nodes does not have length 2.
     */
    public UndirectedUnweightedColouredEdge(Set<T> nodes, String colour) throws IllegalArgumentException {
        if (nodes.size() != 2) throw new IllegalArgumentException();
        this.colour = colour;
        this.nodes = nodes;
    }

    /**
     *
     * @return list of nodes of this edge. Since this edge is undirected, the ordering does not reflect the direction of the edge
     */
    @Override
    public List<T> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public boolean contains(T node) {
        return nodes.contains(node);
    }

    @Override
    public T getOther(T node) throws IllegalArgumentException {
        if (!this.contains(node)) throw new IllegalArgumentException();
        return nodes.stream().filter(e -> e != node).findFirst().orElseThrow();
    }

    @Override
    public int getWeight() {
        return 1;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(colour, nodes);
    }
}
