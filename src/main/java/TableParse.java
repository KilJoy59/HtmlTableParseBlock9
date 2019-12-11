import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class TableParse {
    private static LinkedList<Line> lineList = new LinkedList<>();
    private static GsonBuilder builder = new GsonBuilder();
    private static GsonBuilder builder2 = new GsonBuilder();
    private static Gson GSON;

    public static void main(String[] args) throws IOException {
        builder.registerTypeAdapter(Station.class, new Serializator());
        GSON = builder.setPrettyPrinting().create();
        Metro metro = new Metro();
        String urlName = "https://ru.wikipedia.org/wiki/%D0%A1%D0%BF%D0%B8%D1%81%D0%BE%D0%BA"
                + "_%D1%81%D1%82%D0%B0%D0%BD%D1%86%D0%B8%D0%B9_%D0%9C%D0%BE%D1%81%D0%BA%D0%BE%D0%B2%D1%81%D0%BA%D"
                + "0%BE%D0%B3%D0%BE_%D0%BC%D0%B5%D1%82%D1%80%D0%BE%D0%BF%D0%BE%D0%BB%D0%B8%D1%82%D0%B5%D0%BD%D0%B0";
        Document doc = Jsoup.connect(urlName).maxBodySize(0).get();
        Element table = doc.select("table[class=standard sortable]").get(0);
        Elements trs = table.select("tr");
        for (int i = 1; i < trs.size(); i++) {
            Element tr = trs.get(i);
            Elements col = tr.select("td");
            String lineColor = col.get(0).attr("style").split(":")[1];
            String lineName = col.get(0).select("span[title]").attr("title");
            String lineNumber = col.get(0).child(0).text();
            String stationName = trs.get(i).select("td").get(1).text();
            metro.lines.add(new Line(lineName, lineNumber, lineColor));
            for (Line line : lineList) {
                if (!metro.stations.containsKey(line.getLineNumber())) {
                    metro.stations.put(line.getLineNumber(), line.getStations());
                }
            }
            List<Connection> connection = new ArrayList<>();
            connection.add(new Connection(lineNumber, stationName));
            Elements el = col.get(3).select("span[title]");
            for (int j = 0; j < el.size(); j++) {
                if (el.get(j).attr("title") != null) {
                    String connectStationName = el.get(j).attr("title");
                    String connectStataionNumberLine = el.get(j).previousElementSibling().text();
                    connection.add(new Connection(connectStataionNumberLine, connectStationName));
                }
            }
            if (connection.size() > 1) {
                metro.connections.add(connection);
            }
            addLine(lineName, lineNumber, lineColor);
            addStationInLine(lineNumber, stationName);
        }
        try (Writer writer1 = new FileWriter("js.json")) {
            GSON.toJson(metro, writer1);
        }
        builder2.registerTypeAdapter(Station.class, new Deserializator());
        GSON = builder2.setPrettyPrinting().create();
            String json = GSON.toJson(metro);
            Metro metro2 = GSON.fromJson(json, Metro.class);
            metro2.getCountStations();
    }

    private static void addLine(String name, String number, String color) {
        Line line = new Line(name, number, color);
        if (!lineList.contains(line)) {
            lineList.add(line);
        }
    }

    private static void addStationInLine(String lineNumber, String station) {
        for (Line line : lineList) {
            if (line.getLineNumber().equals(lineNumber)) {
                line.addStation(new Station(station, lineNumber));
            }
        }
    }




}






