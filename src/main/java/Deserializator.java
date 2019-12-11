import com.google.gson.*;

import java.lang.reflect.Type;

public class Deserializator implements JsonDeserializer<Station> {
    @Override
    public Station deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
         Station station = new Station();
         station.setName(jsonElement.getAsJsonObject().get("name").getAsString());
         station.setLineNumber(jsonElement.getAsJsonObject().get("lineNumber").getAsString());
        return station;
    }
}
