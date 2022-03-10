package GraphADT;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.function.Predicate;

// TODO: add possibility of weights to the graph
public interface ADTGraph<T> {

    boolean isEmpty();

    boolean isEdge(T vertex1, T vertex2);

    boolean isVertex(T vertex);

    void addEdge(T vertex1, T vertex2);

    void addVertex(T vertex);

    void removeEdge(T vertex1, T vertex2);

    boolean removeVertex(T vertex);

    Integer getWeightOfEdge(T vertex1, T vertex2);

    Set<T> getVerticesIf(Predicate<T> predicate) throws NoSuchElementException;

    Set<T> getNeighboursOf(T vertex);

    List<T> findPath(T from, T to);

    Set<T> getOutgoing(T vertex);

    Set<T> getIngoing(T vertex);

    Set<T> getAllVertices();
}