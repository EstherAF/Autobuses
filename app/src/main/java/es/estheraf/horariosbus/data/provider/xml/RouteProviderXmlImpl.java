package es.estheraf.horariosbus.data.provider.xml;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

import es.estheraf.horariosbus.business.helper.RouteResultHelper;
import es.estheraf.horariosbus.business.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Schedule;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.RouteProvider;
import es.estheraf.horariosbus.exception.data.loader.LoadingDataException;
import es.estheraf.horariosbus.util.LogUtil;
import es.estheraf.horariosbus.util.Util;

/**
 * Provider of data about routes
 *
 * @author Esther Álvarez
 */
public class RouteProviderXmlImpl extends DataProviderXml implements RouteProvider{

    private static RouteProviderXmlImpl instance;

    public static RouteProvider getInstance(){
        if(instance == null){
            instance = new RouteProviderXmlImpl();
        }
        return instance;
    }

    @Override
    public List<Route> getRoutes(Integer idStop) {
        ArrayList<Route> result = new ArrayList<Route>();

        Stop stopWrapper = new Stop(idStop);

        for (Route r : getRoutes()) {
            if (!Util.isEmpty(r.routeStops) && r.routeStops.contains(stopWrapper)) {
                result.add(r);
            }
        }
        return result;
    }

    @Override
    public List<Route> getRoutes() {
        try {
            return getDataContainer().getRoutes();
        } catch (LoadingDataException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * All routes between two routeStops
     *
     * @param idOrigin      id of origin stop
     * @param idDestination id of destination stop
     * @return List of routes, ordered by departure time. If there aren't coincidences or data loaded returns an empty list.
     */
    @Override
    public List<SimpleResultRoute> findRoutes(LocalDate date, int idOrigin, int idDestination) {
        TreeSet<SimpleResultRoute> result = new TreeSet<SimpleResultRoute>();

        int weekDay = date.getDayOfWeek();

        for (Route r : getRoutes()) {
            List<Schedule> validSchedules = findByWeeklyDay(r.schedules, weekDay);
            if (validSchedules.isEmpty()) continue;

            List<RouteStop> foundRouteStops = StopProviderXmlImpl.getInstance().findStopsInStopCollection(r.routeStops, idOrigin, idDestination);
            if (foundRouteStops.size() == 2) {
                result.addAll(
                        RouteResultHelper.buildFindRoutesResult(validSchedules, r, foundRouteStops.get(0), foundRouteStops.get(1)));
            }
        }
        return new ArrayList<SimpleResultRoute>(result);
    }

    private List<Schedule> findByWeeklyDay(Collection<Schedule> schedules, int day) {
        List<Schedule> result = new ArrayList<Schedule>();
        for (Schedule schedule : schedules) {
            if (schedule.weeklyDays.contains(day))
                result.add(schedule);
        }
        return result;
    }

}
