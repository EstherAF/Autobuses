package es.estheraf.horariosbus.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Esther on 26/10/2014.
 */
public class Util {

    public static final List EMPTY_LIST = new ArrayList();

    public static final boolean isEmpty(Collection o) {
        return (o == null || o.isEmpty());
    }

    public static final boolean isEmpty(Number o) {
        return (o == null || o.intValue() == 0);
    }

    public static final boolean isEmpty(String o) {
        return (o == null || o.isEmpty());
    }

    public static final boolean isEmpty(Object[] o) {
        return (o == null || o.length == 0);
    }

    public static final String join(String joinElement, String... elems) {
        String result = "";
        for (String elem : elems) {
            if (!result.isEmpty())
                result += joinElement;
            result += elem;
        }
        return result;
    }
}
