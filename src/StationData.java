import java.util.List;

public class StationData {
    String name;
    List<String> line;

    public StationData(String name, List<String> line) {
        this.name = name;
        this.line = line;
    }

    @Override
    public String toString() {
        return "Station " + name + ", lines=" + line;
    }
}
