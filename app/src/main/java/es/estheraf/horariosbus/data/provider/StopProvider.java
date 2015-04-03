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
}
