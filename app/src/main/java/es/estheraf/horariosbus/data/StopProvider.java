package es.estheraf.horariosbus.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import es.estheraf.horariosbus.data.exception.LoadingDataException;
import es.estheraf.horariosbus.data.helper.StopHelper;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.util.LogUtil;

/**
 * Provider of data about stops
 *
 * @author Esther Álvarez
 */
public class StopProvider extends DataProvider {

    /**
     * All routeStops
     *
     * @return list of routeStops. If there aren't coincidences or data loaded returns an empty list
     */
    public static List<Stop> getStops() {
        try {
            return getDataContainer().getStops();
        } catch (LoadingDataException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * All possible destinations for a given origin
     *
     * @param origin
     * @return list of possible destinations. If there aren't coincidences or data loaded returns an empty list
     */
    public static List<Stop> getDestinations(Stop origin) {
        TreeMap<Integer, Stop> result = new TreeMap<Integer, Stop>();
        //Iterate through all routes
        for (Route r : RouteProvider.getRoutes()) {
            Integer stopPosition = StopHelper.indexOf(r.routeStops, origin);
            //if route has given stop
            if (stopPosition != -1) {
                //add the following stops in the result list, if aren't yet
                stopPosition++;
                for(int i = stopPosition; i<r.routeStops.size(); i++){
                    Stop iStop = r.routeStops.get(i);
                    if (!result.containsKey(iStop.id)) result.put(iStop.id, iStop);
                }

            }
        }
        return new ArrayList<Stop>(result.values());
    }

}
