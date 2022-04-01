package GraphADT;

import java.util.Set;

/**
 * ADT representing a graph. This class can be used with different kinds of edges to allow for different type of graph kinds.
 * @param <T> Vertex type
 * @param <E> Edge type between vertices
 */
public interface GraphADT<T, E extends Edge<T>> {

    /**
     * Check if the graph is empty.
     * Graph will be empty if it has no vertices
     *
     * @return true if graph is empty, false if it is not empty
     */
    boolean isEmpty();

    /**
     * Check whether this edge exists in the graph
     *
     * @param edge Edge that the presence of is to be checked
     * @return true if this exact edge exists in the graph, false otherwise
     */
    boolean hasEdge(E edge);

    /**
     * Check whether this vertex exists in the graph
     *
     * @param vertex Vertex to be checked
     * @return True if this vertex exists in the graph, false otherwise
     */
    boolean hasVertex(T vertex);

    /**
     * Adds the provided edge to the graph. If either vertex is not present in the graph, this method should add it as well.
     *
     * @param edge The edge to be added
     */
    void addEdge(E edge);

    /**
     * Adds the provided vertex to the graph.
     *
     * @param vertex The vertex to be added
     */
    void addVertex(T vertex);

    /**
     * Removes an edge from the graph.
     *
     * @param edge the edge to be removed
     * @return True if removal was successful, false otherwise
     */
    boolean removeEdge(E edge);

    /**
     * Removes given vertex from the graph
     *
     * @param vertex The vertex to be removed
     * @return True if removal was successful, false otherwise
     */
    boolean removeVertex(T vertex);

    /**
     * Returns the degree of the specified vertex.
     * A degree of a vertex in an undirected graph is the number of edges touching that vertex.
     * In directed graphs this method returns the sum of the "in degree" and the "out degree"
     *
     * @param vertex degree of this vertex will be returned
     * @return integer representing the degree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Integer degreeOf(T vertex);

    /**
     * Returns the inDegree of the specified vertex
     * In an undirected graph inDegree wil be equal to outDegree.
     *
     * @param vertex inDegree of this vertex will be returned
     * @return integer representing the inDegree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Integer inDegreeOf(T vertex);

    /**
     * Returns the outDegree of the specified vertex
     * In an undirected graph outDegree wil be equal to inDegree.
     *
     * @param vertex outDegree of this vertex will be returned
     * @return integer representing the outDegree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Integer outDegreeOf(T vertex);

    /**
     * Returns a set of all edges connecting the specified vertices.
     * If there are no edges between those edges, it returns an empty set.
     *
     * @return set of edges between the vertices
     * @throws java.util.NoSuchElementException if the graph does not contain either the vertex
     */
    Set<E> getEdgesBetween(T v1, T v2);

    /**
     * Get all outgoing edges of the provided vertex. In an undirected graph this will return the same result as getEdgesOf.
     *
     * @param vertex Outgoing edges of this vertex will be returned
     * @return the outgoing edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<E> getOutgoingEdgesOf(T vertex);

    /**
     * Get all ingoing edges of the provided vertex. In an undirected graph this will return the same result as getEdgesOf.
     *
     * @param vertex Ingoing edges of this vertex will be returned
     * @return the Ingoing edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<E> getIngoingEdgesOf(T vertex);

    /**
     * Get ingoing and outgoing edges of the vertex
     *
     * @param vertex All edges of this vertex will be returned
     * @return all edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<E> getEdgesOf(T vertex);

    /**
     * Returns a set of outgoing neighbours of this graph.
     * In an undirected graph this will return the same set as getIngoingNeighbours
     * @param vertex outgoing neighbours of this vertex will be returned
     * @return the set of outgoing neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<T> getOutgoingNeighboursOf(T vertex);

    /**
     * Returns a set of ingoing neighbours of this graph.
     * In an undirected graph this will return the same set as getOutgoingNeighbours
     * @param vertex ingoing neighbours of this vertex will be returned
     * @return the set of ingoing neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<T> getIngoingNeighboursOf(T vertex);

    /**
     * Returns a set of the neighbours of this graph.
     * The set contains both incoming and outgoing neighbours.
     * @param vertex neighbours of this vertex will be returned
     * @return the set of neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    Set<T> getNeighboursOf(T vertex);

    /**
     * Get all the vertices of the graph
     *
     * @return The set of all vertices of the graph
     */
    Set<T> getAllVertices();

    /**
     * Get all edges of the graph
     *
     * @return The set of all the edges of the graph
     */
    Set<E> getAllEdges();
}
