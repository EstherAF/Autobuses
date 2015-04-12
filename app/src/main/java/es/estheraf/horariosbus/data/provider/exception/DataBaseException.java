package es.estheraf.horariosbus.data.provider.exception;

import java.util.logging.Level;

/**
 * Created by Esther on 12/04/2015.
 */
public class DataBaseException extends Exception{



    public DataBaseException(Throwable throwable) {
        super(throwable);
    }

    public DataBaseException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public DataBaseException(String detailMessage) {
        super(detailMessage);
    }

    public DataBaseException() {
    }
}
