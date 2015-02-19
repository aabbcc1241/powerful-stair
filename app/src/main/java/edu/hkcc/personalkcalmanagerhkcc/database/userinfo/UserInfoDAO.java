package edu.hkcc.personalkcalmanagerhkcc.database.userinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.ContentPair;
import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/19/15.
 */
public class UserInfoDAO implements DAOItem<UserInfo> {
    public static final String TABLE_COL_NAME = "var_name";
    public static final String TABLE_COL_VALUE = "var_value";
    public static final String[] COLUMNS = {TABLE_COL_NAME, TABLE_COL_VALUE};
    public static UserInfoDAO static_ = new UserInfoDAO(null);
    private final MyDAO myDAO;

    public UserInfoDAO(MyDAO myDAO) {
        this.myDAO = myDAO;
    }

    @Override
    public String getTableName() {
        return "user_info";
    }

    @Override
    public String getTableColId() {
        return TABLE_COL_NAME;
    }

    @Override
    public String[] getTableColumns() {
        return COLUMNS;
    }

    @Override
    public String getTableCreate() {
        return "CREATE TABLE IF NOT EXISTS " + getTableName() + " (" +
                getTableColId() + " TEXT PRIMARY KEY NOT NULL, " +
                TABLE_COL_VALUE + " TEXT)";
    }

    @Override
    public String getTableDrop() {
        return "DROP TABLE IF EXISTS " + getTableName();
    }

    @Override
    public void insert(UserInfo item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_NAME, item.up_code);
        contentValues.put(TABLE_COL_VALUE, item.down_code);
        contentValues.put(TABLE_COL_DISTANCE, item.distance);

        long id = myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }

    public void insert(ContentPair item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_NAME, item.name);
        contentValues.put(TABLE_COL_VALUE, item.value);

        myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void insertFromXml() {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    public UserInfo getItemRecord(Cursor cursor) {
        return null;
    }
}
