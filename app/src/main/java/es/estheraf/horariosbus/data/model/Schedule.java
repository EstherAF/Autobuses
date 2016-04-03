package es.estheraf.horariosbus.data.model;

import org.joda.time.LocalTime;

import java.io.Serializable;
import java.util.List;


/**
 * POJO for route's schedule
 *
 * @author Esther Álvarez Feijoo
 */
public class Schedule implements Serializable{

    /**
     * Days of week of this schedule
     */
    public List<Integer> weeklyDays;

    /**
     * Time (hours and minutes) of departures
     */
    public List<LocalTime> departures;
}
