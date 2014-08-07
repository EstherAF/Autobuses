package es.estheraf.horariosbus.data;

import es.estheraf.horariosbus.data.loader.DataContainer;
import es.estheraf.horariosbus.data.exception.LoadingDataException;

/**
 * Provider of business data
 *
 * @author Esther Álvarez Feijoo
 */
public class DataProvider {

    protected static DataContainer getDataContainer() throws LoadingDataException {
        return DataContainer.getInstance();
    }
}
