package es.estheraf.horariosbus.data.model;

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
    public String name;

    /**
     * Ordered stops of this route
     */
    public Collection<RouteStop> stops;

}
