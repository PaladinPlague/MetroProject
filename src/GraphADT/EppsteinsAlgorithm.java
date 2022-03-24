package GraphADT;

import java.util.*;
import java.util.stream.Collectors;

public class EppsteinsAlgorithm<T, E extends Edge<T>> implements ShortestPathsAlgorithm<T, E> {
    @Override
    public List<List<E>> searchIn(GraphADT<T, E> graph, T from, T to) {

        if (!graph.hasVertex(from) || !graph.hasVertex(to) || from == to) {
            throw new IllegalArgumentException();
        }

        final List<List<Node<E>>> paths = new ArrayList<>();
        final Queue<List<Node<E>>> heap = new PriorityQueue<>(Comparator.comparingInt(this::getCost));
        final Set<List<Node<E>>> firstEdges = graph.getOutgoingEdgesOf(from).stream()
                .map(edge -> List.of(new Node<E>(edge, edge.getWeight())))
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

    /**
     * get the cost of the path which is the sum of all the costs of edges in this path
     * @param list the path
     * @return sum of all costs of this path
     */
    int getCost(List<Node<E>> list) {
        return list.stream().map(Node::getCost).reduce(Integer::sum).orElse(0);
    }

    /**
     * Assuming that the path of edges is continuous e.i. every two successive nodes have one common vertex,
     * it will return the last one of the nodes of this path
     *
     * @param path  the list of nodes of edges in order
     * @param first the first of the vertices. It should be one of the nodes of the first edge.
     * @return Optional of the last vertex.
     * This optional will be null if there was a loop in the path.
     * @throws IllegalStateException if the path was not continuous
     */
    Optional<T> getLastNode(List<Node<E>> path, T first) throws IllegalStateException, IllegalArgumentException {

        if (path.size() < 1 || !path.get(0).getValue().contains(first)) {
            throw new IllegalArgumentException();
        }

        List<Node<E>> copy = new ArrayList<>(path);
        List<T> antiIntersection = copy.stream()
                .map(node -> node.getValue().getNodes())
                .reduce((a, b) -> {
                    List<T> intersection = new ArrayList<>(a);
                    List<T> sum = new ArrayList<>(b);
                    intersection.retainAll(b);
                    sum.addAll(a);
                    sum.removeAll(intersection);
                    return sum;
                }).orElseThrow();
        if (antiIntersection.size() > 2) throw new IllegalStateException();
        return antiIntersection.stream().filter(vertex -> vertex != first).findFirst();

    }
}

/**
 * Node path for the
 * @param <T>
 */
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
}

