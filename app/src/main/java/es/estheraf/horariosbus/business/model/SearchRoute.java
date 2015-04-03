package es.estheraf.horariosbus.business.model;

import org.joda.time.LocalDate;

import java.io.Serializable;

import es.estheraf.horariosbus.data.model.Stop;

/**
 * Represents a search of routes by Origin and Destination
 *
 * @author Esther Álvarez
 */
public class SearchRoute implements Serializable{

    /**
     * Internal id of origin stop
     */
    public Stop origin;
    /**
     * Internal id of destination stop
     */
    public Stop destination;
    public LocalDate date;

    /**
     * Default constructor
     */
    public SearchRoute() {
    }

    /**
     * Parametrized constructor
     *
     * @param date
     * @param destination destination stop
     * @param origin      departure stop
     */
    public SearchRoute(LocalDate date, Stop destination, Stop origin) {
        this.date = date;
        this.destination = destination;
        this.origin = origin;
    }
}
