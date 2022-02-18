import java.util.*;
import java.util.function.Predicate;

// Generify - make comparator dependant
public class HillClimbing<T> implements SearchAlgo<T> {
    private final Comparator<T> getHeuristic;

    public HillClimbing(Comparator<T> simpleHeuristic) {
        this.getHeuristic = simpleHeuristic;
    }

    public List<T> search(ADTGraph<T> graph, T from, T to) {
        Map<T, T> cameFrom = new HashMap<>();

        List<T> agenda = new ArrayList<>(List.of(from));
        List<T> closed = new ArrayList<>();

        while (agenda.get(0) != null) {
            T current = agenda.remove(0);

            if (current == to) {
                return getPath(cameFrom, to);
            }

            Set<T> neighbours = graph.getNeighboursOf(current);

            for (T neighbour : neighbours) {
                if (!closed.contains(neighbour)) {
                    cameFrom.put(neighbour, current);
                    placeInAgenda(to, agenda, neighbour);
                }
            }

            closed.add(current);
        }

        return null;
    }

    private void placeInAgenda(T to, List<T> agenda, T neighbour) {
        if (this.getHeuristic.compare(neighbour, to) > 0) {
            // if the comparison is < 0 we add it to the beginning of the agenda
            agenda.add(0, neighbour);
        } else {
            // if the comparison is >= 0 we add it to the end of the agenda
            agenda.add(neighbour);
        }
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

