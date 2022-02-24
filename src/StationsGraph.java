import java.util.List;
import java.util.Set;

public class StationsGraph extends UndirectedUnweightedGraph<Station> {

    @Override
    public List<Station> findPath(Station from, Station to) {
        SearchAlgo<Station> searchAlgorithm = new AStar<>(
                (target, station) -> {
                    Set<Line> lines = target.getLines();
                    for (Line line : lines) {
                        if (station.isLine(line)) {
                            return 0;
                        }
                    }
                    return 1;
                }, this
        );
        return searchAlgorithm.search(from, to);
    }
}
