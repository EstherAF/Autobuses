package es.estheraf.horariosbus.data;

import java.util.List;

import es.estheraf.horariosbus.data.loader.DataContainer;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * Provider of business data
 *
 * @author Esther Álvarez Feijoo
 */
public class DataProvider {

    private DataContainer getDataContainer() {
        return DataContainer.getInstance();
    }


    /**
     * Returns a list of all stops
     *
     * @return
     */
    public static List<Stop> getStops() {
        //TODO
        return null;
    }

    /**
     * Returns all destinations for a given origin
     *
     * @param origin
     * @return
     */
    public static List<Stop> getDestinations(Stop origin) {
        //TODO
        return null;
    }

    /**
     * Returns all routes between two stops
     *
     * @param idOrigin
     * @param idDestination
     * @return
     */
    public static List<Route> getRoutes(Integer idOrigin, Integer idDestination) {
        //TODO
        return null;
    }
}
