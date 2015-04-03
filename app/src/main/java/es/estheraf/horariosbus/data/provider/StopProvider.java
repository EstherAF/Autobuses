package es.estheraf.horariosbus.data.provider;

import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;

/**
 * Created by Esther on 21/03/2015.
 */
public interface StopProvider {

    /**
     * All routeStops
     *
     * @return list of routeStops. If there aren't coincidences or data loaded returns an empty list
     */
    List<Stop> getStops();

    /**
     * All possible destinations for a given origin
     *
     * @param idOrigin
     * @return list of possible destinations. If there aren't coincidences or data loaded returns an empty list
     */
    List<Stop> getDestinations(Integer idOrigin);


    /**
     * Get stop by its Id
     *
     * @param idStop
     * @return
     */
    Stop getStop(Integer idStop);

    /**
     * Find all the provided stops in the provided list
     *
     * @param routeStops  list of Stop, to look for in it
     * @param stopsToFind list of stops to look for
     * @return List of found Stops, with the same length as provided stop targets.
     * Empty when (at least) one target isn't found in the same order.
     * Empty when there's no routeStops or any stop target.
     */
    <T extends Stop> List<T> findStopsInStopCollection(Collection<T> routeStops, Integer... stopsToFind);
}
