package GraphADT;

import java.util.*;

public class UndirectedUnweightedColouredEdge<T> implements Edge<T> {
    final private String colour;
    final private Set<T> nodes;

    public UndirectedUnweightedColouredEdge(Set<T> nodes, String colour) {
        this.colour = colour;
        this.nodes = nodes;
    }

    @Override
    public List<T> getNodes() {
        return new ArrayList<>(nodes);
    }

    @Override
    public boolean contains(T vertex) {
        return nodes.contains(vertex);
    }

    @Override
    public T getOther(T vertex) {
        return nodes.stream().filter(e -> e != vertex).findFirst().orElseThrow();
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

    @Override
    public String toString() {
        return "Edge {" +
                "colour='" + colour + '\'' +
                ", nodes=" + nodes +
                '}';
    }
}
