import java.util.List;

public interface SearchAlgo<T> {
    List<T> search(ADTGraph<T> graph, T from, T to);
}
