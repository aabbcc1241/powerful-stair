package edu.hkcc.personalkcalmanagerhkcc.stair;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairMapDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "stairmap.db";
    public static final int VERSION = 1;
    public static SQLiteDatabase database;

    public StairMapDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public StairMapDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public static SQLiteDatabase getDatabase(Context context) {
        if (database == null || !database.isOpen())
            database = new StairMapDatabaseHelper(context, DATABASE_NAME, null, VERSION).getWritableDatabase();
        return database;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
