package es.estheraf.horariosbus.data.provider;

import org.joda.time.LocalDate;

import java.util.List;

import es.estheraf.horariosbus.business.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Route;

/**
 * Created by Esther on 21/03/2015.
 */
public interface RouteProvider {

    /**
     * All routes with the given stop
     *
     * @param idStop
     * @return list of routes. If there aren't coincidences or data loaded returns an empty list.
     * @throws es.estheraf.horariosbus.exception.data.loader.LoadingDataException
     */
    List<Route> getRoutes(Integer idStop);

    /**
     * All routes
     *
     * @return list of routes. If there aren't coincidences or data loaded returns an empty list.
     */
    List<Route> getRoutes();

    /**
     * All routes between two routeStops
     *
     * @param idOrigin      id of origin stop
     * @param idDestination id of destination stop
     * @return List of routes, ordered by departure time. If there aren't coincidences or data loaded returns an empty list.
     */
    List<SimpleResultRoute> findRoutes(LocalDate date, int idOrigin, int idDestination);
}
