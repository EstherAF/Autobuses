package es.estheraf.horariosbus.data.provider.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.estheraf.horariosbus.activity.MainActivity;
import es.estheraf.horariosbus.util.IOUtil;

/**
 * Created by Esther on 21/03/2015.
 */
public class DataBaseHelper extends SQLiteOpenHelper {

    /**
     * DB base configuration.
     * TODO: move it to a xml/properties, to handle it in a declarative way
     */
    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/es.estheraf.horariosbus/databases/";
    private static String DB_NAME = "routes";
    private static String DB_FULL_PATH = DB_PATH + DB_NAME;
    private static int DB_VERSION = 1;




    private SQLiteDatabase myDataBase;
    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     */
    public DataBaseHelper() {
        super(MainActivity.getContext(), DB_NAME, null, DB_VERSION);
        this.myContext = MainActivity.getContext();
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() {
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();
            try {
                copyDataBase();
            } catch (IOException e) {
                throw new Error("Error copying database");
            }
        }
    }

    /**
     * Open the database, to handle it with this helper.
     *
     * @throws SQLiteException
     */
    public void openDataBase() throws SQLiteException {
        //Open the database
        myDataBase = SQLiteDatabase.openDatabase(DB_FULL_PATH, null, SQLiteDatabase.OPEN_READONLY);
    }

    /**
     * If database handled by this helper is open, close it.
     */
    public void closeDataBase() {
        if (myDataBase != null) {
            myDataBase.close();
        }
    }

    @Override
    public synchronized void close() {
        closeDataBase();
        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    // Add your public helper methods to access and get content from the database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd be easy
    // to you to create adapters for your views.

    /**
     * Wrapper of SQLiteDatabase.query, in the SQLiteDataBase handled by this helper
     *
     * @param distinct
     * @param table
     * @param columns
     * @param selection
     * @param selectionArgs
     * @param groupBy
     * @param having
     * @param orderBy
     * @param limit
     * @return Cursor
     */
    public Cursor query(boolean distinct, String table, String[] columns,
                                      String selection, String[] selectionArgs, String groupBy,
                                      String having, String orderBy, String limit) {
        return myDataBase.query(distinct, table, columns, selection, selectionArgs, groupBy, having, orderBy, limit);
    }

    /**
     * Wrapper of SQLiteDatabase.rawQuery, in the SQLiteDataBase handled by this helper
     *
     * @param query
     * @param args
     * @return
     */
    public Cursor rawQuery(String query, String... args){
        return myDataBase.rawQuery(query, args);
    }



    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {
        SQLiteDatabase checkDB = null;
        try {
            //Try to open de DB, if it's already created
            openDataBase();

        } catch (SQLiteException e) {
            //database doesn't exist yet.
        } finally {
            closeDataBase();
            if (checkDB != null) {    //if opened, close it
                checkDB.close();
            }
        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {
        //Open local file (source) and empty db (destination)
        InputStream input = myContext.getAssets().open(DB_NAME);    //local db (sqlite file)
        OutputStream output = new FileOutputStream(DB_FULL_PATH);   //empty db (same name as file)

        //Copy DB, input > output
        IOUtil.copy(input, output);

        //Close the streams
        output.close(); //auto flush
        input.close();
    }
}