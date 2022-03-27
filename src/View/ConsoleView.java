package View;

import java.util.*;
import java.util.stream.IntStream;

// example class, this is not final implementation
public class ConsoleView implements MetroView {

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
    public Integer[] getStationsForPathFinding() {
        System.out.println(">> Please input a starting stations name: \n");
        int start = Integer.parseInt(inputReader.nextLine());
        System.out.println(">> Please input a destination stations name: \n");
        int end = Integer.parseInt(inputReader.nextLine());
        return new Integer[]{start, end};
    }

    @Override
    public void alert(String input) {
        System.out.println(input);
    }

    @Override
    public void setUpOnGetLines(Runnable onGetLine) {
        commands.put("lines", onGetLine);
    }

    @Override
    public Integer getStationForWhichLine() {
        System.out.println(">> Please input stations name: \n");
        return Integer.parseInt(inputReader.nextLine());
    }

    @Override
    public void displayStations(Map<Integer, String> stations, Map<Integer, Set<String>> lines) {
        assert stations.keySet() == lines.keySet();
        stations.keySet().forEach(index -> System.out.println(index + ". " + stations.get(index) + ", which is on lines: " + lines.get(index)));
    }

    @Override
    public void displayPath(List<List<String>> paths) {
//        assert path.size() == lines.size();
//        IntStream.range(0, path.size()).forEach(index -> System.out.println(index + ". " + path.get(index) + ", which is on lines: " + lines.get(index)));
        paths.forEach(path -> System.out.println(path + "\n"));
    }
}
