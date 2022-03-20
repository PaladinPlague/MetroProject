package GraphADT;

import java.util.*;
import java.util.stream.Collectors;

public class EppsteinsAlgorithm<T, E extends Edge<T>> implements ShortestPathsAlgorithm<T, E> {
    @Override
    public List<List<E>> searchIn(GraphADT<T, E> graph, T from, T to) {
        final List<List<Node<E>>> paths = new ArrayList<>();
        final Queue<List<Node<E>>> heap = new PriorityQueue<>(Comparator.comparingInt(this::getCost)); // TODO sorting
        final Set<List<Node<E>>> firstEdges = graph.getEdgesOf(from).stream()
                .map(edge -> List.of(new Node<E>(edge, graph.getWeightOfEdge(edge))))
                .collect(Collectors.toSet());
        heap.addAll(firstEdges);


        while (!heap.isEmpty()) {
            List<Node<E>> path = heap.poll();
            Node<E> lastEdge = path.get(path.size() - 1);

            Optional<T> maybeLastNode = getLastNode(path, from);
            if (maybeLastNode.isEmpty()) {
                // loop
                continue;
            }
            T lastNode = maybeLastNode.get();

            if (to == lastNode) {
                if (paths.size() > 0) {
                    int least = paths.stream().map(List::size).min(Comparator.naturalOrder()).orElseThrow();
                    if (path.size() > least) {
                        break;
                    }
                }
                paths.add(path);
                continue;
            }

            graph.getEdgesOf(lastNode).forEach(edge -> {
                List<Node<E>> copy = new ArrayList<>(path);

                Node<E> node = new Node<>(edge, lastEdge.getCost());
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
                .collect(Collectors.toList());
    }

    int getCost(List<Node<E>> list) {
        return list.stream().map(Node::getCost).reduce(Integer::sum).orElse(0);
    }

    Optional<T> getLastNode(List<Node<E>> path, T first) {
        List<Node<E>> copy = new ArrayList<>(path);
        List<T> antiIntersection = copy.stream().map(node -> node.getValue().getNodes()).reduce((a, b) -> {
            List<T> intersection = new ArrayList<>(a);
            List<T> sum = new ArrayList<>(b);
            intersection.retainAll(b);
            sum.addAll(a);
            sum.removeAll(intersection);
            return sum;
        }).orElseThrow();
        return antiIntersection.stream().filter(vertex -> vertex != first).findFirst();

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
        return "Node {" +
                "value=" + value +
                ", cost=" + cost +
                '}';
    }
}

