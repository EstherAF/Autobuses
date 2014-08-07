package es.estheraf.horariosbus.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import es.estheraf.horariosbus.data.exception.LoadingDataException;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.util.LogUtil;

/**
 * Provider of data about routes
 *
 * @author Esther Álvarez
 */
public class RouteProvider extends DataProvider {

    /**
     * All routes between two routeStops
     *
     * @param idOrigin      id of origin
     * @param idDestination id of destination
     * @return List of routes ordered by departure time. If there aren't coincidences or data loaded returns an empty list.
     */
    public static List<Route> getRoutes(Integer idOrigin, Integer idDestination) {

        return null;
    }

    /**
     * All routes with the given stop
     *
     * @param stop
     * @return list of routes. If there aren't coincidences or data loaded returns an empty list.
     * @throws LoadingDataException
     */
    public static List<Route> getRoutes(Stop stop) {
        ArrayList<Route> result = new ArrayList<Route>();
        for (Route r : getRoutes()) {
            if (r.routeStops.contains(stop)) {
                result.add(r);
            }
        }
        return result;
    }

    /**
     * All routes
     *
     * @return list of routes. If there aren't coincidences or data loaded returns an empty list.
     */
    public static List<Route> getRoutes() {
        try {
            return getDataContainer().getRoutes();
        } catch (LoadingDataException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        }
    }

}
