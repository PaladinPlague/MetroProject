package GraphADT;

import java.util.List;

public interface SearchAlgo<T> {
    List<T> searchIn(ADTGraph<T> graph, T from, T to);
}
