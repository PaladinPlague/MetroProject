package Model;

import GraphADT.AStar;

public class StationAStarSearch extends AStar<Station> {

    @Override
    public Integer getHeuristic(Station goal, Station current) {
        return goal.getLines().stream().anyMatch(current::isLine) ? 0 : 1;
    }
}
