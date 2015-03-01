package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecordDAOItem implements DAOItem<StairRecord> {
    public static final String TABLE_COL_ID = "stair_record_id";
    public static final String TABLE_COL_UP_CODE = "up_code";
    public static final String TABLE_COL_DOWN_CODE = "down_code";
    public static final String TABLE_COL_CAL_BURNED = "cal_burned";
    public static final String TABLE_COL_MILLISECOND = "millisecond";
    public static final String TABLE_COL_WEEK_ID = "week_id";
    public static final String[] COLUMNS = {TABLE_COL_ID, TABLE_COL_UP_CODE, TABLE_COL_DOWN_CODE, TABLE_COL_CAL_BURNED, TABLE_COL_MILLISECOND, TABLE_COL_WEEK_ID};
    public static StairRecordDAOItem static_ = new StairRecordDAOItem(null);
    private final MyDAO myDAO;

    public StairRecordDAOItem(MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    @Override
    public String getTableName() {
        return "stair_record";
    }

    @Override
    public String getTableColId() {
        return TABLE_COL_ID;
    }

    @Override
    public String[] getTableColumns() {
        return COLUMNS;
    }

    @Override
    public String getTableCreate() {
        return "CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                getTableColId() + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TABLE_COL_UP_CODE + " TEXT NOT NULL, " +
                TABLE_COL_DOWN_CODE + " TEXT NOT NULL, " +
                TABLE_COL_CAL_BURNED + " REAL NOT NULL, " +
                TABLE_COL_MILLISECOND + " INTEGER NOT NULL, " +
                TABLE_COL_WEEK_ID + " INTEGER NOT NULL" +
                ")";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(StairRecord item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_UP_CODE, item.up_code);
        contentValues.put(TABLE_COL_DOWN_CODE, item.down_code);
        contentValues.put(TABLE_COL_CAL_BURNED, item.calBurned);
        contentValues.put(TABLE_COL_MILLISECOND, item.millisecond);
        contentValues.put(TABLE_COL_WEEK_ID, item.weekId);
        Log.w("insert cal",item.calBurned+"");

        long id = myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }

    @Override
    public void insertDefaultData() {

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
    public List<StairRecord> getAll() {
        List<StairRecord> result = new ArrayList<>();
        Cursor cursor = myDAO.database.query(getTableName(), null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getItemRecord(cursor));
        cursor.close();
        return result;
    }

    @Override
    public StairRecord getItemRecord(Cursor cursor) {
        StairRecord result = new StairRecord();

        result.id = cursor.getLong(0);
        result.up_code = cursor.getString(1);
        result.down_code = cursor.getString(2);
        result.calBurned = cursor.getFloat(3);
        result.millisecond = cursor.getLong(4);
        result.weekId = cursor.getLong(5);

        return result;
    }

    public List<StairRecord> getAllInSameWeek(long weekId) {
        List<StairRecord> all = getAll();
        List<StairRecord> result = new ArrayList<StairRecord>();
        for (StairRecord record : all)
            if (record.weekId == weekId)
                result.add(record);
        return result;
    }

    public float getSumCalInSameWeek(long weekId) {
        List<StairRecord> records = getAllInSameWeek(weekId);
        float sum = 0f;
        for (StairRecord record : records)
            sum += record.calBurned;
        return sum;
    }
}
