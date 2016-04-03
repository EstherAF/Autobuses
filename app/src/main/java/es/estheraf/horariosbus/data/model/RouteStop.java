package es.estheraf.horariosbus.data.model;

import java.io.Serializable;

/**
 * POJO for routeStops of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class RouteStop implements Serializable {

    public Route route;
    public Stop stop;

    /**
     * Order of the Stop in the route. First = 0
     */
    public Integer pos;

    /**
     * Duration (minutes) of trip between previous and this stop.
     */
    public Integer timeFromDeparture;

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (timeFromDeparture != null ? timeFromDeparture.hashCode() : 0);
        return result;
    }
}
