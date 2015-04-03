package es.estheraf.horariosbus.data.provider.sqlite.definition;

import java.util.Arrays;

import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 03/04/2015.
 */
public class RouteDef {

    public static String _NAME = "route";
    public static String _ID = "_id";
    public static String NAME_SHORT = "name_short";
    public static String NAME_LONG = "name_long";
    public static String DAYS_MAP = "days_map";
    public static String DESC = "desc";
    public static String COMPANY = "company";
    public static String[] ALL_COLUMS = (String[]) (Arrays.asList(
            _ID, NAME_SHORT, NAME_LONG, DAYS_MAP, DESC, COMPANY)).toArray();


    public static class QUERY {


        private static String ROUTE_SELECT = Util.join(",", "r." + RouteDef._ID, "r." + RouteDef.NAME_LONG, "r." + RouteDef.COMPANY);

        public static final String ALL = "SELECT " + ROUTE_SELECT + " FROM route r";

        public static final String BY_STOP = "SELECT " + ROUTE_SELECT +
                " FROM route r JOIN route_stop rs ON r._id = rs.route_id " +
                " WHERE rs.stop_id = ?";

        public static final String BEWTEEN_STOPS = "SELECT r._id, r.name_long, sch.departure_time, " +
                " rs1.time_from_departure as origin_time, rs2.time_from_departure as dest_time" +
                " FROM route r " +
                " JOIN route_schedule sch ON r._id = sch.route_id " +
                " JOIN route_stop rs1 ON r._id = rs1.route_id " +
                " JOIN route_stop rs2 ON r._id = rs2.route_id AND rs1._id != rs2._id AND rs1.pos < rs2.pos" +
                " WHERE r.days_map LIKE '%?%' AND sch.days_map LIKE '%?%' " +
                " AND rs1.stop_id = ? AND rs2.stop_id = ?";
    }
}
