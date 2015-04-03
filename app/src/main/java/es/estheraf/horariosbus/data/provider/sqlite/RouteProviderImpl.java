package es.estheraf.horariosbus.data.provider.sqlite;

import org.joda.time.LocalDate;

import java.util.List;

import es.estheraf.horariosbus.data.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.provider.RouteProvider;
import es.estheraf.horariosbus.data.provider.sqlite.cursor.RouteCursor;
import es.estheraf.horariosbus.data.provider.sqlite.cursor.SimpleResultRouteCursor;
import es.estheraf.horariosbus.data.provider.sqlite.definition.RouteDef;

/**
 * Created by Esther on 21/03/2015.
 */
public class RouteProviderImpl implements RouteProvider {

    private static RouteProviderImpl instance = new RouteProviderImpl();
    private static DataBaseHelper dbHelper;

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

        RouteCursor cur = new RouteCursor(dbHelper.rawQuery(RouteDef.QUERY.BY_STOP, idStop.toString()));
        List<Route> result = cur.getAllValues();
        cur.close();

        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<Route> getRoutes() {
        dbHelper.openDataBase();

        RouteCursor cur = new RouteCursor(dbHelper.rawQuery(RouteDef.QUERY.ALL));
        List<Route> result = cur.getAllValues();
        cur.close();

        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<SimpleResultRoute> findRoutes(LocalDate date, int idOrigin, int idDestination) {
        dbHelper.openDataBase();

        SimpleResultRouteCursor cur = new SimpleResultRouteCursor(dbHelper.rawQuery(RouteDef.QUERY.BEWTEEN_STOPS));
        List<SimpleResultRoute> simpleResultRoutes = cur.getAllValues();
        cur.close();

        dbHelper.closeDataBase();
        return simpleResultRoutes;
    }
}
