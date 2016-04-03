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
        result.routeShortName = getString(RouteDef.NAME_SHORT);
        result.routeLongName = getString(RouteDef.NAME_LONG);
        LocalTime departureTime = new LocalTime(getLong(RouteScheduleDef.DEPARTURE_TIME));
        result.departureTime = departureTime.plusSeconds(getInt(RouteDef.ALIAS_ORIGIN_TIME));
        result.destinationTime = departureTime.plusSeconds(getInt(RouteDef.ALIAS_DEST_TIME));
        return result;
    }
}
