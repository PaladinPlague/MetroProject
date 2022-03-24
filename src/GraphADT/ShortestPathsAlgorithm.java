package GraphADT;

import java.util.List;

/**
 * Algorithm for finding the shortest paths in a graph
 * @param <T> Vertex type
 * @param <E> Edge type
 */
public interface ShortestPathsAlgorithm<T, E extends Edge<T>> {

    /**
     * Find a list of shortest paths starting at from and arriving at to.
     *
     * @param graph graph to find the paths in
     * @param from starting vertex
     * @param to finishing vertex
     * @return A list of shortest paths between from and to. If there exists no path between from and to, this methods returns an empty list.
     * @throws IllegalArgumentException when graph does not contain from or to vertices
     */
    List<List<E>> searchIn(GraphADT<T, E> graph, T from, T to) throws IllegalArgumentException, IllegalStateException;
}
