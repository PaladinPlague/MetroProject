import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;

public class HillClimbing<T> implements SearchAlgo<T> {
    // function where the first argument is the goal and second is the current node, which returns the heuristic of the current node to the goal
    private final BiFunction<T, T, Integer> heuristicFunction;

    public HillClimbing(BiFunction<T, T, Integer> simpleHeuristic) {
        this.heuristicFunction = simpleHeuristic;
    }

    public List<T> search(ADTGraph<T> graph, T from, T to) {

        final Function<T, Integer> getHeuristic = (station) -> this.heuristicFunction.apply(to, station);

        final PriorityQueue<T> agenda = new PriorityQueue<>(
                (o1, o2) -> {
                    Integer o1Heuristic = getHeuristic.apply(o1);
                    Integer o2Heuristic = getHeuristic.apply(o2);
                    return Integer.compare(o1Heuristic, o2Heuristic);
                }
        );
        final List<T> closed = new ArrayList<>();
        final Map<T, T> cameFrom = new HashMap<>();

        agenda.add(from);

        while (agenda.peek() != null) {
            T current = agenda.poll();

            if (current == to) {
                return getPath(cameFrom, to);
            }

            Set<T> neighbours = graph.getNeighboursOf(current);

            for (T neighbour : neighbours) {
                if (!closed.contains(neighbour)) {
                    cameFrom.put(neighbour, current);
                    agenda.add(neighbour);
                }
            }

            closed.add(current);
        }

        return null;
    }

    private List<T> getPath(Map<T, T> cameFrom, T parent) {
        T current = parent;
        List<T> result = new ArrayList<>();
        while (current != null) {
            result.add(current);
            current = cameFrom.get(current);
        }
        return result;
    }
}
