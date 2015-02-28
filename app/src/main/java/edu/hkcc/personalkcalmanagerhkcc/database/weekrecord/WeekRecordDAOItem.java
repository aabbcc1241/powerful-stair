package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecord;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecordDAOItem implements DAOItem<WeekRecord> {
    public static final String TABLE_COL_ID = "week_id";
    public static final String TABLE_COL_WEEK_TARGET = "week_target";
    public static final String[] COLUMNS = {TABLE_COL_ID, TABLE_COL_WEEK_TARGET};
    public static WeekRecordDAOItem static_ = new WeekRecordDAOItem(null);

    public WeekRecordDAOItem(MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    private final MyDAO myDAO;
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
                TABLE_COL_WEEK_TARGET + " REAL NOT NULL " +
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

        return result;
    }
}
