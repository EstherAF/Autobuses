package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Collection;

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
     * Ordered stops of this route
     */
    @JsonProperty(value = "stops", required = true)
    public Collection<RouteStop> stops;


}
