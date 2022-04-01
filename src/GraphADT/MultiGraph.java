package GraphADT;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Graph class that allows multiple edges between the nodes granted that nodes can be distinguished from one another (edge1.equals(edge2) == false) e.g. by color or weight.
 * Vertices have to be distinguishable as well.
 *
 * @param <T> Node type
 * @param <E> Edge type
 */
public class MultiGraph<T, E extends Edge<T>> implements GraphADT<T, E> {

    /**
     * Set of all edges in the graph
     */
    final private Set<E> edgeList;

    /**
     * Set of all vertices in the graph
     */
    final private Set<T> vertices;

    /**
     * Constructs an empty graph with no vertices or edges
     */
    public MultiGraph() {
        this.edgeList = new HashSet<>();
        this.vertices = new HashSet<>();
    }

    /**
     * Check if the graph is empty.
     * Graph will be empty if it has no vertices
     *
     * @return true if graph is empty, false if it is not empty
     */
    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    /**
     * Check whether this edge exists in the graph
     *
     * @param edge Edge that the presence of is to be checked
     * @return true if this exact edge exists in the graph, false otherwise
     */
    @Override
    public boolean hasEdge(E edge) {
        return edgeList.contains(edge);
    }

    /**
     * Get all outgoing edges of the provided vertex. In an undirected graph this will return the same result as getEdgesOf.
     *
     * @param vertex Outgoing edges of this vertex will be returned
     * @return the outgoing edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<E> getOutgoingEdgesOf(T vertex) {
        return getEdgesOf(vertex);
    }

    /**
     * Get all ingoing edges of the provided vertex. In an undirected graph this will return the same result as getEdgesOf.
     *
     * @param vertex Ingoing edges of this vertex will be returned
     * @return the Ingoing edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<E> getIngoingEdgesOf(T vertex) {
        return getEdgesOf(vertex);
    }

    /**
     * Adds the provided edge to the graph. If either vertex is not present in the graph, this method adds it as well.
     *
     * @param edge The edge to be added
     */
    @Override
    public void addEdge(E edge) {
        edge.getNodes().forEach(this::addVertex);
        edgeList.add(edge);
    }

    /**
     * Check whether this vertex exists in the graph
     *
     * @param vertex Vertex to be checked
     * @return True if this vertex exists in the graph, false otherwise
     */
    @Override
    public boolean hasVertex(T vertex) {
        return vertices.contains(vertex);
    }

    /**
     * Adds the provided vertex to the graph.
     *
     * @param vertex The vertex to be added
     */
    @Override
    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    /**
     * Removes an edge from the graph.
     *
     * @param edge the edge to be removed
     * @return True if removal was successful, false otherwise
     */
    @Override
    public boolean removeEdge(E edge) {
        return edgeList.remove(edge);
    }

    /**
     * Removes given vertex from the graph
     *
     * @param vertex The vertex to be removed
     * @return True if removal was successful, false otherwise
     */
    @Override
    public boolean removeVertex(T vertex) {
        // get all edges of the node
        Set<E> edges = getEdgesOf(vertex);
        // remove all those edges from the graph
        edgeList.removeAll(edges);
        // remove the vertex from the graph
        return vertices.remove(vertex);
    }

    /**
     * Get ingoing and outgoing edges of the vertex
     *
     * @param vertex All edges of this vertex will be returned
     * @return all edges of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<E> getEdgesOf(T vertex) {
        if (!this.hasVertex(vertex)) {
            throw new NoSuchElementException();
        }
        return edgeList.stream().filter(edge -> edge.contains(vertex)).collect(Collectors.toSet());
    }

    /**
     * Returns a set of outgoing neighbours of this graph.
     * In an undirected graph this will return the same set as getIngoingNeighbours
     *
     * @param vertex outgoing neighbours of this vertex will be returned
     * @return the set of outgoing neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<T> getOutgoingNeighboursOf(T vertex) {
        return getNeighboursOf(vertex);
    }

    /**
     * Returns a set of ingoing neighbours of this graph.
     * In an undirected graph this will return the same set as getOutgoingNeighbours
     *
     * @param vertex ingoing neighbours of this vertex will be returned
     * @return the set of ingoing neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<T> getIngoingNeighboursOf(T vertex) {
        return getNeighboursOf(vertex);
    }

    /**
     * Returns a set of the neighbours of this graph.
     * The set contains both incoming and outgoing neighbours.
     *
     * @param vertex neighbours of this vertex will be returned
     * @return the set of neighbours of the provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Set<T> getNeighboursOf(T vertex) {
        return getEdgesOf(vertex).stream().map(edge -> edge.getOther(vertex)).collect(Collectors.toSet());
    }

    /**
     * Returns the degree of the specified vertex.
     * A degree of a vertex in an undirected graph is the number of edges touching that vertex.
     * In directed graphs this method returns the sum of the "in degree" and the "out degree"
     *
     * @param vertex degree of this vertex will be returned
     * @return integer representing the degree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Integer degreeOf(T vertex) {
        return getEdgesOf(vertex).size();
    }

    /**
     * Returns the inDegree of the specified vertex
     * In an undirected graph inDegree wil be equal to outDegree.
     *
     * @param vertex inDegree of this vertex will be returned
     * @return integer representing the inDegree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Integer inDegreeOf(T vertex) {
        return getIngoingEdgesOf(vertex).size();
    }

    /**
     * Returns the outDegree of the specified vertex
     * In an undirected graph outDegree wil be equal to inDegree.
     *
     * @param vertex outDegree of this vertex will be returned
     * @return integer representing the outDegree of provided vertex
     * @throws java.util.NoSuchElementException if the graph does not contain the vertex
     */
    @Override
    public Integer outDegreeOf(T vertex) {
        return getOutgoingEdgesOf(vertex).size();
    }

    /**
     * Returns a set of all edges connecting the specified vertices.
     * If there are no edges between those edges, it returns an empty set.
     *
     * @return set of edges between the vertices
     * @throws java.util.NoSuchElementException if the graph does not contain either the vertex
     */
    @Override
    public Set<E> getEdgesBetween(T v1, T v2) {
        if (!this.hasVertex(v1) || !this.hasVertex(v2)) {
            throw new IllegalArgumentException();
        }

        return edgeList.stream().filter(edge -> edge.getNodes().equals(List.of(v1, v2))).collect(Collectors.toSet());
    }


    /**
     * Get all the vertices of the graph
     *
     * @return The set of all vertices of the graph
     */
    @Override
    public Set<T> getAllVertices() {
        return vertices;
    }

    /**
     * Get all edges of the graph
     *
     * @return The set of all the edges of the graph
     */
    @Override
    public Set<E> getAllEdges() {
        return edgeList;
    }
}

