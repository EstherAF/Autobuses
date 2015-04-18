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
        return getInt(_ID);
    }

    /**
     * Gets de positional index of a column, in the given cursor, and caches it
     *
     * @param column column's name
     * @return positional index of the column
     */
    protected Integer getIndex(String column) throws DataBaseException {
        if (columnIndexes.containsKey(column)) {  //Already cached
            return columnIndexes.get(column);
        } else {    //Not cached
            int indexColumn = cursor.getColumnIndex(column);    //Query
            if (indexColumn == -1) {
                throw new DataBaseException("Column " + column + " not found. Existing columns: " + Util.toString(cursor.getColumnNames()));
            }
            columnIndexes.put(column, indexColumn);         //Cache query
            Log.v(TAG, "Cached column " + column + ", index " + indexColumn);
            return indexColumn;
        }

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

    protected String getString(String columnName) {
        try {
            return cursor.getString(getIndex(columnName));
        } catch (DataBaseException ex) {
            Log.e(TAG, "Error getting string column " + columnName, ex);
            return null;
        }
    }

    protected int getInt(String columnName) {
        try {
            return cursor.getInt(getIndex(columnName));
        } catch (DataBaseException ex) {
            Log.e(getClass().getName(), "Error getting int column " + columnName, ex);
            return 0;
        }
    }

    protected long getLong(String columnName) {
        try {
            return cursor.getLong(getIndex(columnName));
        } catch (DataBaseException ex) {
            Log.e(getClass().getName(), "Error getting long column " + columnName, ex);
            return 0;
        }
    }
}
