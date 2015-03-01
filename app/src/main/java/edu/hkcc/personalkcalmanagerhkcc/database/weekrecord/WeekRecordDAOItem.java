package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecordDAOItem implements DAOItem<WeekRecord> {
    public static final String TABLE_COL_ID = "week_id";
    public static final String TABLE_COL_WEEK_TARGET = "week_target";
    public static final String TABLE_COL_CREATE_TIME = "create_time";
    public static final String[] COLUMNS = {TABLE_COL_ID, TABLE_COL_WEEK_TARGET, TABLE_COL_CREATE_TIME};
    public static WeekRecordDAOItem static_ = new WeekRecordDAOItem(null);
    private final MyDAO myDAO;

    public WeekRecordDAOItem(MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    @Override
    public String getTableName() {
        return "week_record";
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
                getTableColId() + " INTEGER PRIMARY KEY NOT NULL, " +
                TABLE_COL_WEEK_TARGET + " REAL NOT NULL, " +
                TABLE_COL_CREATE_TIME + " INTEGER NOT NULL " +
                ")";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(WeekRecord item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_ID, item.weekId);
        contentValues.put(TABLE_COL_WEEK_TARGET, item.weekTarget);
        contentValues.put(TABLE_COL_CREATE_TIME, item.createTime);

        myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
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
    public List<WeekRecord> getAll() {
        List<WeekRecord> result = new ArrayList<>();
        Cursor cursor = myDAO.database.query(getTableName(), null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getItemRecord(cursor));
        cursor.close();
        return result;
    }

    @Override
    public WeekRecord getItemRecord(Cursor cursor) {
        WeekRecord result = new WeekRecord();

        result.weekId = cursor.getLong(0);
        result.weekTarget = cursor.getFloat(1);
        result.createTime = cursor.getLong(2);

        return result;
    }

    public synchronized WeekRecord getWeekRecord(long weekId) throws WeekRecordNotFoundException {
        List<WeekRecord> list = getAll();
        for (WeekRecord item : list) {
            if (item.weekId == weekId)
                return item;
        }
        throw new WeekRecordNotFoundException();
    }

    public synchronized float getWeekTarget(long weekId) throws WeekRecordNotFoundException {
        return getWeekRecord(weekId).getCalSum();
    }

    public long getFirstMillisecond() throws WeekRecordNotFoundException {
        List<WeekRecord> records = getAll();
        if (records.size() <= 0) throw new WeekRecordNotFoundException();
        long result = records.get(0).createTime;
        for (WeekRecord record : records)
            if (result > record.createTime)
                result = record.createTime;
        return result;
    }
}
