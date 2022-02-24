import java.io.FileNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

public class Controller implements Runnable {

    final static String FILENAME = "bostonmetro.txt";

    final View view;
    final Metro metro;

    public Controller() {
        this.view = new ConsoleView();
        this.metro = new Metro();
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }

    @Override
    public void run() {
        try {
            // read in the stations
            List<Station> stations = StationReader.readStations(FILENAME);
            // pass them into the system and initialize it
            metro.init(stations);
            // set up communication between model and view
            setUpView();
            // TODO: assert that stations from the UI match those from the backend

            // start the ui
             view.start();
        } catch (FileNotFoundException e) {
            System.out.println("ERROR READING IN DATA");
            System.exit(1);
        }
    }

    /**
     * Initialize and set up callback - communication between UI and backend
     */
    private void setUpView() {
        view.setUpOnDisplayGraph(() -> {
            List<StationData> stations = metro.getStations().stream()
                    .map(Controller::toStationData)
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
                            .map(Controller::toStationData)
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
                Set<Line> lines = metro.getStationByName(stationName).getLines();
                view.alert(lines.toString());
            } catch (NoSuchElementException e) {
                view.alert("No Stations with this name exist in the system");
            }
        });


    }

    /**
     * Convert Backend Station class to UI StationData class
     *
     * @param station Station that is to be converted
     * @return StationData object representing Station passed in
     */
    private static StationData toStationData(Station station) {
        String stationName = station.getName();
        List<String> lines = station.getLines().stream().map(Enum::toString).collect(Collectors.toList());
        return new StationData(stationName, lines);
    }
}
