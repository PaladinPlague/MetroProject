package GraphADT;

import java.util.List;

public interface Edge<T> {
    List<T> getNodes();

    boolean contains(T vertex);

    T getOther(T vertex);

    int getWeight();
}
