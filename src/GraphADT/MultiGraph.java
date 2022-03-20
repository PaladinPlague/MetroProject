package GraphADT;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MultiGraph<T, E extends Edge<T>> implements GraphADT<T, E> {

    final private List<E> edgeList;
    final private Set<T> vertices;


    public MultiGraph() {
        this.edgeList = new ArrayList<>();
        this.vertices = new HashSet<>();
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public boolean hasEdge(E edge) {
        return edgeList.contains(edge);
    }

    @Override
    public boolean hasEdgeBetween(T v1, T v2) {
        return edgeList.stream().anyMatch(e -> e.getNodes().equals(List.of(v1, v2)));
    }

    @Override
    public int getWeightOfEdge(E edge) {
        return 1;
    }

    @Override
    public Set<T> getOutgoing(T vertex) {
        return getNeighboursOf(vertex);
    }

    @Override
    public Set<T> getIngoing(T vertex) {
        return getNeighboursOf(vertex);
    }

    @Override
    public void addEdge(E edge) {
        edge.getNodes().forEach(this::addVertex);
        edgeList.add(edge);
    }

    @Override
    public boolean hasVertex(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    @Override
    public boolean removeEdge(E edge) {
        return edgeList.remove(edge);
    }

    @Override
    public boolean removeVertex(T vertex) {
        // get all edges of the node
        Set<E> edges = getEdgesOf(vertex);
        // remove all those edges from the graph
        edgeList.removeAll(edges);
        // remove the vertex from the graph
        return vertices.remove(vertex);
    }

    @Override
    public Set<T> getVerticesIf(Predicate<T> predicate) {
        return vertices.stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<E> getEdgesOf(T vertex) {
        return edgeList.stream().filter(edge -> edge.contains(vertex)).collect(Collectors.toSet());
    }

    @Override
    public Set<T> getNeighboursOf(T vertex) {
        return getEdgesOf(vertex).stream().map(edge -> edge.getOther(vertex)).collect(Collectors.toSet());
    }

    @Override
    public Set<E> getEdgesBetween(T v1, T v2) {
        return edgeList.stream().filter(edge -> edge.getNodes().equals(List.of(v1, v2))).collect(Collectors.toSet());
    }

    @Override
    public Set<T> getAllVertices() {
        return vertices;
    }

    @Override
    public Set<E> getAllEdges() {
        return new HashSet<>(edgeList);
    }


    @Override
    public String toString() {
        return "Graph {" + vertices + "}";
    }
}

