import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

public interface ADTGraph<N> {

    boolean isEmpty();

    boolean isEdge(N vertex1, N vertex2);


    void addEdge(N vertex1, N vertex2);

    void addVertex(N vertex);

    void removeEdge(N vertex1, N vertex2);

    boolean removeVertex(N vertex);

    Set<N> getVerticesIf(Predicate<N> predicate);

    Set<N> getNeighboursOf(N vertex);

    List<N> findPath(N from, N to);
}