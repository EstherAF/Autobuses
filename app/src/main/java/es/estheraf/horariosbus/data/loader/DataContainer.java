package es.estheraf.horariosbus.data.loader;

import java.util.Collection;

import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * Singleton that contains business data
 *
 * @author Esther Álvarez Feijoo
 */
public class DataContainer {

    /**
     * Singleton instance
     */
    private static DataContainer instance;

    /**
     * Loaded stops
     */
    private Collection<Stop> stops;

    /**
     * Loaded routes
     */
    private Collection<Route> routes;

    private DataContainer() {
        /**
         * Load data from json using its loader
         */
    }

    /**
     * Obtain a singleton instance of DataContainer
     *
     * @return DataContainer with loaded business data
     */
    public static DataContainer getInstance() {
        if (instance == null) {
            instance = new DataContainer();
        }
        return instance;
    }

    public Collection<Stop> getStops() {
        return stops;
    }

    public Collection<Route> getRoutes() {
        return routes;
    }
}
