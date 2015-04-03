package es.estheraf.horariosbus.data.provider.xml;

import es.estheraf.horariosbus.data.loader.xml.PermDataContainer;
import es.estheraf.horariosbus.exception.data.loader.LoadingDataException;

/**
 * Provider of business data
 *
 * @author Esther Álvarez Feijoo
 */
public abstract class DataProviderXml {

    protected static PermDataContainer getDataContainer() throws LoadingDataException {
        return PermDataContainer.getInstance();
    }
}
