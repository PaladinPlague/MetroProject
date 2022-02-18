import java.util.List;
import java.util.Set;

public class StationsGraph extends UndirectedUnweightedGraph<Station> {

    @Override
    public List<Station> findPath(Station from, Station to) {
        HillClimbing<Station> searchAlgorithm = new HillClimbing<>(
                (o1, target) -> {
                    Set<Line> lines = target.getLines();
                    for (Line line : lines) {
                        if (o1.isLine(line)) {
                            return 1;
                        }
                    }
                    return -1;
                }
        );
        return searchAlgorithm.search(this, from, to);
    }
}
