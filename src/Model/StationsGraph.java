package Model;

import GraphADT.ADTGraph;
import GraphADT.AStar;
import GraphADT.SearchAlgo;
import GraphADT.UndirectedUnweightedGraph;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

class StationsGraph extends UndirectedUnweightedGraph<Station> {

    private final SearchAlgo<Station> searchAlgorithm;

    /**
     *
     * @param searchAlgoProvider
     */
    public StationsGraph(Function<ADTGraph<Station>, SearchAlgo<Station>> searchAlgoProvider) {
        this.searchAlgorithm = searchAlgoProvider.apply(this);
    }

    /**
     * Default constructor with a* algorithm for finding the path
     */
    public StationsGraph() {
        final BiFunction<Station, Station, Integer> aStarHeuristic =
                (target, station) -> target.getLines().stream().anyMatch(station::isLine) ? 0 : 1;
        this.searchAlgorithm = new AStar<>(aStarHeuristic, this);
    }

    @Override
    public List<Station> findPath(Station from, Station to) {
        return searchAlgorithm.search(from, to);
    }
}
