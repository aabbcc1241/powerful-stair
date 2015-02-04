package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;

/**
 * Created by beenotung on 2/4/15.
 */
public class StairPairDAOItem implements DAOItem<StairPair> {

    public static final String TABLE_COL_UP_CODE = StairPair.UP_CODE_COL;
    public static final String TABLE_COL_DOWN_CODE = StairPair.DOWN_CODE_COL;
    public static final String TABLE_COL_DISTANCE = StairPair.DISTANCE_COL;

    @Override
    public String getTableName() {
        return "stair_pair";
    }

    @Override
    public String getTableColId() {
        return StairPair.ID_COL;
    }

    @Override
    public String[] getTableColumns() {
        return new String[]{getTableColId(), TABLE_COL_UP_CODE, TABLE_COL_DOWN_CODE, TABLE_COL_DISTANCE};
    }

    @Override
    public String getTableCreate() {
        return "CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                getTableColId() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COL_UP_CODE + " TEXT NOT NULL, " +
                TABLE_COL_DOWN_CODE + " TEXT NOT NULL, " +
                TABLE_COL_DISTANCE + " REAL NOT NULL)";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(SQLiteDatabase database, StairPair item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_UP_CODE, item.up_code);
        contentValues.put(TABLE_COL_DOWN_CODE, item.down_code);
        contentValues.put(TABLE_COL_DISTANCE, item.distance);

        long id = database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }

    @Override
    public int getCount(SQLiteDatabase database) {
        Cursor cursor = StairMapDatabaseHelper.getDatabase().rawQuery("select count (*) from " + getTableName(), null);
        if (!cursor.moveToFirst())
            return 0;
        else
            return cursor.getInt(0);
    }

    @Override
    public List<StairPair> getAll(SQLiteDatabase database) {
        List<StairPair> result = new ArrayList<>();
        Cursor cursor = database.query(getTableName(), null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getItemRecord(cursor));
        cursor.close();
        return result;
    }

    @Override
    public StairPair getItemRecord(Cursor cursor) {
        StairPair result = new StairPair();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.distance = cursor.getDouble(3);

        return result;
    }
}
