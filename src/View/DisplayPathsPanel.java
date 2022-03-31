package View;

import javax.swing.*;
import java.awt.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DisplayPathsPanel extends JPanel {

    Set<JList<String>> jlists;

    public DisplayPathsPanel() {
        super();
        build();
    }

    public void displayPaths(Set<List<String>> paths) {
        // remove previous result
        if (jlists != null) {
            jlists.forEach(this::remove);
        }

        jlists = new HashSet<>();

        paths.forEach(path -> {
            String[] pathArray = path.toArray(new String[]{});
            JList<String> pathList = new JList<>(pathArray);
            pathList.setBackground(new Color(240,240,240));
            jlists.add(pathList);

            add(pathList);
        });
        repaint();
        doLayout();
    }

    private void build() {
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }
}
