package es.estheraf.horariosbus.data.loader.xml;

import java.util.List;

import es.estheraf.horariosbus.exception.data.loader.LoadingDataException;
import es.estheraf.horariosbus.data.helper.StopHelper;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * Singleton that contains business data.
 * When the singleton instance is created, load all business data on it.
 *
 * @author Esther Álvarez Feijoo
 */
public class PermDataContainer {

    /**
     * Singleton instance
     */
    private static PermDataContainer instance;

    /**
     * Loaded stops
     */
    private List<Stop> stops;

    /**
     * Loaded routes
     */
    private List<Route> routes;

    /**
     * Private constructor
     *
     * @throws LoadingDataException Exception on loading business data
     */
    private PermDataContainer() throws LoadingDataException {
        this.stops = MainDataLoader.loadStops();
        this.routes = MainDataLoader.loadRoutes();
        fixRouteStopsReferences();
    }

    /**
     * Obtains a singleton instance of DataContainer
     *
     * @return DataContainer with loaded business data
     * @throws LoadingDataException Exception on loading business data
     */
    public static PermDataContainer getInstance() throws LoadingDataException {
        if (instance == null) {
            instance = new PermDataContainer();
        }
        return instance;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    /**
     * For every RouteStop in 'routes', sets its stop to the corresponding in 'stops' list,
     * finding it by the loaded id
     */
    private void fixRouteStopsReferences() {
        for (Route route : this.routes) {
            for (RouteStop routeStop : route.routeStops) {
                //Id of stop to fix
                fixRouteStopWithLoadedStop(routeStop, getStop(routeStop));
            }
        }
    }

    private void fixRouteStopWithLoadedStop(RouteStop routeStop, Stop loadedStop) {
        routeStop.name = loadedStop.name;
    }

    private Stop getStop(Stop stop) {
        Integer position = StopHelper.indexOf(this.stops, stop);
        return this.stops.get(position);
    }
}
