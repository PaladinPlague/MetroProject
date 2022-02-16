import java.util.Collection;

public interface View {

//  in this method you should display the ui
    void start();

//  with setUp<SomeAction> methods, we will set up callbacks
//  depending on the framework we might need to change the type of the argument
    void setUpOnDisplayGraphClick(Runnable onNodes);

//  utility methods for the view - in order to separate backend and front end
//  we should agree on one data class for transferring information about stations
//  this one probably contains too much info
    void displayStations(Collection<String> nodes);

}
