package GraphADT;

import java.util.List;

public interface SearchAlgo<T> {
    List<T> searchIn(Expandable<T> graph, T from, T to);
}
