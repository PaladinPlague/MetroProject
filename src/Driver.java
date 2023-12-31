import GraphADT.*;
import Model.Metro;
import View.MetroView;
import View.UIView;

/**
 * Driver for the system. Launches the application.
 */
public class Driver {
    public static void main(String[] args) {
        final String FILENAME = "bostonmetro.txt";

        // set up dependencies
        final GraphADT<Integer, UndirectedUnweightedColouredEdge<Integer>> stations = new MultiGraph<Integer, UndirectedUnweightedColouredEdge<Integer>>();
        final ShortestPathsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>> searchAlgorithm = new EppsteinsAlgorithm<Integer, UndirectedUnweightedColouredEdge<Integer>>();

        // init dependencies
        final Metro metro = new Metro(stations, searchAlgorithm);
        final MetroView view = new UIView();
//         final MetroView view = new ConsoleView();

        final MetroController controller = new MetroController(view, metro, FILENAME);
        controller.run();
    }
}
