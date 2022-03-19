import GraphADT.*;
import Model.Metro;
import Model.Station;
import View.ConsoleView;
import View.MetroView;

public class Driver {
    public static void main(String[] args) {
        // set up dependencies
        final MultiGraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> stations = new UndirectedUnweightedMultiGraph<>();
        final MultiGraphSearchAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm = new EppsteinsAlgorithm<>();

        // init dependencies
        final Metro metro = new Metro(stations, searchAlgorithm);
        final MetroView view = new ConsoleView();

        final MetroController controller = new MetroController(view, metro);
        controller.run();
    }
}

