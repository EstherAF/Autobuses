package es.estheraf.horariosbus.data.loader;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.util.LogUtil;

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
     * Reference to routeStops data file
     */
    private static File sStopsDataSourceName = new File("routeStops.json");

    /**
     * Json mapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * Obtain full list of routes contained in his related file
     *
     * @return list of routes
     * @throws IOException Exception parsing JSON file
     */
    protected static List<Route> loadRoutes() throws LoadingDataException {
        try {
            return mapper.readValue(sRoutesDataSourceFile, new TypeReference<List<Route>>() {
            });
        } catch (IOException e){
            LogUtil.error(e);
            throw new LoadingDataException();
        }
    }

    /**
     * Obtain full list of routeStops contained in his related file
     *
     * @return list of routeStops
     * @throws IOException Exception parsing JSON file
     */
    protected static List<Stop> loadStops() throws LoadingDataException {
        try {
            return mapper.readValue(sStopsDataSourceName, new TypeReference<List<Stop>>() {
            });
        } catch (IOException e){
            LogUtil.error(e);
            throw new LoadingDataException();
        }
    }
}
