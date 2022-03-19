import Model.Metro;
import View.MetroView;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;

public class MetroController implements Runnable {

    final MetroView view;
    final Metro metro;

    public MetroController(MetroView view, Metro metro) {
        this.view = view;
        this.metro = metro;
    }

    @Override
    public void run() {
        try {
            // initialise the backend
            metro.init();
            // set up communication between model and view
            setUpView();
            // TODO: assert that stations from the UI match those from the backend
//
            // start the ui
            view.start();
        } catch (FileNotFoundException e) {
            System.err.println(e);
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("FILE DOES NOT EXIST");
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e);
            System.err.println(e.getMessage());
            System.err.println(Arrays.toString(e.getStackTrace()));

            System.out.println("ERROR READING DATA");
            System.exit(1);
        }
    }

    /**
     * Initialize and set up callback - communication between UI and backend
     */
    private void setUpView() {
        view.setUpOnDisplayGraph(() -> view.displayStations(metro.getStationsNames(), metro.getStationsLines()));

        view.setUpOnFindPath(() -> {
            Integer[] stations = view.getStationsForPathFinding();
            if (stations.length != 2) {
                view.alert("Please provide the names of the stations");
            } else {
                try {
                    Integer from = stations[0];
                    Integer to = stations[1];
                    Set<List<Integer>> paths = metro.getShortestPaths(from, to);
                    // get names of the stations in order
                    Set<List<String>> names = paths.stream().map(path -> path.stream().map(metro::getStationNameByIndex).collect(Collectors.toList())).collect(Collectors.toSet());
                    view.displayPath(names);
                } catch (NoSuchElementException e) {
                    view.alert("Either one or both of the stations have a name that we couldn't find. Please check the names");
                }
            }
        });

        view.setUpOnGetLines(() -> {
            Integer stationIndex = view.getStationForWhichLine();
            try {
                view.alert(metro.getLinesByIndex(stationIndex).toString());
            } catch (NoSuchElementException e) {
                view.alert("No Stations with this name exist in the system");
            }
        });
    }
}
