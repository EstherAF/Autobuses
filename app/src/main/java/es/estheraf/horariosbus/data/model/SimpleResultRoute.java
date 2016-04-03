package es.estheraf.horariosbus.data.model;

import org.joda.time.LocalTime;

import java.io.Serializable;

/**
 * Simple result of a search of routes.
 *
 * @author Esther Álvarez
 */
public class SimpleResultRoute implements Comparable, Serializable {

    /**
     * Route's internal id
     */
    public Integer routeId;
    /**
     * Route's name
     */
    public String routeName;
    /**
     * Departure time, from the specified origin
     */
    public LocalTime departureTime;
    /**
     * Destination time, to the specified destination
     */
    public LocalTime destinationTime;

    @Override
    public int compareTo(Object o) {
        SimpleResultRoute obj = (SimpleResultRoute) o;

        //Compare by departure time
        int comparison = this.departureTime.compareTo(obj.departureTime);
        if (comparison != 0) return comparison;

        //If equals, compare by destination time
        comparison = this.destinationTime.compareTo(obj.destinationTime);
        if (comparison != 0) return comparison;

        //If equals, compare by route id
        comparison = this.routeId - obj.routeId;
        return comparison;
    }
}
