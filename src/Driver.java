import GraphADT.ADTGraph;
import GraphADT.SearchAlgo;
import GraphADT.UndirectedUnweightedGraph;
import Model.Metro;
import Model.Station;
import Model.StationAStarSearch;
import View.ConsoleView;
import View.MetroView;

public class Driver {
    public static void main(String[] args) {
        // set up dependencies
        final SearchAlgo<Station> aStar = new StationAStarSearch();
        final ADTGraph<Station> stationsGraph = new UndirectedUnweightedGraph<>(aStar);

        // init dependencies
        final Metro metro = new Metro(stationsGraph);
        final MetroView view = new ConsoleView();

        final MetroController controller = new MetroController(view, metro);
        controller.run();
    }
}

