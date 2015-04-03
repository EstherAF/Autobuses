package es.estheraf.horariosbus.data.provider.sqlite.definition;

import java.util.Arrays;

import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 03/04/2015.
 */
public class StopDef {

    public static String _NAME = "stop";
    public static String _ID = "_id";
    public static String NAME_SHORT = "name_short";
    public static String NAME_LONG = "name_long";
    public static String COORDS = "coords";
    public static String PARENT_ID = "parent_id";
    public static String DESC = "desc";
    public static String[] ALL_COLUMS = (String[]) (Arrays.asList(
            _ID,NAME_SHORT, NAME_SHORT, COORDS, PARENT_ID, DESC).toArray());


    public static class QUERY {

        private static String SELECT = Util.join(",", "s." + Naming.STOP._ID, "s." + Naming.STOP.NAME_LONG);

        public static String ALL = "SELECT " + SELECT + " FROM stop s ORDER BY s.name_long";
        public static String BY_ID = "SELECT " + SELECT + " FROM stop s WHERE s._id = ?";

        public static String BY_DESTINATIONS =
                "SELECT " + SELECT + " FROM stop s WHERE s._id IN ("
                        + " SELECT r2.stop_id "
                        + " FROM route_stop r1 JOIN route_stop r2 ON r1.route_id = r2.route_id"
                        + " WHERE r1.stop_id = ? AND r2.pos > r1.pos)"
                        + " ORDER BY s.name_long";

    }
}
