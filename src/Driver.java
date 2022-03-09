import GraphADT.AStar;
import Model.AStarStationHeuristic;
import Model.Metro;
import Model.Station;
import Model.StationsGraph;
import View.ConsoleView;
import View.MetroView;

import java.util.function.BiFunction;

public class Driver {
    public static void main(String[] args) {
        // set up dependencies
        // final BiFunction<Station, Station, Integer> aStarHeuristic = (target, station) -> target.getLines().stream().anyMatch(station::isLine) ? 0 : 1;
        final AStarStationHeuristic aStarHeuristic = new AStarStationHeuristic();
        final AStar<Station> aStar = new AStar<>(aStarHeuristic);
        final StationsGraph stationsGraph = new StationsGraph(aStar);

        // init dependencies
        final Metro metro = new Metro(stationsGraph);
        final MetroView view = new ConsoleView();

        final MetroController controller = new MetroController(view, metro);
        controller.run();
    }
}

