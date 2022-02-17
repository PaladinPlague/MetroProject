import java.util.*;

public class StationHillClimbing implements SearchAlgo<Station> {
    public List<Station> search(ADTGraph<Station> graph, Station from, Station to) {
        Map<Station, Station> cameFrom = new HashMap<>();

        Set<Line> lines = to.getLines();
        PriorityQueue<Station> agenda = new PriorityQueue<>((o1, o2) -> {
            for (Line line : lines) {
                if (o1.isLine(line)) {
                    return 1;
                }
            }
            return -1;
        });
        List<Station> closed = new ArrayList<>();


        agenda.add(from);
        while (agenda.peek() != null) {
            Station current = agenda.poll();

            if (current == to) {
                return getPath(cameFrom, to);
            }

            Set<Station> neighbours = graph.getNeighboursOf(current);
            for (Station neighbour : neighbours) {
                if (!closed.contains(neighbour)) {
                    cameFrom.put(neighbour, current);
                    agenda.add(neighbour);
                }
            }
            closed.add(current);
        }

        return null;
    }

    private List<Station> getPath(Map<Station, Station> cameFrom, Station parent) {
        Station current = parent;
        List<Station> result = new ArrayList<>();
        while (current != null) {
            result.add(current);
            current = cameFrom.get(current);
        }
        return result;
    }
}

