package View;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MetroView {
    /**
     * Starts the ui
     */
    void start();

    /**
     * Call this method to set up onDisplayGraph callback
     * onDisplayGraph will be called when displayGraph action is called from the UI
     *
     * @param OnDisplayGraph action to be run when displayGraph is called from the UI
     */
    void setUpOnDisplayGraph(Runnable OnDisplayGraph);

    /**
     * Call this method to set up onFindPath
     *
     * @param onFindPath action to be run when findPath is called from the UI
     */
    void setUpOnFindPath(Runnable onFindPath);

    /**
     * Call this method to set up onGetLine
     *
     * @param onGetLine action to be run when getLine is called from the UI
     */
    void setUpOnGetLines(Runnable onGetLine);

    // Getter methods - you call these to get the values from the UI

    void setUpStations(Map<Integer, String> stations);

    /**
     * Call to get two stations that should be passed to the backend to find path between
     *
     * @return array of two string, representing the names of stations.
     * Index 0 should be the starting stations, index 1 should be the destination station
     */
    Integer[] getStationsForPathFinding();

    /**
     * Call to get a name of the station which should be passed to the backend to find
     * this stations lines
     *
     * @return string which represents the name of the station which you want to find the line of
     */
    Integer getStationForWhichLine();

    // Display methods for the view

    /**
     * Call to tell the UI to display given list of stations
     *
     * @param stations List of View.StationData to be displayed
     */
    void displayStations(Map<Integer, String> stations, Map<Integer, Set<String>> lines) throws IllegalStateException;

    /**
     * Call to tell the UI to display given list of stations
     * representing the path between first and last of the elements of this path
     *
     * @param path List of View.StationData, representing path from first element to the last element of this list
     */
    void displayPath(Set<List<String>> path);

    /**
     * Call to alert the UI with message.
     * For example: when an exception is thrown in the system, call this method to inform the user
     *
     * @param message the message that the ui should display
     */
    void alert(String message);


}
