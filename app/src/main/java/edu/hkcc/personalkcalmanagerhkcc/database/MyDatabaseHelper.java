package edu.hkcc.personalkcalmanagerhkcc.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

/**
 * Created by beenotung on 1/17/15.
 */
public class MyDatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "hkccstair.db";
    public static final int VERSION = 1;
    public static SQLiteDatabase database;
    public MainActivity mainActivity;

    public MyDatabaseHelper(MainActivity mainActivity,  String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(mainActivity, name, factory, version);
        this.mainActivity = mainActivity;
        myInit();
    }

    public MyDatabaseHelper(MainActivity mainActivity,  String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(mainActivity, name, factory, version, errorHandler);
        this.mainActivity = mainActivity;
        myInit();
    }

    public static SQLiteDatabase getDatabase(MainActivity mainActivity) {
        if (database == null || !database.isOpen())
            database = new MyDatabaseHelper(mainActivity, DATABASE_NAME, null, VERSION).getWritableDatabase();
        return database;
    }

    protected void myInit(){

    }
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }
}
