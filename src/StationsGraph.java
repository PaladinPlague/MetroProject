import java.util.List;
import java.util.Set;

public class StationsGraph extends UndirectedUnweightedGraph<Station> {

    @Override
    public List<Station> findPath(Station from, Station to) {
        SearchAlgo<Station> searchAlgorithm = new HillClimbing<>(
                (target, station) -> {
                    Set<Line> lines = target.getLines();
                    for (Line line : lines) {
                        if (station.isLine(line)) {
                            return 1;
                        }
                    }
                    return -1;
                }
        );
        return searchAlgorithm.search(this, from, to);
    }
}
