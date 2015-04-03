package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * POJO of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class Route {

    /**
     * Internal _id
     */
    public Integer id;

    /**
     * Company name
     */
    public String company;

    /**
     * Route's name (shorter)
     */
    public String nameShort;

    /**
     * Route's name (longer)
     */
    public String nameLong;

    /**
     * Days of week of this route
     */
    public List<Integer> days;

    /**
     * Ordered routeStops of this route
     */
    public List<RouteStop> routeStops;

    public List<Schedule> schedules;
}
