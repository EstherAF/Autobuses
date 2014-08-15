package es.estheraf.horariosbus.data.helper;

import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;

/**
 * @author Esther Álvarez
 */
public class StopHelper {

    public static boolean contains(Collection<Stop> stops, Stop target) throws NullPointerException {
        if (stops == null) throw new NullPointerException();

        for (Stop stop : stops) {
            if (stop.id.equals(target.id)) return true;
        }
        return false;
    }

    public static boolean equals(Stop a, Stop b) {
        if (a == null || b == null) return false;

        if (a == b) return true;
        if (a.id == b.id) return true;

        return false;
    }

    public static <T extends Stop> int indexOf(List<T> stops, Stop target) throws NullPointerException {
        if (stops == null) throw new NullPointerException();

        for (int i = 0; i < stops.size(); i++) {
            if (equals(stops.get(i), target)) return i;
        }
        return -1;
    }

}
