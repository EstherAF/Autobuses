package es.estheraf.horariosbus.data.viewModel;

import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;

/**
 * @author Esther Álvarez
 */
public class FindRoutesResult {

    public Stop origin;
    public Stop destination;

    public List<RouteResult> routeResults;

}
