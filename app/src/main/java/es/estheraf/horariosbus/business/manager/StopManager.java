package es.estheraf.horariosbus.business.manager;

import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.data.provider.xml.StopProviderXmlImpl;

/**
 * Created by Esther on 26/10/2014.
 */
public class StopManager {

    private static StopManager instance;
    private static StopProvider stopProvider;

    public StopManager getInstance() {
        if (instance == null) {
            instance = new StopManager();
            stopProvider = StopProviderXmlImpl.getInstance();
        }
        return instance;
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
