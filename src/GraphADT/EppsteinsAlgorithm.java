package GraphADT;

import java.util.*;
import java.util.stream.Collectors;

public class EppsteinsAlgorithm<T, E extends Edge<T>> implements MultiGraphSearchAlgorithm<T, E> {
    @Override
    public Set<List<T>> searchIn(MultiGraphADT<T, E> graph, T from, T to) {

        final Set<List<Node<T>>> paths = new HashSet<>();
        final PriorityQueue<List<Node<T>>> heap = new PriorityQueue<>(Comparator.comparingInt(this::getCost));
        heap.add(List.of(new Node<>(from, 0)));

        while (!heap.isEmpty()) {
            List<Node<T>> path = heap.poll();
            Node<T> last = path.get(path.size() - 1);

            if (to.equals(last.getValue())) {
                if (paths.size() > 0) {
                    int least = paths.stream().map(List::size).min(Comparator.naturalOrder()).orElse(0);
                    if (path.size() > least) {
                        break;
                    }
                }
                paths.add(path);
            }

            graph.getEdgesOf(last.getValue()).forEach(edge -> {
                ArrayList<Node<T>> copy = new ArrayList<>(path);
                T vertex = edge.getOther(last.getValue());

                Node<T> node = new Node<>(vertex, graph.getWeightOfEdge(edge) + last.getCost());
                if (!copy.contains(node)) {
                    copy.add(node);
                    heap.add(copy);
                }
            });
        }


        return paths.stream()
                .map(list -> list.stream()
                        .map(Node::getValue)
                        .collect(Collectors.toList()))
                .collect(Collectors.toSet());
    }

    int getCost(List<Node<T>> list) {
        return list.stream().map(Node::getCost).reduce(Integer::sum).orElse(0);
    }
}

class Node<T> {
    final T value;
    final int cost;

    public Node(T value, int cost) {
        this.value = value;
        this.cost = cost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node<?> node = (Node<?>) o;
        return Objects.equals(value, node.value);
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

    @Override
    public String toString() {
        return "NodeE{" +
                "value=" + value +
                ", cost=" + cost +
                '}';
    }
}

