package GraphADT;

import java.util.Collection;

public interface Edge<T> {
    Collection<T> getNodes();

    boolean contains(T vertex);

    T getOther(T vertex);

    int getCost();

}
