package GraphADT;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

// Class for undirected, unweighted graphs
public class UndirectedUnweightedGraph<T> implements ADTGraph<T> {
    final private Map<T, Set<T>> adjMatrix;
    private final SearchAlgo<T> searchAlgorithm;


    public UndirectedUnweightedGraph(SearchAlgo<T> searchAlgorithm) {
        this.adjMatrix = new HashMap<>();
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public boolean isEmpty() {
        return adjMatrix.isEmpty();
    }

    @Override
    public boolean isEdge(T vertex1, T vertex2) {
        // assume that an edge between v1 and v2 will be stored in the matrix as both in v1 and v2 set of neighbours
        return adjMatrix.containsKey(vertex1) && adjMatrix.get(vertex1).contains(vertex2);
    }

    @Override
    public Integer getWeightOfEdge(T vertex1, T vertex2) {
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
    public void addEdge(T vertex1, T vertex2) {
        addVertex(vertex1);
        addVertex(vertex2);
        adjMatrix.get(vertex1).add(vertex2);
        adjMatrix.get(vertex2).add(vertex1);
    }

    @Override
    public boolean isVertex(T vertex) {
        return !adjMatrix.containsKey(vertex);
    }

    @Override
    public void addVertex(T vertex) {
        if (!adjMatrix.containsKey(vertex)) {
            adjMatrix.put(vertex, new HashSet<>());
        }
    }

    @Override
    public void removeEdge(T vertex1, T vertex2) {
        adjMatrix.get(vertex1).remove(vertex2);
        adjMatrix.get(vertex2).remove(vertex1);
    }

    @Override
    public boolean removeVertex(T vertex) {
        // find all neighbours of this vertex
        Set<T> neighbours = getNeighboursOf(vertex);
        // remove all adjacent edges
        neighbours.forEach((neighbour) -> removeEdge(vertex, neighbour));
        // remove the node
        return adjMatrix.remove(vertex) != null;
    }

    @Override
    public Set<T> getVerticesIf(Predicate<T> predicate) {
        return adjMatrix.keySet().stream()
                .filter(predicate)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<T> getNeighboursOf(T vertex) {
        return adjMatrix.get(vertex);
    }

    @Override
    public Set<T> getAllVertices() {
        return adjMatrix.keySet();
    }

    @Override
    public List<T> findPath(T from, T to) {
        return searchAlgorithm.searchIn(this, from, to);
    }

    @Override
    public String toString() {
        return "Graph {" +
                adjMatrix.entrySet().stream()
                        .map((entry) -> entry.getKey() + ":" + entry.getValue() + "\n\n")
                        .collect(Collectors.toList()) +
                "}";
    }
}
