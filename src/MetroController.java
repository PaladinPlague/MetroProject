import Model.Metro;
import Model.Station;
import View.ConsoleView;
import View.MetroView;
import View.StationData;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class MetroController implements Runnable {

    final MetroView view;
    final Metro metro;

    public MetroController() {
        this.metro = new Metro();
        this.view = new ConsoleView();
    }

    public static void main(String[] args) {
        MetroController controller = new MetroController();
        controller.run();
    }

    @Override
    public void run() {
        try {
            // initialise the backend
            metro.init();
            // set up communication between model and view
            setUpView();
            // TODO: assert that stations from the UI match those from the backend

            // start the ui
            view.start();
        } catch (FileNotFoundException e) {
            System.out.println("FILE DOES NOT EXIST");
            System.exit(1);
        } catch (Exception e) {
            System.out.println("ERROR READING DATA");
            System.exit(1);
        }
    }

    /**
     * Initialize and set up callback - communication between UI and backend
     */
    private void setUpView() {
        view.setUpOnDisplayGraph(() -> {
            List<StationData> stations = metro.getStations().stream()
                    .map(MetroController::toStationData)
                    .collect(Collectors.toList());
            view.displayStations(stations);
        });

        view.setUpOnFindPath(() -> {
            String[] stations = view.getStationsForPathFinding();
            String from = stations[0];
            String to = stations[1];
            if (from == null || to == null) {
                view.alert("Please provide the names of the stations");
            } else {
                try {
                    List<StationData> path = metro.getPath(from, to).stream()
                            .map(MetroController::toStationData)
                            .collect(Collectors.toList());
                    view.displayPath(path);
                } catch (NoSuchElementException e) {
                    view.alert("Either one or both of the stations have a name that we couldn't find. Please check the names");
                }
            }
        });

        view.setUpOnGetLine(() -> {
            String stationName = view.getStationForWhichLine();
            try {
                view.alert(metro.getStationByName(stationName).getLines().toString());
            } catch (NoSuchElementException e) {
                view.alert("No Stations with this name exist in the system");
            }
        });
    }

    /**
     * Convert Backend Model.Station class to UI View.StationData class
     *
     * @param station Model.Station that is to be converted
     * @return View.StationData object representing Model.Station passed in
     */
    private static StationData toStationData(Station station) {
        String stationName = station.getName();
        List<String> lines = station.getLines().stream().map(Enum::toString).collect(Collectors.toList());
        return new StationData(stationName, lines);
    }
}
