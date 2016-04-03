package es.estheraf.horariosbus.data.model;

import java.io.Serializable;
import java.util.List;

/**
 * POJO of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class Route implements Serializable {

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
