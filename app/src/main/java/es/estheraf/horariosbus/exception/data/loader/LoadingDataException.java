package es.estheraf.horariosbus.exception.data.loader;

/**
 * @author Esther Álvarez
 */
public class LoadingDataException extends Exception {

    public LoadingDataException() {
        super();
    }

    public LoadingDataException(Exception e) {
        super(e);
    }

    public LoadingDataException(String message) {
        super(message);
    }

    public LoadingDataException(String message, Exception e) {
        super(message, e);
    }

}
