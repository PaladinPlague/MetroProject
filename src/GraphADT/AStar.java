package GraphADT;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AStar<T> implements SearchAlgo<T> {
    // function where the first argument is the goal and second is the current node, which returns the heuristic of the current node to the goal
    private final AStartHeuristic<T> heuristic;

    public AStar(AStartHeuristic<T> heuristic) {
        this.heuristic = heuristic;
    }

    @Override
    public List<T> searchIn(ADTGraph<T> graph,T from, T to) {
        final PriorityQueue<Node<T>> agenda = new PriorityQueue<>(
                Comparator.comparingInt(o -> o.getF() + o.getCost())
        );

        final List<Node<T>> visited = new ArrayList<>();

        agenda.add(new Node<>(from, 0, 0, null));

        while (agenda.peek() != null) {
            final Node<T> current = agenda.poll();

            if (current.getValue() == to) {
                List<T> path = getPath(current).stream().map(Node::getValue).collect(Collectors.toList());
                Collections.reverse(path);
                return path;
            }

            visited.add(current);

            final Function<T, Integer> getHeuristic = (station) -> this.heuristic.apply(to, station);
            final Set<Node<T>> neighbours = graph.getOutgoing(current.getValue()).stream()
                    .map((neighbour) -> new Node<>(neighbour, current.getCost() + 1, getHeuristic.apply(neighbour), current))
                    .collect(Collectors.toSet());

            for (Node<T> neighbour : neighbours) {
                if (isNotInVisited(visited, neighbour) || isBetterThanAllInVisited(visited, neighbour)) {
                    agenda.add(neighbour);
                }
            }
        }
        return null;
    }

    private List<Node<T>> getPath(Node<T> parent) {
        final List<Node<T>> result = new ArrayList<>();
        Node<T> current = parent;
        while (current != null) {
            result.add(current);
            current = current.getParent();
        }
        return result;
    }

    private boolean isNotInVisited(List<Node<T>> visited, Node<T> current) {
        return visited.stream().noneMatch(
                (node) -> current.getValue() == node.getValue()
        );
    }

    private boolean isBetterThanAllInVisited(List<Node<T>> visited, Node<T> current) {
        return visited.stream()
                .filter((node) -> current.getValue() == node.getValue())
                .allMatch((node) -> current.getF() + current.getCost() < node.getF() + node.getCost());
    }
}

class Node<T> {
    final private Node<T> parent;
    final private T value;
    final private int cost;
    final private int f;

    public Node(T value, int cost, int f, Node<T> parent) {
        this.value = value;
        this.cost = cost;
        this.f = f;
        this.parent = parent;
    }

    public int getCost() {
        return cost;
    }

    public int getF() {
        return f;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getParent() {
        return parent;
    }

    @Override
    public String toString() {
        return "GraphADT.Node{" +
                ", value=" + value +
                ", cost=" + cost +
                ", f=" + f +
                '}';
    }
}

