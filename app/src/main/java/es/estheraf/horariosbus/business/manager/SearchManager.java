package es.estheraf.horariosbus.business.manager;

import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.TreeSet;

import es.estheraf.horariosbus.business.helper.RouteResultHelper;
import es.estheraf.horariosbus.business.model.SearchRoute;
import es.estheraf.horariosbus.business.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Schedule;
import es.estheraf.horariosbus.data.provider.RouteProvider;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.data.provider.xml.RouteProviderXmlImpl;
import es.estheraf.horariosbus.data.provider.xml.StopProviderXmlImpl;

/**
 * Created by Esther on 26/10/2014.
 */
public class SearchManager {

    private static SearchManager instance;

    private static StopProvider stopProvider;
    private static RouteProvider routeProvider;

    public static SearchManager getInstance(){
        if(instance==null){
            instance = new SearchManager();
            stopProvider = StopProviderXmlImpl.getInstance();
            routeProvider = RouteProviderXmlImpl.getInstance();
        }

        return instance;
    }

    public static List<SimpleResultRoute> doSearch(SearchRoute search) {
        return routeProvider.findRoutes(search.date, search.origin.id, search.destination.id);
    }


}
