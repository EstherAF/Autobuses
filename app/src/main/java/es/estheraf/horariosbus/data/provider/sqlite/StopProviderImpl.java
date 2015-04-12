package es.estheraf.horariosbus.data.provider.sqlite;

import java.util.List;

import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.StopProvider;
import es.estheraf.horariosbus.data.provider.sqlite.cursor.StopCursor;
import es.estheraf.horariosbus.data.provider.sqlite.definition.StopDef;

/**
 * Created by Esther on 21/03/2015.
 */
public class StopProviderImpl implements StopProvider {

    private static StopProviderImpl instance = new StopProviderImpl();
    private static DataBaseHelper dbHelper;



    /**
     * @return Singleton
     */
    public static StopProviderImpl getInstance() {
        return instance;
    }

    private StopProviderImpl(){
        dbHelper = new DataBaseHelper();
        dbHelper.createDataBase();
    }


    @Override
    public List<Stop> getStops() {
        dbHelper.openDataBase();

        StopCursor cur = new StopCursor(dbHelper.rawQuery(StopDef.QUERY.ALL));
        List<Stop> result = cur.getAllValues();
        cur.close();

        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public List<Stop> getDestinations(Integer idOrigin) {
        dbHelper.openDataBase();

        StopCursor cur = new StopCursor(dbHelper.rawQuery(StopDef.QUERY.BY_DESTINATIONS, idOrigin.toString()));
        List<Stop> result = cur.getAllValues();
        cur.close();

        dbHelper.closeDataBase();
        return result;
    }

    @Override
    public Stop getStop(Integer idStop) {
        dbHelper.openDataBase();

        StopCursor cur = new StopCursor( dbHelper.rawQuery(StopDef.QUERY.BY_ID, idStop.toString()) );
        Stop result = cur.getFirst();
        cur.close();

        dbHelper.closeDataBase();
        return result;
    }
}
