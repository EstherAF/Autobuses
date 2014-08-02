package es.estheraf.horariosbus.data.model;

import java.util.Collection;
import java.util.Date;

/**
 * POJO for route's schedule
 *
 * @author Esther Álvarez Feijoo
 */
public class Schedule {

    /**
     * Days of week of this schedule
     */
    public Collection<Integer> weeklyDays;

    /**
     * Time (hours and minutes) of departures
     */
    public Collection<Date> departures;
}
