package edu.hkcc.personalkcalmanagerhkcc.stair;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairMapItemDAO extends StairMapItem {
    public static final String TABLE_NAME = "item";

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    UP_CODE_COL + " TEXT NOT NULL, " +
                    DOWN_CODE_COL + " TEXT NOT NULL, " +
                    DISTANCE_COL + " REAL NOT NULL)";
    protected List<StairMapItem> stairMapItems = null;
    private SQLiteDatabase db;

    public StairMapItemDAO(Context context) {
        super();
        db = StairMapDatabaseHelper.getDatabase(context);
    }

    public List<StairMapItem> getStairMapItems() {
        if (stairMapItems == null)
            stairMapItems = getAll();
        return stairMapItems;
    }

    public void close() {
        db.close();
    }

    public void insert(StairMapItem item) {
        ContentValues contentValues = new ContentValues();

        //contentValues.put(ID_COL,item.id);
        contentValues.put(UP_CODE_COL, item.up_code);
        contentValues.put(DOWN_CODE_COL, item.down_code);
        contentValues.put(DISTANCE_COL, item.distance);

        long id = db.insert(TABLE_NAME, null, contentValues);
        item.id = id;
    }

    public List<StairMapItem> getAll() {
        List<StairMapItem> result = new ArrayList<>();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getRecord(cursor));
        cursor.close();
        return result;
    }

    public void updateList() {
        stairMapItems = getAll();
    }

    public StairMapItem getPair(String code1, String code2) {
        getStairMapItems();
        StairMapItem result = null;
        for (StairMapItem item : stairMapItems) {
            if (item.hasPair(code1, code2))
                result = item;
        }
        return result;
    }

    public double getDistance(String code1, String code2) {
        getStairMapItems();
        StairMapItem item = getPair(code1, code2);
        if (item == null) return 0d;
        return item.distance;
    }

    public StairMapItem getRecord(Cursor cursor) {
        StairMapItem result = new StairMapItem();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.distance = cursor.getDouble(3);

        return result;
    }
}
