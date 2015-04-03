package es.estheraf.horariosbus.data;

import java.util.List;

import es.estheraf.horariosbus.data.model.SearchRoute;
import es.estheraf.horariosbus.data.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.RouteProvider;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.data.provider.sqlite.RouteProviderImpl;
import es.estheraf.horariosbus.data.provider.sqlite.StopProviderImpl;

/**
 * Created by Esther on 26/10/2014.
 */
public class DataProviderFacade {

    private static DataProviderFacade instance = new DataProviderFacade();

    private static StopProvider stopProvider;
    private static RouteProvider routeProvider;

    private DataProviderFacade(){
        stopProvider = StopProviderImpl.getInstance();
        routeProvider = RouteProviderImpl.getInstance();
    }

    public static DataProviderFacade getInstance(){
        return instance;
    }

    public List<SimpleResultRoute> doSearch(SearchRoute search) {
        return routeProvider.findRoutes(search.date, search.origin.id, search.destination.id);
    }


    /**
     * Direct wrapper of StopProvider.getStops
     * @return
     */
    public final List<Stop> getStops() {
        return stopProvider.getStops();
    }

    /**
     * Direct wrapper of StopProvider.getDestinations
     * @return
     */
    public final List<Stop> getDestinations(Stop origin) {
        return stopProvider.getDestinations(origin.id);
    }

}
