package es.estheraf.horariosbus.util;

import android.util.Log;

/**
 * Utilities for logging
 *
 * @author Esther Álvarez Feijoo
 */
public class LogUtil {

    /**
     * Obtains a valid tag for logging purpose with format className:method
     *
     * @return tag
     */
    protected static String getLogTag() {
        StackTraceElement trace = Thread.currentThread().getStackTrace()[4];    //get the invoker, not actual
        return trace.getClassName() + ":" + trace.getMethodName();
    }

    /**
     * Log on error mode an exception
     *
     * @param e exception
     */
    public static void error(Exception e) {
        Log.e(getLogTag(), "", e);
    }

    /**
     * Log on error mode an exception with a custom message
     *
     * @param e       exception
     * @param message message to log
     */
    public static void error(Exception e, String message) {
        Log.e(getLogTag(), message, e);
    }

    /**
     * Log on debug mode a given message
     *
     * @param message message to log
     */
    public static void debug(String message) {
        Log.d(getLogTag(), message);
    }
}
