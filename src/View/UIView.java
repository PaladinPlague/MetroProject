package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class UIView implements MetroView {

    JFrame frame;
    JButton findPathButton;
    JPanel main;
    JComboBox<Map.Entry<Integer, String>> start, end;
    Map<String, Runnable> commands;

    public UIView() {
        commands = new HashMap<>();
        build();
    }

    private void build() {
        // frame
        frame = new JFrame("Boston Metro Route-finder");

        // initialise submit button
        findPathButton = new JButton("Find Path");

        // initialise start field
        JLabel startLabel = new JLabel("Starting:");
        start = new JComboBox<>();

        // initialise end field
        JLabel endLabel = new JLabel("Destination:");
        end = new JComboBox<>();

        // initialise main panel
        main = new JPanel();
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        try {
            BufferedImage bufferedImage = ImageIO.read(new File("boston_image.png"));
            Image image = bufferedImage.getScaledInstance(500, 500, Image.SCALE_SMOOTH);

            JLabel map = new JLabel(new ImageIcon(image));

            GridBagLayout layout = new GridBagLayout();
            main.setLayout(layout);
            GridBagConstraints gridBagConstraints = new GridBagConstraints();

            gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
            gridBagConstraints.insets = new Insets(0,0,10,0);
            gridBagConstraints.gridx = 0;
            gridBagConstraints.gridy = 0;
            gridBagConstraints.gridwidth = 3;
            main.add(map, gridBagConstraints);
            // add start field

            gridBagConstraints.insets = new Insets(0,0,0,0);
            gridBagConstraints.gridwidth = 1;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 1;
            main.add(startLabel, gridBagConstraints);
            // add end field
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 3;
            main.add(endLabel, gridBagConstraints);

            gridBagConstraints.insets = new Insets(0,0,20,0);
            gridBagConstraints.ipady = 20;
            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 2;
            main.add(start, gridBagConstraints);

            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 4;
            main.add(end, gridBagConstraints);

            gridBagConstraints.gridx = 1;
            gridBagConstraints.gridy = 5;
            // add submit button
            main.add(findPathButton, gridBagConstraints);

            // setup frame
            frame.setSize(500, 500);
            frame.add(main);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();

        } catch (IOException e) {
            e.printStackTrace();
        }


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
        findPathButton.addActionListener(e -> onFindPath.run());
    }

    @Override
    public void setUpOnGetLines(Runnable onGetLine) {
        System.out.println("TODO: setUpOnGetLine");
    }

    @Override
    public void setUpStations(Map<Integer, String> stations) {
        stations.entrySet().forEach(start::addItem);
        stations.entrySet().forEach(end::addItem);
    }

    @Override
    public Integer[] getStationsForPathFinding() {
        Map.Entry<Integer, String> from = start.getItemAt(start.getSelectedIndex());
        int startText = from.getKey();

        Map.Entry<Integer, String> to = end.getItemAt(end.getSelectedIndex());
        int endText = to.getKey();

        return new Integer[]{startText, endText};
    }

    @Override
    public Integer getStationForWhichLine() {
        System.out.println("TODO: getStationForWhichLine");
        return 0;
    }

    @Override
    public void displayStations(Map<Integer, String> stations, Map<Integer, Set<String>> lines) {
        System.out.println("TODO: displayStations");
    }

    @Override
    public void displayPath(Set<List<String>> path) {
        System.out.println("TODO: Turn this text output into a panel displayed south of main panel");
        path.forEach(station -> System.out.println(station.toString()));
    }

    @Override
    public void alert(String input) {
        System.out.println(input);
    }
}
