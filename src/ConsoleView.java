import java.util.*;

// example class, this is not final implementation
public class ConsoleView implements View {

    private final Scanner inputReader;
    private final Map<String, Runnable> commands;

    public ConsoleView() {
        this.inputReader = new Scanner(System.in);
        this.commands = new HashMap<>();
        this.commands.put("help", () -> {
            System.out.println("All available commands: ");
            System.out.println(commands.keySet());
            System.out.println("Type exit and press enter to exit the program");
        });
    }

    private static void commandNotFoundCommand() {
        System.out.println("Command not found\n");
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(">> Input command: \n");
            String input = inputReader.nextLine();
            if (input.toLowerCase().contains("exit")) {
                break;
            }
            commands.getOrDefault(input, ConsoleView::commandNotFoundCommand).run();
        }
        System.out.println("Bye");
    }


    @Override
    public void setUpOnDisplayGraph(Runnable OnDisplayGraph) {
        commands.put("nodes", OnDisplayGraph);
    }

    @Override
    public void setUpOnFindPath(Runnable onFindPath) {
        commands.put("path", onFindPath);
    }

    @Override
    public String[] getStationsForPathFinding() {
        System.out.println(">> Please input a starting stations name: \n");
        String start = inputReader.nextLine();
        System.out.println(">> Please input a destination stations name: \n");
        String end = inputReader.nextLine();
        return new String[]{start, end};
    }

    @Override
    public void alert(String input) {
        System.out.println(input);
    }

    @Override
    public void setUpOnGetLine(Runnable onGetLine) {
        commands.put("lines", onGetLine);
    }

    @Override
    public String getStationForWhichLine() {
        System.out.println(">> Please input stations name: \n");
        return inputReader.nextLine();
    }

    @Override
    public void displayStations(List<StationData> stations) {
        stations.forEach(System.out::println);
    }

    @Override
    public void displayPath(List<StationData> path) {
        path.forEach(System.out::println);
    }
}
