package edu.hkcc.personalkcalmanagerhkcc.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stair.StairPairDAOItem;

/**
 * Created by beenotung on 2/3/15.
 */
public class MyDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "powerful_stair.db";

    public static int VERSION = 1;
    public MainActivity mainActivity;
    public SQLiteDatabase database = getWritableDatabase();

    public StairPairDAOItem stairPairDAOItem=new StairPairDAOItem(this);

    public MyDAO(MainActivity mainActivity, SQLiteDatabase.CursorFactory factory) {
        super(mainActivity, DATABASE_NAME, factory, VERSION);
        this.mainActivity = mainActivity;
    }

    public MyDAO(MainActivity mainActivity) {
        this(mainActivity, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(stairPairDAOItem.getTableCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
        VERSION = newVersion;
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL(stairPairDAOItem.getTableDrop());
    }

    public synchronized void myInit() {
        insertFromXml();
    }

    public void insertFromXml() {
        stairPairDAOItem.insertFromXml();
    }


}
