package GraphADT;

import java.util.List;

public interface ShortestPathsAlgorithm<T, E extends Edge<T>> {
    List<List<E>> searchIn(GraphADT<T, E> graph, T from, T to);
}
