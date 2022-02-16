import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// example class, this is not final implementation
public class ConsoleView implements View {

    private final Scanner inputReader;
    private final Map<String, Runnable> commands;

    public ConsoleView() {
        this.inputReader = new Scanner(System.in);
        this.commands = new HashMap<>();
    }

    private static void commandNotFoundCommand() {
        System.out.println("Command not found");
    }

    @Override
    public void start() {
        while (true) {
            System.out.println(">> Input command:");
            String input = inputReader.nextLine();
            if (input.toLowerCase().contains("exit")) {
                break;
            }
            commands.getOrDefault(input, ConsoleView::commandNotFoundCommand).run();
        }
        System.out.println("Bye");
    }


    @Override
    public void setUpOnDisplayGraphClick(Runnable onNodes) {
        commands.put("nodes", onNodes);
    }

    @Override
    public void displayStations(Collection<String> nodes) {
        nodes.forEach(System.out::println);
    }
}
