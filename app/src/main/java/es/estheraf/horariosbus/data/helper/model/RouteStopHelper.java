package es.estheraf.horariosbus.data.helper.model;

import java.util.Collection;

import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * @author Esther Álvarez
 */
public class RouteStopHelper {

    public static final RouteStop[] EMPTY_ROUTESTOP_ARRAY = new RouteStop[0];

    /**
     * Find all the provided stops in the provided list
     *
     * @param routeStops list of RouteStop where to look in
     * @param target list of stops to look for
     * @return  Array of found RouteStops, with the same length as provided stop targets.
     *          Empty array when routeStops is null or empty, there isn't any stop target, or some of the stop target isn't found.
     */
    public static RouteStop[] getRouteStops(Collection<RouteStop> routeStops, Stop... target) {
        //Check of null or empty lists
        if (routeStops == null || target==null || routeStops.isEmpty() || target.length==0)
            return EMPTY_ROUTESTOP_ARRAY;

        RouteStop[] result = new RouteStop[target.length];

        //Find provided targets in the stop Collection, in the provided order
        int targetIndex = 0;  //index of next target stop
        int lastTargetIndex = target.length-1;     //index of last target stop to find
        Stop nextTarget = target[targetIndex];     //next target to find

        for (RouteStop stop : routeStops) {
            //Check coincidence between actual stop and target stop
            if (stop.id.equals(nextTarget)) {
                //add coincidences to result list
                result[targetIndex] = stop;

                if (targetIndex == lastTargetIndex)
                    //if it's the last target stop, done
                    return result;
                else {
                    //otherwise, set the next stop to find
                    targetIndex++;
                    nextTarget = target[targetIndex];
                }
            }
        }
        return EMPTY_ROUTESTOP_ARRAY;
    }

}
