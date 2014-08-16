package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import org.joda.time.LocalTime;

import java.util.Date;
import java.util.List;

import es.estheraf.horariosbus.data.loader.deserializer.ListTimeJsonDeserializer;

/**
 * POJO for route's schedule
 *
 * @author Esther Álvarez Feijoo
 */
public class Schedule {

    /**
     * Days of week of this schedule
     */
    @JsonProperty(value = "weekly_days", required = true)
    public List<Integer> weeklyDays;

    /**
     * Time (hours and minutes) of departures
     */
    @JsonProperty(value = "departures", required = true)
    @JsonDeserialize(using = ListTimeJsonDeserializer.class)
    public List<LocalTime> departures;
}
