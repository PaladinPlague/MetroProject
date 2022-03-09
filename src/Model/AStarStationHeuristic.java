package Model;

import java.util.function.BiFunction;

public class AStarStationHeuristic implements BiFunction<Station, Station, Integer> {
    @Override
    public Integer apply(Station target, Station station) {
        return target.getLines().stream().anyMatch(station::isLine) ? 0 : 1;
    }
}
