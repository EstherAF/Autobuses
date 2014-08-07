package es.estheraf.horariosbus.util;

import android.util.Log;

import java.lang.reflect.Method;

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
    public static String getLogTag(){
        StackTraceElement trace = Thread.currentThread().getStackTrace()[2];    //get the invoker, not actual
        return trace.getClassName() + ":" + trace.getMethodName();
    }

    /**
     * Log on error mode an exception
     *
     * @param e exception
     */
    public static void error(Exception e){
        LogUtil.error(e, null);
    }

    /**
     * Log on error mode an exception with a custom message
     *
     * @param e exception
     * @param message message to log
     */
    public static void error(Exception e, String message){
        Log.e(getLogTag(), message, e);
    }

    /**
     * Log on debug mode a given message
     *
     * @param message message to log
     */
    public static void debug(String message){
        Log.e(getLogTag(), message);
    }
}
