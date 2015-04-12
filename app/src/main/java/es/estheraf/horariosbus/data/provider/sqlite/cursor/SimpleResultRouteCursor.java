package es.estheraf.horariosbus.data.provider.sqlite.cursor;

import android.database.Cursor;

import org.joda.time.LocalTime;

import es.estheraf.horariosbus.data.model.SimpleResultRoute;
import es.estheraf.horariosbus.data.provider.sqlite.definition.RouteDef;
import es.estheraf.horariosbus.data.provider.sqlite.definition.RouteScheduleDef;

/**
 * Created by Esther on 03/04/2015.
 */
public class SimpleResultRouteCursor extends CustomCursor<SimpleResultRoute> {

    public SimpleResultRouteCursor(Cursor cursor) {
        super(cursor);
    }

    @Override
    public SimpleResultRoute getCurrentValue() {
        SimpleResultRoute result = new SimpleResultRoute();
        result.routeId = getId();
        result.routeName = cursor.getString(getIndex(RouteDef.NAME_SHORT));
        LocalTime departureTime = new LocalTime(cursor.getLong(getIndex(RouteScheduleDef.DEPARTURE_TIME)));
        result.departureTime = departureTime.plusSeconds(cursor.getInt(getIndex("origin_time")));
        result.destinationTime = departureTime.plusSeconds(cursor.getInt(getIndex("dest_time")));
        return result;
    }
}
