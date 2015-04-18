package es.estheraf.horariosbus.data.provider.sqlite.cursor;

import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.estheraf.horariosbus.data.provider.exception.DataBaseException;
import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 03/04/2015.
 */
public abstract class CustomCursor<C> {
    //Constants
    public static final String _ID = "_id";   //PK column's name
    public final String TAG = this.getClass().getSimpleName();

    //Instance's attributes
    public Cursor cursor;   //DB cursor to handle
    private Map<String, Integer> columnIndexes = new HashMap<String, Integer>();    //Map of <columnName, idColumns>

    public CustomCursor(Cursor cursor) {
        cursor = cursor;
        Log.v(TAG, "Constructor, cursor is " + ((cursor == null) ? "" : "not") + " empty");
    }

    public Integer getId() {
        return cursor.getInt(getIndex(_ID));
    }

    protected Integer getIndex(String column) {
        if (!columnIndexes.containsKey(column)) {
            return insertColumnIndex(column);
        } else
            return columnIndexes.get(column);
    }

    private int insertColumnIndex(String column) {
        int index = cursor.getColumnIndex(column);
        columnIndexes.put(column, index);
        return index;
    }

    public abstract C getCurrentValue();

    public C getFirst() {
        if (cursor.moveToFirst())
            return getCurrentValue();
        else
            return null;
    }

    public List<C> getAllValues() {
        Log.v(TAG, "Start getAllValues()");
        List<C> result = new ArrayList<C>();
        if (cursor.moveToFirst()) {
            do {
                result.add(getCurrentValue());
            } while (cursor.moveToNext());  //move cursor
        }
        Log.v(TAG, "End getAllValues(). "+result.size()+" results.");
        return result;
    }

    public List<C> getPaginatedValues(Integer numResults) {
        List<C> result = new ArrayList<C>();
        if (Util.isEmpty(numResults)) {  //0 or null = no pagination
            return getAllValues();
        }

        int index = 0;
        if (cursor.moveToFirst()) {
            do {
                result.add(getCurrentValue());
            } while (++index < numResults && cursor.moveToNext());  //increment index, move cursor
        }

        return result;
    }

    public void close() {
        cursor.close();
        this.cursor = null;
    }
}
