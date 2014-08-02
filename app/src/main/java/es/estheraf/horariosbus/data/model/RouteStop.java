package es.estheraf.horariosbus.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;

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
    @JsonProperty(value = "time_from_previous", required = true)
    public Date timeFromPrevious;


    @JsonProperty(value = "id", required = true)
    private void setStop(Integer id) {
        this.stop = new Stop(id);
    }
}
