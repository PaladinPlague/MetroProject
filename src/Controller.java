import java.util.List;
import java.util.stream.Collectors;

public class Controller implements Runnable {
    @Override
    public void run() {
        Metro metro = new Metro();
        View view = new ConsoleView();

        var result = metro.getPath("Harvard", "Riverside");
        System.out.println(result);

//       ! example of how we (IMO) we should plumb back end front end
//      initialize callbacks
//        view.setUpOnDisplayGraphClick(() -> {
//            List<String> names = metro.getStations().stream()
//                    .map(Station::getName)
//                    .collect(Collectors.toList());
//            view.displayStations(names);
//        });
//
////        assert that all the stations match in backend and frontend
//        view.start();

    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.run();
    }
}
