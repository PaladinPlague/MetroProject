package View;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UIView implements MetroView {

    JFrame frame;
    JButton findPathButton;
    JPanel main;
    DisplayPathsPanel displayPathsPanel;
    JComboBox<Map.Entry<Integer, String>> start, end;
    Map<String, Runnable> commands;

    public UIView() {
        commands = new HashMap<>();
        buildUI();
    }

    private void buildImage() throws IOException {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        BufferedImage bufferedImage = ImageIO.read(Objects.requireNonNull(ClassLoader.getSystemResourceAsStream("boston_image.png")));
        int height = 500;
        int width = 500;
        ZoomablePannablePanel zoomable = new ZoomablePannablePanel(bufferedImage, width);
        zoomable.setPreferredSize(new Dimension(width, height));
        zoomable.setMinimumSize(new Dimension(width, height));

        gridBagConstraints.insets = new Insets(0, 0, 10, 10);
        gridBagConstraints.gridheight = 4;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        main.add(zoomable, gridBagConstraints);
    }

    private void buildLabels() {

        // initialise start field
        JLabel startLabel = new JLabel("Starting:");
        JLabel endLabel = new JLabel("Destination:");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.gridx = 1;
        main.add(startLabel, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        main.add(endLabel, gridBagConstraints);
    }

    private void buildDropDowns() {
        start = new JComboBox<>();
        end = new JComboBox<>();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.ipady = 20;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;
        main.add(start, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        main.add(end, gridBagConstraints);
    }

    private void buildButton() {
        // initialise submit button
        findPathButton = new JButton("Find Path");

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        // add submit button
        main.add(findPathButton, gridBagConstraints);
    }

    private void buildDisplayPathPanel() {
        displayPathsPanel = new DisplayPathsPanel();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        // add submit button
        main.add(displayPathsPanel, gridBagConstraints);

    }

    private void buildPathFindingLabel() {
        JLabel pathFindingLabel = new JLabel("Paths:");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(20, 0, 0, 0);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;

        main.add(pathFindingLabel, gridBagConstraints);
    }

    private void buildUI() {
        frame = new JFrame("Boston Metro Route-finder");
        main = new JPanel(new GridBagLayout());
        main.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        try {
            buildImage();
        } catch (IOException e) {
            e.printStackTrace();
        }

        buildLabels();
        buildDropDowns();
        buildButton();
        buildPathFindingLabel();
        buildDisplayPathPanel();

        // setup frame
        frame.add(main);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1300, 700));
        frame.setMinimumSize(new Dimension(1300, 700));
        frame.pack();
    }

    @Override
    public void start() {
        // show frame
        frame.setVisible(true);
    }

    @Override
    public void setUpOnDisplayGraph(Runnable OnDisplayGraph) {
    }

    @Override
    public void setUpOnFindPath(Runnable onFindPath) {
        findPathButton.addActionListener(e -> onFindPath.run());
    }

    @Override
    public void setUpOnGetLines(Runnable onGetLine) {

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
        return 0;
    }

    @Override
    public void displayStations(Map<Integer, String> stations, Map<Integer, Set<String>> lines) {
    }

    @Override
    public void displayPath(Set<List<String>> path) {
        displayPathsPanel.displayPaths(path);
    }

    @Override
    public void alert(String input) {

        JFrame popUpFrame = new JFrame("Error");
        JPanel main = new JPanel();
        JLabel text = new JLabel(input);
        text.setForeground(new Color(255, 0, 0));

        main.add(text);
        popUpFrame.add(main);
        popUpFrame.pack();

        popUpFrame.setVisible(true);
    }
}
