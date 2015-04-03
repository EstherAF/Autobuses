package es.estheraf.horariosbus.data.loader.sqlite;

import java.util.Arrays;

/**
 * Created by Esther on 21/03/2015.
 */
public class Naming {

    public static final class ROUTE {
        public static String _NAME = ROUTE.class.getSimpleName().toLowerCase();
        public static String _ID = "_id";
        public static String NAME_SHORT = "name_short";
        public static String NAME_LONG = "name_long";
        public static String DAYS_MAP = "days_map";
        public static String DESC = "desc";
        public static String COMPANY = "company";
        public static String[] ALL_COLUMS = (String[]) (Arrays.asList(
                _ID, NAME_SHORT, NAME_LONG, DAYS_MAP, DESC, COMPANY)).toArray();
    }

    public static final class ROUTE_SCHEDULE {
        public static String _NAME = ROUTE_SCHEDULE.class.getSimpleName().toLowerCase();
        public static String _ID = "_id";
        public static String ROUTE_ID = "route_id";
        public static String DAYS_MAP = "days_map";
        public static String DEPARTURE_TIME = "departure_time";
        public static String DESC = "desc";
    }

    public static final class ROUTE_STOP {
        public static String _NAME = ROUTE_STOP.class.getSimpleName().toLowerCase();
        public static String _ID = "_id";
        public static String ROUTE_ID = "route_id";
        public static String STOP_ID = "stop_id";
        public static String POS = "pos";
        public static String TIME_FROM_DEPARTURE = "time_from_departure";
        public static String DESC = "desc";
    }

    public static final class STOP {
        public static String _NAME = STOP.class.getSimpleName().toLowerCase();
        public static String _ID = "_id";
        public static String NAME_SHORT = "name_short";
        public static String NAME_LONG = "name_long";
        public static String COORDS = "coords";
        public static String PARENT_ID = "parent_id";
        public static String DESC = "desc";
        public static String[] ALL_COLUMS = (String[]) (Arrays.asList(
                _ID,NAME_SHORT, NAME_SHORT, COORDS, PARENT_ID, DESC).toArray());
    }
}
