package GraphADT;

import java.util.List;
import java.util.Set;

public interface MultiGraphSearchAlgorithm<T, E extends Edge<T>> {
    Set<List<T>> searchIn(MultiGraphADT<T, E> graph, T from, T to);
}
