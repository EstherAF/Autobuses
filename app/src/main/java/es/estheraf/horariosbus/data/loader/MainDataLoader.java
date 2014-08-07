package es.estheraf.horariosbus.data.loader;

import android.app.Application;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

import es.estheraf.horariosbus.MainActivity;
import es.estheraf.horariosbus.data.exception.LoadingDataException;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.util.IOUtil;
import es.estheraf.horariosbus.util.LogUtil;

/**
 * Loader of business data from JSON
 *
 * @author Esther Álvarez Feijoo
 */
final class MainDataLoader extends Application{

    /**
     * Access and file information
     */
    //private static int sFileAccessMode = AssetManager.ACCESS_BUFFER;
    private static String sRoutesDataFile = "json/routes.json";
    private static String sStopsDataFile = "json/stops.json";

    /**
     * Json mapper
     */
    private static ObjectMapper mapper = new ObjectMapper();

    private static InputStream openFile(String file) throws IOException{
        return MainActivity.getContext().getAssets().open(file);
    }

    /*
    public void closeMainDataLoader(InputStream file){
        MainActivity.getContext().getAssets().close();
    }
    */

    /**
     * Obtain full list of routes contained in his related file
     *
     * @return list of routes. Empty list if file not found
     * @throws IOException Exception parsing JSON file
     */
    protected static List<Route> loadRoutes() throws LoadingDataException {
        InputStream file=null;
        try {
            file = openFile(sRoutesDataFile);
            return mapper.readValue( file, new TypeReference<List<Route>>() {});
        } catch (FileNotFoundException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        } catch (IOException e) {
            throw new LoadingDataException(e);
        } finally {
            IOUtil.closeQuietly(file);
        }
    }

    /**
     * Obtain full list of stops contained in his related file
     *
     * @return list of stops. Empty list if file not found
     * @throws IOException Exception parsing JSON file
     */
    protected static List<Stop> loadStops() throws LoadingDataException {
        InputStream file=null;
        try {
            file = openFile(sStopsDataFile);
            return mapper.readValue(file, new TypeReference<List<Stop>>() {
            });
        } catch (FileNotFoundException e) {
            LogUtil.error(e);
            return Collections.EMPTY_LIST;
        } catch (IOException e) {
            throw new LoadingDataException(e);
        } finally {
            IOUtil.closeQuietly(file);
        }
    }
}
