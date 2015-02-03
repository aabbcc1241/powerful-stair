package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairPairDAO extends StairPair {
    public static final String TABLE_NAME = "stair_pair";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UP_CODE_COL + " TEXT NOT NULL, " +
                    DOWN_CODE_COL + " TEXT NOT NULL, " +
                    DISTANCE_COL + " REAL NOT NULL)";
    public static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    private final MainActivity mainActivity;
    protected List<StairPair> stairPairs = null;

    public StairPairDAO(MainActivity mainActivity) {
        super();
        this.mainActivity = mainActivity;
    }

    public List<StairPair> getStairPairs() {
        if (stairPairs == null)
            stairPairs = getAll();
        return stairPairs;
    }


    @Deprecated
    public synchronized void insert(StairPair item) {
        mainActivity.myDAO.insertStairPair(item);
    }

    public synchronized int getCount() {
        Log.w("StairMapItemDAO", "getCount");
        Cursor cursor = StairMapDatabaseHelper.getDatabase().rawQuery("select count (*) from " + TABLE_NAME, null);
        if (!cursor.moveToFirst())
            return 0;
        else
            return cursor.getInt(0);
    }

    public synchronized List<StairPair> getAll() {
        List<StairPair> result = new ArrayList<>();
        Cursor cursor = StairMapDatabaseHelper.getDatabase().query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getRecord(cursor));
        cursor.close();
        return result;
    }

    public boolean isExist(String code) {
        List<StairPair> list = getAll();
        for (StairPair item : list) {
            if (code.equals(item.up_code) || code.equals(item.down_code))
                return true;
        }
        return false;
    }

    public void updateList() {
        stairPairs = getAll();
    }

    public StairPair getPair(String code1, String code2) {
        getStairPairs();
        StairPair result = null;
        for (StairPair item : stairPairs) {
            if (item.isPair(code1, code2))
                result = item;
        }
        return result;
    }

    public double getDistance(String code1, String code2) {
        getStairPairs();
        StairPair item = getPair(code1, code2);
        if (item == null) return 0d;
        return item.distance;
    }

    public StairPair getRecord(Cursor cursor) {
        StairPair result = new StairPair();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.distance = cursor.getDouble(3);

        return result;
    }
}
