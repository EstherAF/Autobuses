package es.estheraf.horariosbus.business.helper;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.business.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Schedule;

/**
 * @author Esther Álvarez
 */
public class RouteResultHelper {


    public static List<SimpleResultRoute> buildFindRoutesResult(List<Schedule> schedules, Route route, RouteStop origin, RouteStop destination) {
        List<SimpleResultRoute> result = new ArrayList<SimpleResultRoute>();
        for (Schedule schedule : schedules) {
            for (LocalTime departureTime : schedule.departures) {
                result.add(buildRouteResult(route, departureTime, origin.timeFromDeparture, destination.timeFromDeparture));
            }
        }
        return result;
    }

    /**
     * Build a RouteResult, calculating the origin and destination stops times
     *
     * @param route                Route
     * @param departureTime        departure time of the whole route
     * @param minutesToOrigin      duration of trip between route's first stop to the origin stop target
     * @param minutesToDestination duration of trip between route's first stop to the destination stop target
     * @return RouteResult completely built
     */
    public static SimpleResultRoute buildRouteResult(Route route, LocalTime departureTime, Integer minutesToOrigin, Integer minutesToDestination) {
        SimpleResultRoute result = new SimpleResultRoute();
        result.routeId = null;      //TODO: create field RouteId
        result.routeName = route.nameShort;
        result.departureTime = departureTime.plusMinutes(minutesToOrigin); //Create a copy, LocalTime is immutable
        result.destinationTime = departureTime.plusMinutes(minutesToDestination);
        return result;
    }
}
