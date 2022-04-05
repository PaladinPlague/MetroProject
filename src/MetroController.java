import Model.Metro;
import View.MetroView;

import java.io.FileNotFoundException;
import java.io.IOError;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class        MetroController implements Runnable {
    final MetroView view;
    final Metro metro;
    final String sourcePath;

    /**
     * @param view       view of the system
     * @param metro      the backend metro class
     * @param sourcePath the path for the source file for station data
     */
    public MetroController(MetroView view, Metro metro, String sourcePath) {
        this.view = view;
        this.metro = metro;
        this.sourcePath = sourcePath;
    }

    @Override
    public void run() {
        try {
            // initialise the backend
            metro.init(sourcePath);
            // set up communication between model and view
            setUpView();

            // start the ui
            view.start();
        } catch (NullPointerException e) {

            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("FILE DOES NOT EXIST");
            System.exit(1);
        } catch (IndexOutOfBoundsException | IllegalArgumentException e) {
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("INVALID DATA FORMAT");
            System.exit(1);
        } catch (IOError e) {

            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("ERROR READING DATA");
            System.exit(1);
        } catch (Exception e) {

            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("ERROR");
            System.exit(1);
        }
    }

    /**
     * Initialize and set up callback - communication between UI and backend
     */
    private void setUpView() {
        view.setUpOnFindPath(() -> {
            Integer[] stations = view.getStationsForPathFinding();
            if (stations == null || stations.length != 2) {
                view.alert("Please provide the names of the stations.");
            } else {
                try {
                    Integer from = stations[0];
                    Integer to = stations[1];
                    Set<List<Integer>> paths = metro.getShortestPaths(from, to);

                    // get names of the stations in order
                    Set<List<String>> names = paths.stream()
                            .map(path -> path.stream()
                                    .map(metro::getStationByIndex)
                                    .collect(Collectors.toList()))
                            .collect(Collectors.toSet());

                    view.displayPath(names);
                } catch (NoSuchElementException e) {
                    view.alert("Either one or both of the stations have a name that we couldn't find. Please check the names");
                } catch (IllegalArgumentException e) {
                    view.alert("These two stations are the same! Pick a pair that's different!");
                }
            }
        });

        view.setUpStations(metro.getStationsNames());
        view.setUpFilter( (s, b) -> {
            if(b) {
                view.setUpStartStations(metro.filterStationsNames(s));
            }
            else {
                view.setUpEndStations(metro.filterStationsNames(s));
            }
        });
    }
}
