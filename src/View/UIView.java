package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.*;

/**
 * The gui view of the metro system
 */
public class UIView implements MetroView {

    JFrame frame;
    JTextField startField, endField;
    JButton filterButton, findPathButton;
    JPanel main;
    DisplayPathsPanel displayPathsPanel;
    JComboBox<Map.Entry<Integer, String>> start, end;
    Map<String, Runnable> commands;
    FilterInterface fi;

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
        startField = new JTextField();
        startField.setPreferredSize(new Dimension(250, 20));
        endField = new JTextField();
        endField.setPreferredSize(new Dimension(250, 20));

        start = new JComboBox<>();
        end = new JComboBox<>();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(0, 0, 0, 10);
        gridBagConstraints.ipady = 20;

        startField.addActionListener(e -> filterStartStations(startField.getText()));

        endField.addActionListener(e -> filterEndStations(endField.getText()));

        gridBagConstraints.gridy = 1;
        gridBagConstraints.gridx = 1;

        main.add(startField, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        main.add(endField, gridBagConstraints);

        gridBagConstraints.gridy = 2;
        gridBagConstraints.gridx = 1;

        main.add(start, gridBagConstraints);

        gridBagConstraints.gridx = 2;
        main.add(end, gridBagConstraints);
    }

    private void buildButtons() {
        //initialise filter button
        filterButton = new JButton("Filter");
        filterButton.addActionListener(e -> {
            filterStartStations(startField.getText());
            filterEndStations(endField.getText());
            end.hidePopup();
        });

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;

        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        // add submit button
        main.add(filterButton, gridBagConstraints);

        // initialise submit button
        findPathButton = new JButton("Find Path");

        gridBagConstraints.gridy = 2;
        // add submit button
        main.add(findPathButton, gridBagConstraints);
    }

    private void buildDisplayPathPanel() {
        displayPathsPanel = new DisplayPathsPanel();

        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.fill = GridBagConstraints.BOTH;
        gridBagConstraints.insets = new Insets(-360, 0, 0, 0);
        gridBagConstraints.gridwidth = 3;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        // add submit button
        main.add(displayPathsPanel, gridBagConstraints);

    }

    private void buildPathFindingLabel() {
        JLabel pathFindingLabel = new JLabel("Paths:");
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        gridBagConstraints.anchor = GridBagConstraints.WEST;
        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new Insets(-360, 0, 0, 0);
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;

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
        buildButtons();
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
        setUpStartStations(stations);
        setUpEndStations(stations);
    }

    @Override
    public void setUpStartStations(Map<Integer, String> stations) {
        stations.entrySet().forEach(start::addItem);
    }

    @Override
    public void setUpEndStations(Map<Integer, String> stations) {
        stations.entrySet().forEach(end::addItem);
    }

    @Override
    public void setUpFilter(FilterInterface filterInterface) {
        fi = filterInterface;
    }

    @Override
    public void filterStartStations(String filterString) {
        start.removeAllItems();
        fi.filter(filterString, true);
        start.repaint();
        start.showPopup();
    }

    @Override
    public void filterEndStations(String filterString) {
        end.removeAllItems();
        fi.filter(filterString, false);
        end.repaint();
        end.showPopup();
    }

    @Override
    public Integer[] getStationsForPathFinding() {
        Map.Entry<Integer, String> from = start.getItemAt(start.getSelectedIndex());
        if(from != null) {
            int startText = from.getKey();

            Map.Entry<Integer, String> to = end.getItemAt(end.getSelectedIndex());
            if(to != null) {
                int endText = to.getKey();
                return new Integer[]{startText, endText};
            }
        }
        return null;
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