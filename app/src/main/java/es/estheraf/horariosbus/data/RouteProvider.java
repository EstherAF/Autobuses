package es.estheraf.horariosbus.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import es.estheraf.horariosbus.data.exception.LoadingDataException;
import es.estheraf.horariosbus.data.helper.model.RouteStopHelper;
import es.estheraf.horariosbus.data.helper.viewModel.RouteResultHelper;
import es.estheraf.horariosbus.data.helper.model.StopHelper;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.viewModel.FindRoutesResult;
import es.estheraf.horariosbus.data.viewModel.RouteResult;
import es.estheraf.horariosbus.util.LogUtil;

/**
 * Provider of data about routes
 *
 * @author Esther Álvarez
 */
public class RouteProvider extends DataProvider {

    public static FindRoutesResult findRoutesBewteenStops(Integer idOrigin, Integer idDestination) {
        FindRoutesResult result = new FindRoutesResult();

        result.origin = StopProvider.getStop(idOrigin);
        result.destination = StopProvider.getStop(idDestination);

        if(result.origin != null && result.destination != null) {
            List<RouteResult> routesResult = findRoutesBetweenStops(result.origin, result.destination);
        }

        return result;
    }

    /**
     * All routes between two routeStops
     *
     * @param origin      origin stop
     * @param destination destination stop
     * @return List of routes ordered by departure time. If there aren't coincidences or data loaded returns an empty list.
     */
    public static List<RouteResult> findRoutesBetweenStops(Stop origin, Stop destination) {
        TreeSet<RouteResult> result = new TreeSet<RouteResult>();


        for(Route r : getRoutes()){
            RouteStop[] foundRouteStops = RouteStopHelper.getRouteStops(r.routeStops, origin, destination);
            if(foundRouteStops.length == 2) {
                result.addAll(
                        RouteResultHelper.buildFindRoutesResult(r, foundRouteStops[0], foundRouteStops[1]));
            }
        }
        return new ArrayList<RouteResult>(result);
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
            if (StopHelper.contains(r.routeStops, stop)) {
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
