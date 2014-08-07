package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * POJO for routeStops of bus routes
 *
 * @author Esther Álvarez Feijoo
 */
public class RouteStop extends Stop{

    /**
     * Duration (minutes) of trip between previous and this stop.
     */
    @JsonProperty(value = "time_from_previous", required = true)
    public Date timeFromPrevious;

    @Override
    public boolean equals(Object o) {
        if(o.getClass().equals(Stop.class)) {
            return super.equals(o);
        } else {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            if (!super.equals(o)) return false;

            RouteStop routeStop = (RouteStop) o;

            if (timeFromPrevious != null ? !timeFromPrevious.equals(routeStop.timeFromPrevious) : routeStop.timeFromPrevious != null)
                return false;

            return true;
        }
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (timeFromPrevious != null ? timeFromPrevious.hashCode() : 0);
        return result;
    }
}
