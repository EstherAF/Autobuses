package es.estheraf.horariosbus.data.viewModel;

import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;

/**
 * Represents a search of routes with its results
 *
 * @author Esther Álvarez
 */
public class FindRoutesResult {

    public Stop origin;
    public Stop destination;
    public List<RouteResult> routeResults;

    /**
     * Default constructor
     */
    public FindRoutesResult(){
    }

    /**
     * Parametrized constructor
     *
     * @param origin departure stop
     * @param destination destination stop
     * @param routeResults  list of results
     */
    public FindRoutesResult(Stop origin, Stop destination, List<RouteResult> routeResults){
        this.origin=origin;
        this.destination=destination;
        this.routeResults=routeResults;
    }

}
