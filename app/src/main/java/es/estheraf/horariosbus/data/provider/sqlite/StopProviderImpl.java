package es.estheraf.horariosbus.data.provider.sqlite;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import es.estheraf.horariosbus.data.loader.sqlite.DataBaseHelper;
import es.estheraf.horariosbus.data.loader.sqlite.Naming;
import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 21/03/2015.
 */
public class StopProviderImpl implements StopProvider {

    private static StopProviderImpl instance = new StopProviderImpl();
    private static DataBaseHelper dbHelper;

    private static String STOP_SELECT = Util.join(",", "s." + Naming.STOP._ID, "s." + Naming.STOP.NAME_LONG);

    private static String Q_ALL = "SELECT " + STOP_SELECT + " FROM stop s ORDER BY s.name_long";
    private static String Q_BY_ID = "SELECT " + STOP_SELECT + " FROM stop s WHERE s._id = ?";

    private static String Q_BY_DESTINATIONS =
            "SELECT " + STOP_SELECT + " FROM stop s WHERE s._id IN ("
                    + " SELECT r2.stop_id "
                    + " FROM route_stop r1 JOIN route_stop r2 ON r1.route_id = r2.route_id"
                    + " WHERE r1.stop_id = ? AND r2.pos > r1.pos)"
                    + " ORDER BY s.name_long";

    /**
     * @return Singleton
     */
    public static StopProviderImpl getInstance() {
        return instance;
    }

    private StopProviderImpl(){
        instance = new StopProviderImpl();
        dbHelper = new DataBaseHelper();
        dbHelper.createDataBase();
    }


    @Override
    public List<Stop> getStops() {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_ALL, null);
        List<Stop> result = readCursorList(cur);
        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<Stop> getDestinations(Integer idOrigin) {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_BY_DESTINATIONS, idOrigin.toString());
        List<Stop> result = readCursorList(cur);
        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public Stop getStop(Integer idStop) {
        dbHelper.openDataBase();
        Cursor cur = dbHelper.rawQuery(Q_BY_ID, idStop.toString());
        Stop result = readFirst(cur);
        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public <T extends Stop> List<T> findStopsInStopCollection(Collection<T> routeStops, Integer... stopsToFind) {
        return null;
    }

    private List<Stop> readCursorList(Cursor c) {
        List<Stop> result = new ArrayList<Stop>();
        if (c.moveToFirst()) {
            int id = 0;
            int name = 0;
            do {
                if (id == 0) {  //Init column index
                    id = c.getColumnIndex(Naming.STOP._ID);
                    name = c.getColumnIndex(Naming.STOP.NAME_LONG);
                }
                result.add(readCursorRow(c, id, name));
            } while (c.moveToNext());
        }
        return result;
    }

    private Stop readFirst(Cursor c) {
        Stop result = null;
        if(c.moveToFirst()) {
            int id = c.getColumnIndex(Naming.STOP._ID);
            int name = c.getColumnIndex(Naming.STOP.NAME_LONG);
            result = readCursorRow(c, id, name);
        }
        return result;
    }

    private Stop readCursorRow(Cursor c, int id, int name) {
        Stop result = new Stop();
        result.id = c.getInt(id);
        result.name = c.getString(name);
        return result;
    }
}
