package GraphADT;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class MultiGraph<T, E extends Edge<T>> implements GraphADT<T, E> {

    final private Set<E> edgeList;
    final private Set<T> vertices;


    public MultiGraph() {
        this.edgeList = new HashSet<>();
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
    public Set<E> getOutgoingEdgesOf(T vertex) {
        return getEdgesOf(vertex);
    }

    @Override
    public Set<E> getIngoingEdgesOf(T vertex) {
        return getEdgesOf(vertex);
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
    public Set<E> getEdgesOf(T vertex) {
        if (!this.hasVertex(vertex)) {
            throw new NoSuchElementException();
        }
        return edgeList.stream().filter(edge -> edge.contains(vertex)).collect(Collectors.toSet());
    }

    @Override
    public Set<T> getOutgoingNeighboursOf(T vertex) {
        return getNeighboursOf(vertex);
    }

    @Override
    public Set<T> getIngoingNeighboursOf(T vertex) {
        return getNeighboursOf(vertex);
    }

    @Override
    public Set<T> getNeighboursOf(T vertex) {
        return getEdgesOf(vertex).stream().map(edge -> edge.getOther(vertex)).collect(Collectors.toSet());
    }

    @Override
    public Integer degreeOf(T vertex) {
        return getEdgesOf(vertex).size();
    }

    @Override
    public Integer inDegreeOf(T vertex) {
        return getIngoingEdgesOf(vertex).size();
    }

    @Override
    public Integer outDegreeOf(T vertex) {
        return getOutgoingEdgesOf(vertex).size();
    }

    @Override
    public Set<E> getEdgesBetween(T v1, T v2) {
        if (!this.hasVertex(v1) || !this.hasVertex(v2)) {
            throw new IllegalArgumentException();
        }

        return edgeList.stream().filter(edge -> edge.getNodes().equals(List.of(v1, v2))).collect(Collectors.toSet());
    }

    @Override
    public Set<T> getAllVertices() {
        return vertices;
    }

    @Override
    public Set<E> getAllEdges() {
        return edgeList;
    }
}

