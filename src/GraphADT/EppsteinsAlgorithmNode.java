package GraphADT;

import java.util.Objects;

/**
 * Node for the EppsteinsAlgorithm class. Keeps track of the value and the cost for the node
 *
 * @param <T>
 */
class EppsteinsAlgorithmNode<T> {
    /**
     * The value of the node
     */
    final private T value;

    /**
     * Cost of the node
     */
    final private int cost;

    public EppsteinsAlgorithmNode(T value, int cost) {
        this.value = value;
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    /**
     * @return the value of the node
     */
    public T getValue() {
        return value;
    }

    /**
     * @return the cost of the node
     */
    public int getCost() {
        return cost;
    }
}
