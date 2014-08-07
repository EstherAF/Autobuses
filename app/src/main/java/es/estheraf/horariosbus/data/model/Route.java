package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * POJO of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class Route {

    /**
     * Route's name
     */
    @JsonProperty(value = "name", required = true)
    public String name;

    /**
     * Ordered routeStops of this route
     */
    @JsonProperty(value = "stops", required = true)
    public List<RouteStop> routeStops;

    @JsonProperty(value = "schedules", required = true)
    public List<Schedule> schedules;
}
