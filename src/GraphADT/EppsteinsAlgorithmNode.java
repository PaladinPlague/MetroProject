package GraphADT;

import java.util.Objects;

/**
 * Node for the EppsteinsAlgorithm class. Keeps track of the value and the cost for the node
 *
 * @param <T>
 */
class EppsteinsAlgorithmNode<T> {
    final T value;
    final int cost;

    public EppsteinsAlgorithmNode(T value, int cost) {
        this.value = value;
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    public T getValue() {
        return value;
    }

    public int getCost() {
        return cost;
    }
}
