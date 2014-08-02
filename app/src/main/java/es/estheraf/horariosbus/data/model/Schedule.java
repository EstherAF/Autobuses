package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Collection;
import java.util.Date;

import es.estheraf.horariosbus.data.loader.deserializer.TimeJsonDeserializer;

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
    public Collection<Integer> weeklyDays;

    /**
     * Time (hours and minutes) of departures
     */
    @JsonDeserialize(using = TimeJsonDeserializer.class)
    @JsonProperty(value = "depatures", required = true)
    public Collection<Date> departures;
}
