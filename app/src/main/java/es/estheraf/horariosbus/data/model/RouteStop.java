package es.estheraf.horariosbus.data.model;

import java.util.Date;

/**
 * POJO for stops of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class RouteStop {

    /**
     * Name of stop
     */
    public Stop stop;

    /**
     * Duration (minutes) of trip between previous and this stop.
     */
    public Date timeFromPrevious;
}
