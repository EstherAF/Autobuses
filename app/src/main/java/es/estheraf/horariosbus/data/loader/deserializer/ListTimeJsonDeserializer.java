package es.estheraf.horariosbus.data.loader.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * @author Esther Álvarez
 */
public class ListTimeJsonDeserializer extends JsonDeserializer<List<Date>> {

    private static final TypeReference<List<String>> listType = new TypeReference<List<String>>() {};

    @Override
    public List<Date> deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        List<Date> result = new ArrayList<Date>();
        Iterator<List<String>> readValues = jp.readValuesAs(listType);
        if(readValues.hasNext()){
            for(String readDate : readValues.next()) {
                result.add(
                        formatStringIntoTime(readDate));
            }
        }
        return result;
    }

    private Date formatStringIntoTime(String sDate){
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        try {
            return format.parse(sDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
