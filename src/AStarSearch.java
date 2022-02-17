import java.util.*;

public class AStarSearch<T> {
    public List<T> search(ADTGraph<T> graph, T node1, T node2, Comparator<T> comparator) {
        Map<T, T> cameFrom = new HashMap<>();
        PriorityQueue<T> agenda = new PriorityQueue<T>(comparator);
        List<T> closed = new ArrayList<>();


        agenda.add(node1);
        while(agenda.peek() != null) {
            var current = agenda.poll();

            if( current == node2) {
                return getPath(cameFrom, node2);
            }

            var neighbours = graph.getNeighboursOf(current);
            for(var neighbour : neighbours) {
                if(!closed.contains(neighbour)) {
                    cameFrom.put(neighbour, current);
                    agenda.add(neighbour);
                }
            }
            closed.add(current);
        }

        return null;
    }

    private List<T> getPath(Map<T, T> cameFrom, T parent) {
        var current = parent;
        List<T> result = new ArrayList<>();
        while(current != null) {
            result.add(current);
            current = cameFrom.get(current);
        }
        return result;
    }
}

class GraphNode {



}
