import GraphADT.*;
import Model.AStarStationHeuristic;
import Model.Metro;
import Model.Station;
import View.ConsoleView;
import View.MetroView;

public class Driver {
    public static void main(String[] args) {
        // set up dependencies
        final AStartHeuristic<Station> aStarHeuristic = new AStarStationHeuristic();
        final SearchAlgo<Station> aStar = new AStar<>(aStarHeuristic);
        final ADTGraph<Station> stationsGraph = new UndirectedUnweightedGraph<>(aStar);

        // init dependencies
        final Metro metro = new Metro(stationsGraph);
        final MetroView view = new ConsoleView();

        final MetroController controller = new MetroController(view, metro);
        controller.run();
    }
}

