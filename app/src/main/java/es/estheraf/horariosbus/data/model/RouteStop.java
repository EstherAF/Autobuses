package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * POJO for routeStops of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class RouteStop extends Stop {

    /**
     * Duration (minutes) of trip between previous and this stop.
     */
    @JsonProperty(value = "time_from_previous", required = true)
    public Integer minutesFromOrigin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        RouteStop routeStop = (RouteStop) o;

        if (minutesFromOrigin != null ? !minutesFromOrigin.equals(routeStop.minutesFromOrigin) : routeStop.minutesFromOrigin != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (minutesFromOrigin != null ? minutesFromOrigin.hashCode() : 0);
        return result;
    }
}
