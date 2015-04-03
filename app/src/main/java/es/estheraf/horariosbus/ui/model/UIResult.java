package es.estheraf.horariosbus.ui.model;

import java.io.Serializable;
import java.util.List;

import es.estheraf.horariosbus.business.model.SearchRoute;
import es.estheraf.horariosbus.business.model.SimpleResultRoute;

/**
 * Created by Esther on 26/10/2014.
 */
public class UIResult implements Serializable{
    /**
     *
     */
    public SearchRoute search;
    /**
     *
     */
    public List<SimpleResultRoute> results;

    /**
     * Constructor without parameters
     */
    public UIResult() {
    }

    /**
     * Constructor with parameters
     *
     * @param search contains the search parameters
     * @param results contains the results of the search
     */
    public UIResult(SearchRoute search, List<SimpleResultRoute> results) {
        this.search = search;
        this.results = results;
    }
}
