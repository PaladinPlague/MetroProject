package GraphADT;

import java.util.Set;

public interface Expandable<T> {
    Set<T> expand(T from);
}
