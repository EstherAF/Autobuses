package es.estheraf.horariosbus.data.helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import es.estheraf.horariosbus.data.model.RouteStop;
import es.estheraf.horariosbus.data.model.Stop;

/**
 * @author Esther Álvarez
 */
public class StopHelper {

    /**
     * Check if an Stop or child instance has the same id that another Stop or child instance.
     * They don't have to be the same class.
     *
     * @param a
     * @param b
     * @return true when their ids are equals
     */
    public static boolean equals(Stop a, Stop b) {
        if (a == null || b == null) return false;

        if (a == b) return true;
        if (a.id == b.id) return true;

        return false;
    }

    /**
     * Obtains the position of a Stop  inside a List of Stops. They can be Stop or any child class, and don't necessarily the same.
     *
     * @param stops List where to look for.
     * @param target Stop to find
     * @param <T> Type of list elements
     * @return  true when there is (at least) one element in the list with the same id as target's
     * @throws NullPointerException
     */
    public static <T extends Stop> int indexOf(List<T> stops, Stop target) throws NullPointerException {
        return indexOf(stops, target.id);
    }

    /**
     * Obtains the position of a Stop  inside a List of Stops. They can be Stop or any child class, and don't necessarily the same.
     *
     * @param stops List where to look for.
     * @param idTarget Stop's id to find
     * @param <T> Type of list elements
     * @return  true when there is (at least) one element in the list with the same id as target's
     * @throws NullPointerException
     */
    public static <T extends Stop> int indexOf(List<T> stops, int idTarget) throws NullPointerException {
        if (stops == null) throw new NullPointerException();

        for (int i = 0; i < stops.size(); i++) {
            if ( stops.get(i).id == idTarget) return i;
        }
        return -1;
    }



}
