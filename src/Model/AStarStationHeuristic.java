package Model;

import GraphADT.AStartHeuristic;

public class AStarStationHeuristic implements AStartHeuristic<Station> {
    @Override
    public Integer apply(Station target, Station station) {
        return target.getLines().stream().anyMatch(station::isLine) ? 0 : 1;
    }
}
