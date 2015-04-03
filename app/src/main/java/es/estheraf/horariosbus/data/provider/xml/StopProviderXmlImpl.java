package es.estheraf.horariosbus.data.provider.xml;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import es.estheraf.horariosbus.data.helper.StopHelper;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.exception.data.loader.LoadingDataException;
import es.estheraf.horariosbus.util.LogUtil;
import es.estheraf.horariosbus.util.Util;

/**
 * Provider of data about stops
 *
 * @author Esther Álvarez
 */
public class StopProviderXmlImpl extends DataProviderXml implements StopProvider {

    private static StopProviderXmlImpl instance;

    public static StopProviderXmlImpl getInstance(){
        if(instance == null){
            instance = new StopProviderXmlImpl();
        }
        return instance;
    }

    @Override
    public List<Stop> getStops() {
        try {
            return getDataContainer().getStops();
        } catch (LoadingDataException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public List<Stop> getDestinations(Integer idOrigin) {
        SortedSet<Stop> result = new TreeSet<Stop>();
        //Iterate through all routes
        for (Route r : RouteProviderXmlImpl.getInstance().getRoutes()) {
            //if route has the given stop, add it and the following
            Integer stopPosition = StopHelper.indexOf(r.routeStops, idOrigin);
            if (stopPosition != -1) {
                result.addAll(r.routeStops.subList(stopPosition+1, r.routeStops.size()));
            }
        }
        return new ArrayList<Stop>(result);
    }

    @Override
    public Stop getStop(Integer idStop) {
        for (Stop stop : getStops()) {
            if (stop.id.equals(idStop))
                return stop;
        }
        return null;
    }

    @Override
    public <T extends Stop> List<T> findStopsInStopCollection(Collection<T> routeStops, Integer... stopsToFind) {
        List<T> result = new ArrayList<T>();    //Initialize result

        //Check of null or empty lists
        if (!Util.isEmpty(routeStops) && !Util.isEmpty(routeStops)) {

            Iterator<T> stopIterator = routeStops.iterator();
            for (int targetId : Arrays.asList(stopsToFind)) {
                while (stopIterator.hasNext()) {
                    T next = stopIterator.next();
                    //if actual stop is the target, add it as result and find the next target
                    if (next.id.equals(targetId)) {
                        result.add(next);
                        targetId++;
                        break;
                    }
                }
                //If we have targets to find and routeStopIterator is ended, there's no solution
                if (!stopIterator.hasNext()) break;
            }
        }
        return result;
    }

}
