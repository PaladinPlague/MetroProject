package Model;

import GraphADT.Expandable;
import GraphADT.SearchAlgo;
import GraphADT.UndirectedUnweightedGraph;

import java.util.List;
import java.util.Set;

public class StationsGraph extends UndirectedUnweightedGraph<Station> implements Expandable<Station> {

    private final SearchAlgo<Station> searchAlgorithm;

    /**
     * @param searchAlgorithm the search algorithm to be used e.g. A*
     */
    public StationsGraph(SearchAlgo<Station> searchAlgorithm) {
        this.searchAlgorithm = searchAlgorithm;
    }

    @Override
    public List<Station> findPath(Station from, Station to) {
        return searchAlgorithm.searchIn(this, from, to);
    }

    @Override
    public Set<Station> expand(Station from) {
        return getOutgoing(from);
    }
}
