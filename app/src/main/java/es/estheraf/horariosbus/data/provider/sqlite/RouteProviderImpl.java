package es.estheraf.horariosbus.data.provider.sqlite;

import android.database.Cursor;

import org.joda.time.LocalDate;
import org.joda.time.LocalTime;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.business.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.loader.sqlite.DataBaseHelper;
import es.estheraf.horariosbus.data.loader.sqlite.Naming;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.provider.RouteProvider;
import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 21/03/2015.
 */
public class RouteProviderImpl implements RouteProvider {

    private static RouteProviderImpl instance = new RouteProviderImpl();
    private static DataBaseHelper dbHelper;

    private static String ROUTE_SELECT = Util.join(",", "r." + Naming.ROUTE._ID, "r." + Naming.ROUTE.NAME_LONG, "r."+Naming.ROUTE.COMPANY);

    private static final String Q_ALL = "SELECT " + ROUTE_SELECT + " FROM route r";

    private static final String Q_BY_STOP = "SELECT " + ROUTE_SELECT +
            " FROM route r JOIN route_stop rs ON r._id = rs.route_id " +
            " WHERE rs.stop_id = ?";

    private static final String Q_COMPLETE = "SELECT r._id, r.name_long, sch.departure_time, " +
            " rs1.time_from_departure as origin_time, rs2.time_from_departure as dest_time" +
            " FROM route r " +
            " JOIN route_schedule sch ON r._id = sch.route_id " +
            " JOIN route_stop rs1 ON r._id = rs1.route_id " +
            " JOIN route_stop rs2 ON r._id = rs2.route_id AND rs1._id != rs2._id AND rs1.pos < rs2.pos" +
            " WHERE r.days_map LIKE '%?%' AND sch.days_map LIKE '%?%' " +
            " AND rs1.stop_id = ? AND rs2.stop_id = ?";

    private RouteProviderImpl(){
        super();
        instance = this;
        dbHelper = new DataBaseHelper();
        dbHelper.createDataBase();
    }

    public static RouteProviderImpl getInstance() {
        return instance;
    }

    @Override
    public List<Route> getRoutes(Integer idStop) {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_BY_STOP, idStop.toString());
        List<Route> result = readCursorList(cur);
        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<Route> getRoutes() {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_ALL);
        List<Route> result = readCursorList(cur);
        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<SimpleResultRoute> findRoutes(LocalDate date, int idOrigin, int idDestination) {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_COMPLETE);
        List<SimpleResultRoute> simpleResultRoutes = readCursorSimpleResultList(cur);
        dbHelper.closeDataBase();
        return simpleResultRoutes;
    }

    private List<SimpleResultRoute> readCursorSimpleResultList(Cursor cur) {
        List<SimpleResultRoute> simpleResultRoutes = new ArrayList<SimpleResultRoute>();

        int id = 0, name = 0, departure = 0, origin = 0, dest = 0;
        if(cur.moveToFirst()){
            id = cur.getColumnIndex("_id");
            name = cur.getColumnIndex("name_long");
            departure = cur.getColumnIndex("departure_time");
            origin = cur.getColumnIndex("origin_time");
            dest = cur.getColumnIndex("dest_time");

            do{
                simpleResultRoutes.add(readCursorSimpleResultRow(cur, id, name, departure, origin, dest));
            }while (cur.moveToNext());
        }
        return simpleResultRoutes;
    }

    private SimpleResultRoute readCursorSimpleResultRow(Cursor cur, int id, int name, int departure, int origin, int dest) {
        SimpleResultRoute route = new SimpleResultRoute();
        route.routeId = cur.getInt(id);
        route.routeName = cur.getString(name);
        LocalTime departureTime = new LocalTime(cur.getLong(departure));
        route.departureTime = departureTime.plusSeconds(cur.getInt(origin));
        route.destinationTime = departureTime.plusSeconds(cur.getInt(dest));
        return route;
    }

    private List<Route> readCursorList(Cursor c) {
        List<Route> result = new ArrayList<Route>();
        if (c.moveToFirst()) {
            int id = 0;
            int name = 0;
            int company = 0;
            do {
                if (id == 0) {  //Init column index
                    id = c.getColumnIndex(Naming.ROUTE._ID);
                    name = c.getColumnIndex(Naming.ROUTE.NAME_LONG);
                    company = c.getColumnIndex(Naming.ROUTE.COMPANY);
                }
                result.add(readCursorRow(c, id, name, company));
            } while (c.moveToNext());
        }
        return result;
    }

    private Route readFirst(Cursor c) {
        Route result = null;
        if(c.moveToFirst()) {
            int id = c.getColumnIndex(Naming.ROUTE._ID);
            int name = c.getColumnIndex(Naming.ROUTE.NAME_LONG);
            int company = c.getColumnIndex(Naming.ROUTE.COMPANY);
            result = readCursorRow(c, id, name, company);
        }
        return result;
    }

    private Route readCursorRow(Cursor c, int id, int name, int company) {
        Route result = new Route();
        result.id = c.getInt(id);
        result.nameShort = c.getString(name);
        result.company= c.getString(company);
        return result;
    }
}
