package Model;

public class Station {
    /**
     * index of the station in the file
     */
    final private int index;

    /**
     * The name of the station
     */
    final private String name;

    public Station(int index, String name) {
        this.index = index;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station {" + "\n index=" + index + ",\n name='" + name + "\n}";
    }

    public int getIndex() {
        return this.index;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return name.equals(station.name);
    }
}
