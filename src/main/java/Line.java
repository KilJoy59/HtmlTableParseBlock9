import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Line implements Comparable<Line>{
    private String name;
    private String lineNumber;
    private String lineColor;
    private transient List<Station> stations;

    public String getLineColor() {
        return lineColor;
    }

    public Line(String name, String lineNumber, String lineColor) {
        this.name = name;
        this.lineNumber = lineNumber;
        this.lineColor = lineColor;
        stations = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "линия:" + name + " " + lineNumber + " " + lineColor;
    }

    public String getName() {
        return name;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void addStation(Station station) {
        stations.add(station);
    }

    public List<Station> getStations() {
        return stations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Line)) return false;
        Line line = (Line) o;
        return Objects.equals(getName(), line.getName()) &&
                Objects.equals(getLineNumber(), line.getLineNumber()) &&
                Objects.equals(getLineColor(), line.getLineColor()) &&
                Objects.equals(getStations(), line.getStations());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getLineNumber(), getLineColor(), getStations());
    }

    @Override
    public int compareTo(Line o) {
        return this.name.compareTo(o.name);
    }
}


