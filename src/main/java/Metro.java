import java.util.*;
import java.util.stream.Collectors;

public class Metro {
    public TreeMap<String,List<Station>> stations = new TreeMap<>();
    public List<List<Connection>> connections = new ArrayList<>();
    public Set<Line> lines = new HashSet<>();

    public void setStations(TreeMap<String, List<Station>> stations) {
        this.stations = stations;
    }

    public TreeMap<String, List<Station>> getStations() {
        return stations;
    }

    public Metro() {
    }

    public void getCountStations() {
        stations.values().stream().mapToInt(List::size).forEach(System.out::println);
    }
}
