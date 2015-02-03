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
public class StairPairItemDAO extends StairPairItem {
    public static final String TABLE_NAME = "stair_pair";

    public static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UP_CODE_COL + " TEXT NOT NULL, " +
                    DOWN_CODE_COL + " TEXT NOT NULL, " +
                    DISTANCE_COL + " REAL NOT NULL)";
    public static final String DROP_TABLE="DROP TABLE IF EXISTS "+TABLE_NAME;
    protected List<StairPairItem> stairPairItems = null;

    public StairPairItemDAO(MainActivity mainActivity) {
        super();
    }

    public List<StairPairItem> getStairPairItems() {
        if (stairPairItems == null)
            stairPairItems = getAll();
        return stairPairItems;
    }



    public synchronized void insert(StairPairItem item) {
        Log.w("StairMapItemDAO", "insert");
        ContentValues contentValues = new ContentValues();

        //contentValues.put(ID_COL,item.id);
        contentValues.put(UP_CODE_COL, item.up_code);
        contentValues.put(DOWN_CODE_COL, item.down_code);
        contentValues.put(DISTANCE_COL, item.distance);

        //long id = database.insert(TABLE_NAME, null, contentValues);
        long id = StairMapDatabaseHelper.getDatabase().insertWithOnConflict(TABLE_NAME, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }

    public synchronized int getCount() {
        Log.w("StairMapItemDAO", "getCount");
        Cursor cursor = StairMapDatabaseHelper.getDatabase().rawQuery("select count (*) from "+TABLE_NAME,null);
        if(!cursor.moveToFirst())
            return 0;
        else
        return cursor.getInt(0);
    }

    public synchronized List<StairPairItem> getAll() {
        List<StairPairItem> result = new ArrayList<>();
        Cursor cursor = StairMapDatabaseHelper.getDatabase().query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getRecord(cursor));
        cursor.close();
        return result;
    }

    public  boolean isExist(String code) {
        List<StairPairItem> list = getAll();
        for (StairPairItem item : list) {
            if(code.equals(item.up_code)||code.equals(item.down_code))
                return true;
        }
        return false;
    }

    public void updateList() {
        stairPairItems = getAll();
    }

    public StairPairItem getPair(String code1, String code2) {
        getStairPairItems();
        StairPairItem result = null;
        for (StairPairItem item : stairPairItems) {
            if (item.isPair(code1, code2))
                result = item;
        }
        return result;
    }

    public double getDistance(String code1, String code2) {
        getStairPairItems();
        StairPairItem item = getPair(code1, code2);
        if (item == null) return 0d;
        return item.distance;
    }

    public StairPairItem getRecord(Cursor cursor) {
        StairPairItem result = new StairPairItem();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.distance = cursor.getDouble(3);

        return result;
    }
}
