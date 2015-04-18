package es.estheraf.horariosbus.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @author Esther Álvarez
 */
public class IOUtil {

    public static void closeQuietly(InputStream s) {
        if (null != s) {
            try {
                s.close();  //auto flush
            } catch (IOException e) {
                LogUtil.error(e);
            }
        }
    }

    public static void closeQuietly(OutputStream s){
        if (null != s) {
            try {
                s.close();
            } catch (IOException e) {
                LogUtil.error(e);
            }
        }
    }

    public static void copy(InputStream in, OutputStream out) throws IOException{
        byte[] buffer = new byte[1024];     //middle buffer
        int length;
        while ((length = in.read(buffer))>0){
            out.write(buffer, 0, length);
        }
    }

}
