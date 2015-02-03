package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;

/**
 * Created by beenotung on 2/3/15.
 */
public class MyDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "powerful_stair.db";
    public static final String TABLE_STAIR_PAIR = "stair_pair";
    public static final String TABLE_DROP_STAIR_PAIR = "DROP TABLE IF EXISTS " + TABLE_STAIR_PAIR;
    public static final String TABLE_STAIR_PAIR_COL_ID = StairPair.ID_COL;
    public static final String TABLE_STAIR_PAIR_COL_UP_CODE = StairPair.UP_CODE_COL;
    public static final String TABLE_STAIR_PAIR_COL_DOWN_CODE = StairPair.DOWN_CODE_COL;
    public static final String TABLE_STAIR_PAIR_COL_DISTANCE = StairPair.DISTANCE_COL;
    public static final String[] TABLE_STAIR_PAIR_COLUMNS = {TABLE_STAIR_PAIR_COL_ID, TABLE_STAIR_PAIR_COL_UP_CODE, TABLE_STAIR_PAIR_COL_DOWN_CODE, TABLE_STAIR_PAIR_COL_DISTANCE};
    public static final String TABLE_CREATE_STAIR_PAIR =
            "CREATE TABLE IF NOT EXISTS " + TABLE_STAIR_PAIR + " (" +
                    TABLE_STAIR_PAIR_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TABLE_STAIR_PAIR_COL_UP_CODE + " TEXT NOT NULL, " +
                    TABLE_STAIR_PAIR_COL_DOWN_CODE + " TEXT NOT NULL, " +
                    TABLE_STAIR_PAIR_COL_DISTANCE + " REAL NOT NULL)";
    public static int VERSION = 1;
    public MainActivity mainActivity;

    public MyDAO(MainActivity mainActivity, SQLiteDatabase.CursorFactory factory) {
        super(mainActivity, DATABASE_NAME, factory, VERSION);
        this.mainActivity = mainActivity;
    }

    public MyDAO(MainActivity mainActivity) {
        this(mainActivity, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_STAIR_PAIR);
        insertStairPairsFromXml();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_STAIR_PAIR);
        onCreate(db);
        VERSION = newVersion;
    }

    public synchronized void insertStairPairsFromXml() {
        String[] rawStrings = mainActivity.getResources().getStringArray(R.array.stair_pair_string_array);
        Vector<StairPair> stairPairs = new Vector<>();
        int j; //buffer
        String upCode = "", downCode = "";
        double distance;
        for (int i = 0; i < rawStrings.length; i++) {
            j = i % 3;
            switch (j) {
                case 0:
                    upCode = rawStrings[i];
                    break;
                case 1:
                    downCode = rawStrings[i];
                    break;
                case 2:
                    distance = Double.parseDouble(rawStrings[i]);
                    stairPairs.add(new StairPair(upCode, downCode, distance));
                    break;
            }
        }
        StairPairDAO dao = new StairPairDAO(mainActivity);
        for (StairPair item : stairPairs)
            dao.insert(item);
    }

    public synchronized void insertStairPair(StairPair item) {
        ContentValues contentValues = new ContentValues();

        //contentValues.put(ID_COL,item.id);
        contentValues.put(TABLE_STAIR_PAIR_COL_UP_CODE, item.up_code);
        contentValues.put(TABLE_STAIR_PAIR_COL_DOWN_CODE, item.down_code);
        contentValues.put(TABLE_STAIR_PAIR_COL_DISTANCE, item.distance);

        //long id = database.insertStairPair(TABLE_NAME, null, contentValues);

        long id = database.insertWithOnConflict(TABLE_STAIR_PAIR, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }
    public SQLiteDatabase database=getWritableDatabase();

    public StairPair getStairMapRecord(Cursor cursor) {
        StairPair result = new StairPair();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.distance = cursor.getDouble(3);

        return result;
    }

    public synchronized List<StairPair> getAllStairPairItems() {
        List<StairPair> result = new ArrayList<>();
        Cursor cursor =database.query(TABLE_STAIR_PAIR, null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getStairMapRecord(cursor));
        cursor.close();
        return result;
    }

    public synchronized int getStairPairCount() {
        Log.w("StairMapItemDAO", "getCount");
        Cursor cursor = StairMapDatabaseHelper.getDatabase().rawQuery("select count (*) from "+TABLE_STAIR_PAIR,null);
        if(!cursor.moveToFirst())
            return 0;
        else
            return cursor.getInt(0);
    }

    public synchronized boolean isStairCodeExist(String code) {
        List<StairPair> list = getAllStairPairItems();
        for (StairPair item : list) {
            if (code.equals(item.up_code) || code.equals(item.down_code))
                return true;
        }
        return false;
    }
}
