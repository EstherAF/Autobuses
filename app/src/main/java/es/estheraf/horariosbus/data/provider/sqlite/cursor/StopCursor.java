package es.estheraf.horariosbus.data.provider.sqlite.cursor;

import android.database.Cursor;

import es.estheraf.horariosbus.data.model.Stop;
import es.estheraf.horariosbus.data.provider.sqlite.definition.StopDef;

/**
 * Created by Esther on 03/04/2015.
 */
public class StopCursor extends CustomCursor<Stop>{

    public StopCursor(Cursor cursor){
        super(cursor);
    }

    @Override
    public Stop getCurrentValue() {
        Stop result = new Stop();
        result.id = super.getId();
        result.nameLong = getString(StopDef.NAME_LONG);
        //result.nameShort = getString(StopDef.NAME_SHORT);
        //result.coords = cursor.getString(getIndex(COORDS));       //not for now
        //result.parentId = cursor.getInt(getIndex(PARENT_ID));     //not for now
        //result.desc = cursor.getString(getIndex(DESC));   //not necessary
        return result;
    }
}
