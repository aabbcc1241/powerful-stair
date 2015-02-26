package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecordDAOItem implements DAOItem<StairRecord> {
    public static final String TABLE_COL_ID = "stair_record_id";
    public static final String TABLE_COL_STAIR_PAIR_ID = "stair_pair_id";
    public static final String TABLE_COL_DURATION = "duration";
    public static final String TABLE_COL_TIME = "time";
    public static final String[] COLUMNS = {TABLE_COL_ID, TABLE_COL_STAIR_PAIR_ID, TABLE_COL_DURATION, TABLE_COL_TIME};
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
                TABLE_COL_STAIR_PAIR_ID + " INTEGER NOT NULL, " +
                TABLE_COL_DURATION + " INTEGER NOT NULL, " +
                TABLE_COL_TIME + " INTEGER NOT NULL)";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(StairRecord item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_STAIR_PAIR_ID, item.stairPairId);
        contentValues.put(TABLE_COL_DURATION, item.duration);
        contentValues.put(TABLE_COL_TIME, item.time);

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
        result.stairPairId = cursor.getInt(1);
        result.duration = cursor.getInt(2);
        result.time = cursor.getInt(3);

        return result;
    }
}
