package es.estheraf.horariosbus.data.provider.sqlite.cursor;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.estheraf.horariosbus.util.Util;

/**
 * Created by Esther on 03/04/2015.
 */
public abstract class CustomCursor<C> {

    private Map<String, Integer> columnIndexes = new HashMap<String, Integer>();

    public static String _ID = "_id";

    public Cursor cursor;

    public CustomCursor(Cursor cursor) {
        cursor = cursor;
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
        List<C> result = new ArrayList<C>();
        if (cursor.moveToFirst()) {
            do {
                result.add(getCurrentValue());
            } while (cursor.moveToNext());  //move cursor
        }

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
