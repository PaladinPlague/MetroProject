package GraphADT;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Path finding algorithm that finds all possible shortest paths within a graph
 * @param <T> Node type
 * @param <E> Edge type between nodes
 */
public class EppsteinsAlgorithm<T, E extends Edge<T>> implements ShortestPathsAlgorithm<T, E> {
    @Override
    public List<List<E>> searchIn(GraphADT<T, E> graph, T from, T to) {

        if (!graph.hasVertex(from) || !graph.hasVertex(to) || from == to) {
            throw new IllegalArgumentException();
        }

        final List<List<EppsteinsAlgorithmNode<E>>> paths = new ArrayList<>();
        final Queue<List<EppsteinsAlgorithmNode<E>>> heap = new PriorityQueue<>(Comparator.comparingInt(this::getCost));
        final Set<List<EppsteinsAlgorithmNode<E>>> firstEdges = graph.getOutgoingEdgesOf(from).stream()
                .map(edge -> List.of(new EppsteinsAlgorithmNode<E>(edge, edge.getWeight())))
                .collect(Collectors.toSet());
        heap.addAll(firstEdges);


        while (!heap.isEmpty()) {

            // get next path to explore
            List<EppsteinsAlgorithmNode<E>> path = heap.poll();

            // check that it's not longer that the shortest path
            if (paths.size() > 0) {
                int least = paths.stream().map(List::size).min(Comparator.naturalOrder()).orElseThrow();
                if (path.size() > least) {
                    continue;
                }
            }

            // get the last node of that path
            EppsteinsAlgorithmNode<E> lastEdge = path.get(path.size() - 1);
            Optional<T> maybeLastNode = getLastNode(path, from);
            if (maybeLastNode.isEmpty()) {
                // if the path is a loop
                continue;
            }
            T lastNode = maybeLastNode.get();

            // if that node is the destination node, add the path to return paths
            if (to == lastNode) {
                paths.add(path);
                continue;
            }

            // explore the successors of the last node
            graph.getEdgesOf(lastNode).forEach(edge -> {
                List<EppsteinsAlgorithmNode<E>> copy = new ArrayList<>(path);

                EppsteinsAlgorithmNode<E> node = new EppsteinsAlgorithmNode<>(edge, lastEdge.getCost());
                if (copy.stream().noneMatch(anyNode -> anyNode.getValue().stationsMatch(node.getValue()))) {
                    copy.add(node);
                    heap.add(copy);
                }
            });
        }

        return paths.stream()
                .map(list -> list.stream()
                        .map(EppsteinsAlgorithmNode::getValue)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }

    /**
     * get the cost of the path which is the sum of all the costs of edges in this path
     *
     * @param list the path
     * @return sum of all costs of this path
     */
    int getCost(List<EppsteinsAlgorithmNode<E>> list) {
        return list.stream().map(EppsteinsAlgorithmNode::getCost).reduce(Integer::sum).orElse(0);
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
    Optional<T> getLastNode(List<EppsteinsAlgorithmNode<E>> path, T first) throws IllegalStateException, IllegalArgumentException {

        if (path.size() < 1 || !path.get(0).getValue().contains(first)) {
            throw new IllegalArgumentException();
        }

        List<EppsteinsAlgorithmNode<E>> copy = new ArrayList<>(path);
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



