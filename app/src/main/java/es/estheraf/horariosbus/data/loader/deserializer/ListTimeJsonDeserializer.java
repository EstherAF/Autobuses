package es.estheraf.horariosbus.data.loader.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Esther Álvarez
 */
public class ListTimeJsonDeserializer extends JsonDeserializer<List<LocalTime>> {

    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");

    private static final TypeReference<List<String>> listType = new TypeReference<List<String>>() {
    };

    @Override
    public List<LocalTime> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<LocalTime> result = new ArrayList<LocalTime>();
        Iterator<List<String>> readValues = jp.readValuesAs(listType);
        if (readValues.hasNext()) {
            for (String readDate : readValues.next()) {
                result.add(formatStringIntoTime(readDate));
            }
        }
        return result;
    }

    private LocalTime formatStringIntoTime(String sDate) {
        return LocalTime.parse(sDate, formatter);
    }
}
