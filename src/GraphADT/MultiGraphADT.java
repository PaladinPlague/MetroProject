package GraphADT;

import java.util.Collection;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

public interface MultiGraphADT<T, E extends Edge<T>> {

    /**
     * Check if the graph is empty.
     * Graph will be empty if it has no vertices
     * @return true if graph is empty, false if it is not empty
     */
    boolean isEmpty();

    /**
     * Check whether this edge exists in the graph
     * @param edge Edge that the presence of is to be checked
     * @return true if this exact edge exists in the graph, false otherwise
     */
    boolean isEdge(E edge);

    /**
     * Check whether an edge between these two nodes exists in the system
     * @param v1 One of the edges
     * @param v2 Second of the edges
     * @return True if there exists an edge between v1 and v2, false otherwise
     */
    boolean hasEdge(T v1, T v2);

    /**
     * Check whether this vertex exists in the graph
     * @param vertex Vertex to be checked
     * @return True if this vertex exists in the graph, false otherwise
     */
    boolean hasVertex(T vertex);

    /**
     * Adds the provided edge to the graph. If either vertex is not present in the graph, this method should add it as well.
     * @param edge The edge to be added
     */
    void addEdge(E edge);

    /**
     * Adds the provided vertex to the graph.
     * @param vertex The vertex to be added
     */
    void addVertex(T vertex);

    /**
     * Removes an edge from the graph.
     * @param edge the edge to be removed
     * @return True if removal was successful, false otherwise
     */
    boolean removeEdge(E edge);

    /**
     * Removes given vertex from the graph
     * @param vertex The vertex to be removed
     * @return True if removal was successful, false otherwise
     */
    boolean removeVertex(T vertex);

    /**
     * Check the weight of a given edge
     * @param edge The edge to check the weight of
     * @return the weight of the edge
     */
    Integer getWeightOfEdge(E edge);

    Set<T> getVerticesIf(Predicate<T> predicate);

    Set<T> getNeighboursOf(T vertex);

//    List<T> findPath(T from, T to);

    Set<T> getOutgoing(T vertex);

    Set<E> getEdgesOf(T vertex);

    Set<T> getIngoing(T vertex);

    Set<T> getAllVertices();

    Collection<E> getAllEdges();
}
