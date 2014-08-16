package es.estheraf.horariosbus.data.helper;

import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Schedule;
import es.estheraf.horariosbus.data.viewModel.RouteResult;

/**
 * @author Esther Álvarez
 */
public class RouteResultHelper {


    public static List<RouteResult> buildFindRoutesResult(Route route, RouteStop origin, RouteStop destination) {
        List<RouteResult> result = new ArrayList<RouteResult>();

        for(Schedule schedule : route.schedules) {
            /*
            //NOTE: This check will be useful if exist differences on schedule depending on days of week
            if(! schedule.weeklyDays.contains(day))
                continue;
            */

            for(LocalTime departureTime : schedule.departures){
                result.add(buildRouteResult(route, departureTime, origin.minutesFromOrigin, destination.minutesFromOrigin));
            }
        }
        return result;
    }

    public static RouteResult buildRouteResult (Route r, LocalTime departureTime, Integer minutesToOrigin, Integer minutesToDestination) {
        RouteResult result = new RouteResult();
        result.routeId = null;      //TODO: create field RouteId
        result.routeName = r.name;
        result.originTime = departureTime.plusMinutes(minutesToOrigin); //Create a copy, LocalTime is immutable
        result.destinationTime = departureTime.plusMinutes(minutesToDestination);
        return result;
    }
}
