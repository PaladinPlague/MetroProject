package GraphADT;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

public class UndirectedUnweightedColouredEdge<T> implements Edge<T> {
    final private String colour;
    final private Set<T> nodes;

    public UndirectedUnweightedColouredEdge(Set<T> nodes, String colour) {
        this.colour = colour;
        this.nodes = nodes;
    }

    @Override
    public Collection<T> getNodes() {
        return nodes;
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
    public int getCost() {
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
