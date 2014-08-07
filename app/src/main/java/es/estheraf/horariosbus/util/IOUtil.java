package es.estheraf.horariosbus.util;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Esther Álvarez
 */
public class IOUtil {

    public static void closeQuietly(InputStream s) {
        if (null != s) {
            try {
                s.close();
            } catch (IOException e) {
                LogUtil.error(e);
            }
        }
    }

}
