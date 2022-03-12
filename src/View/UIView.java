package View;

import java.awt.event.*;
import java.awt.TextField;
import java.awt.GridLayout;
import java.util.*;
import javax.swing.*;

public class UIView implements MetroView, ActionListener {

    private JFrame frame;
    private JButton submit;
    private JPanel main;

    private TextField start, end;

    private Map<String, Runnable> commands;

    public UIView() {
        commands = new HashMap<>();

        // frame
        frame = new JFrame("Boston Metro Route-finder");

        // initialise submit button
        submit = new JButton("submit");
        submit.addActionListener(this);

        // initialise start field
        JLabel startLabel = new JLabel("Starting:");
        start = new TextField(20);

        // initialise end field
        JLabel endLabel = new JLabel("Destination:");
        end = new TextField(20);

        // initialise main panel
        main = new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        main.setLayout(new GridLayout(3, 2, 0, 5));

        // add start field
        main.add(startLabel);
        main.add(start);

        // add end field
        main.add(endLabel);
        main.add(end);

        // add submit button
        main.add(submit);

        // setup frame
        frame.setSize(200,200);
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
    }

    @Override
    public void start() {
        // show frame
        frame.setVisible(true);
    }

    @Override
    public void setUpOnDisplayGraph(Runnable OnDisplayGraph) {
        System.out.println("TODO: setUpOnDisplayGraph");
    }

    @Override
    public void setUpOnFindPath(Runnable onFindPath) {
        commands.put("path", onFindPath);
    }

    @Override
    public void setUpOnGetLine(Runnable onGetLine) {
        System.out.println("TODO: setUpOnGetLine");
    }

    @Override
    public String[] getStationsForPathFinding() {
        String startText = start.getText();
        String endText = end.getText();

        return new String[]{startText, endText};
    }

    @Override
    public String getStationForWhichLine() {
        System.out.println("TODO: getStationForWhichLine");
        return "";
    }

    @Override
    public void displayStations(List<StationData> stations) {
        System.out.println("TODO: displayStations");
    }

    @Override
    public void displayPath(List<StationData> path) {
        System.out.println("TODO: Turn this text output into a panel displayed south of main panel");
        path.forEach(station -> {
            System.out.println(station.toString());
        });
    }

    @Override
    public void alert(String input) {
        System.out.println(input);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        commands.get("path").run();
    }
}
