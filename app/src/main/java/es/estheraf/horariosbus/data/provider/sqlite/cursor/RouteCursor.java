package es.estheraf.horariosbus.data.provider.sqlite.cursor;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import es.estheraf.horariosbus.data.model.Route;
import es.estheraf.horariosbus.data.provider.sqlite.definition.RouteDef;

/**
 * Created by Esther on 03/04/2015.
 */
public class RouteCursor extends CustomCursor<Route>{

    public static String _TABLE = "route";

    public RouteCursor(Cursor cursor){
        super(cursor);
    }

    @Override
    public Route getCurrentValue() {
        Route result = new Route();
        result.id = super.getId();
        result.nameShort = getString(RouteDef.NAME_SHORT);
        result.nameLong = getString(RouteDef.NAME_LONG);
        //result.days = getDays(getString(Naming.ROUTE.DAYS_MAP));     //not for now
        //result.company = getString(Naming.ROUTE.COMPANY);     //not for now
        //result.desc = getString(Naming.ROUTE.DESC);   //not necessary
        return result;
    }

    private List<Integer> getDays(String days){
        List<Integer> result = new ArrayList<Integer>();
        for(char day : days.toCharArray()){
            result.add((int) day);
        }
        return result;
    }
}
