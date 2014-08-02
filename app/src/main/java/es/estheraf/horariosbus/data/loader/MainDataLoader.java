package es.estheraf.horariosbus.data.loader;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * Loader of business data from JSON
 *
 * @author Esther Álvarez Feijoo
 */
class MainDataLoader {

    /**
     * Reference to routes data file
     */
    private static File sRoutesDataSourceFile = new File("routes.json");
    /**
     * Reference to stops data file
     */
    private static File sStopsDataSourceName = new File("stops.json");

    /**
     * Json mapper
     */
    private ObjectMapper mapper = new ObjectMapper();

    /**
     * Obtain full list of routes contained in his related file
     *
     * @return list of routes
     * @throws IOException Exception parsing JSON file
     */
    protected List<Route> loadRoutes() throws IOException {
        return mapper.readValue(sRoutesDataSourceFile, new TypeReference<List<Route>>() {});
    }

    /**
     * Obtain full list of stops contained in his related file
     *
     * @return list of stops
     * @throws IOException Exception parsing JSON file
     */
    protected List<Stop> loadStops() throws IOException {
        return mapper.readValue(sStopsDataSourceName, new TypeReference<List<Stop>>() {});
    }
}
