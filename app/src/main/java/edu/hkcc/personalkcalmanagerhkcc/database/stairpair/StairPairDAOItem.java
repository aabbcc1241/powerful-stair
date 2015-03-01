package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import edu.hkcc.personalkcalmanagerhkcc.R;
import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/4/15.
 */
public class StairPairDAOItem implements DAOItem<StairPair> {
    //public static final String TABLE_COL_ID = "stair_pair_id";
    public static final String TABLE_COL_UP_CODE = "up_code";
    public static final String TABLE_COL_DOWN_CODE = "down_code";
    public static final String TABLE_COL_HEIGHT = "height";
    public static final String[] COLUMNS = {TABLE_COL_UP_CODE, TABLE_COL_DOWN_CODE, TABLE_COL_HEIGHT};
    public static StairPairDAOItem static_ = new StairPairDAOItem(null);
    private final MyDAO myDAO;

    public StairPairDAOItem(MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    @Override
    public String getTableName() {
        return "stair_pair";
    }

    @Deprecated
    @Override
    public String getTableColId() {
        //return TABLE_COL_ID;
        return null;
    }

    @Override
    public String[] getTableColumns() {
        return COLUMNS;
    }

    @Override
    public String getTableCreate() {
        return "CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                //getTableColId() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COL_UP_CODE + " TEXT NOT NULL, " +
                TABLE_COL_DOWN_CODE + " TEXT NOT NULL, " +
                TABLE_COL_HEIGHT + " REAL NOT NULL, " +
                "PRIMARY KEY (" + TABLE_COL_UP_CODE + ", " + TABLE_COL_DOWN_CODE + ") " +
                ")";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(StairPair item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_UP_CODE, item.up_code);
        contentValues.put(TABLE_COL_DOWN_CODE, item.down_code);
        contentValues.put(TABLE_COL_HEIGHT, item.height);

        myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public synchronized boolean isStairCodeExist(String code) {
        List<StairPair> list = getAll();
        for (StairPair item : list) {
            if (code.equals(item.up_code) || code.equals(item.down_code))
                return true;
        }
        return false;
    }

    @Override
    public void insertDefaultData() {
        String[] rawStrings =
                myDAO.mainActivity.getResources().getStringArray(R.array.stair_pair_string_array);
        Vector<StairPair> stairPairs = new Vector<>();
        int j; //buffer
        String upCode = "", downCode = "";
        float distance;
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
                    distance = Float.parseFloat(rawStrings[i]);
                    stairPairs.add(new StairPair(upCode, downCode, distance));
                    break;
            }
        }
        //StairPairDAO_old dao = new StairPairDAO_old(mainActivity);
        for (StairPair item : stairPairs)
            insert(item);
    }

    @Override
    public int getCount() {
        Cursor cursor = myDAO.database.rawQuery("select count (*) from " + getTableName(), null);
        if (!cursor.moveToFirst())
            return 0;
        else
            return cursor.getInt(0);
    }

    @Override
    public List<StairPair> getAll() {
        List<StairPair> result = new ArrayList<>();
        Cursor cursor = myDAO.database.query(getTableName(), null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getItemRecord(cursor));
        cursor.close();
        return result;
    }

    @Override
    public StairPair getItemRecord(Cursor cursor) {
        StairPair result = new StairPair();


        result.up_code = cursor.getString(0);
        result.down_code = cursor.getString(1);
        result.height = cursor.getFloat(2);

        return result;
    }

    public synchronized StairPair getStairPair(String source, String destination) throws StairPairNotFoundException {
        List<StairPair> list = getAll();
        for (StairPair item : list) {
            if ((source.equals(item.up_code) && destination.equals(item.down_code))
                    || (source.equals(item.down_code) && destination.equals(item.up_code)))
                return item;
        }
        throw new StairPairNotFoundException(source, destination);
    }

}
