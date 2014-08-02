package es.estheraf.horariosbus.data;

import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.loader.DataContainer;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * Provider of business data
 *
 * @author Esther Álvarez Feijoo
 */
public class DataProvider {

    private DataContainer getDataContainer(){
        return DataContainer.getInstance();
    }


    public static List<Stop> getStops(){
        //TODO
        return null;
    }

    public static List<Route> getRoute(Integer idOrigin, Integer idDestination){
        //TODO
        return null;
    }
}
