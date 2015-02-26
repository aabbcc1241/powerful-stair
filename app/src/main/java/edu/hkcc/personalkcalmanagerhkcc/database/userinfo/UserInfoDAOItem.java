package edu.hkcc.personalkcalmanagerhkcc.database.userinfo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import edu.hkcc.personalkcalmanagerhkcc.database.ContentPair;
import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;

/**
 * Created by beenotung on 2/19/15.
 */
public class UserInfoDAOItem implements DAOItem<UserInfo> {
    public static final String TABLE_COL_NAME = "var_name";
    public static final String TABLE_COL_VALUE = "var_value";
    public static final String[] COLUMNS = {TABLE_COL_NAME, TABLE_COL_VALUE};
    public static UserInfoDAOItem static_ = new UserInfoDAOItem(null);
    private final MyDAO myDAO;

    public UserInfoDAOItem(MyDAO myDAO) {
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
        insert(item.name);
        insert(item.age);
        insert(item.height);
        insert(item.weight);
        insert(item.bmi);
    }

    public void insert(ContentPair item) {
        ContentValues contentValues = new ContentValues();

        contentValues.put(TABLE_COL_NAME, item.name);
        contentValues.put(TABLE_COL_VALUE, item.value);

        myDAO.database.insertWithOnConflict(getTableName(), null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
    }

    @Override
    public void insertDefaultData() {
        insert(new UserInfo());
    }

    @Override
    @Deprecated
    public int getCount() {
        Cursor cursor = myDAO.database.rawQuery("select count (*) from " + getTableName(), null);
        if (!cursor.moveToFirst())
            return 0;
        else
            return cursor.getInt(0);
    }

    @Override
    @Deprecated
    public List<UserInfo> getAll() {
        return null;
    }

    @Override
    @Deprecated
    public UserInfo getItemRecord(Cursor cursor) {
        return null;
    }

    public List<ContentPair> getContentPairs() {
        List<ContentPair> result = new ArrayList<>();
        Cursor cursor = myDAO.database.query(getTableName(), null, null, null, null, null, null);
        while (cursor.moveToNext())
            result.add(getContentPairRecord(cursor));
        cursor.close();
        return result;
    }

    protected ContentPair getContentPairRecord(Cursor cursor) {
        ContentPair result = new ContentPair(cursor.getString(0));
        result.value = cursor.getString(1);
        return result;
    }

    public void getContentValue(List<ContentPair> contentPairs, ContentPair contentPair) {
        for (ContentPair iContentPair : contentPairs)
            if (contentPair.name.equals(iContentPair.name))
                contentPair.value = iContentPair.value;
    }

    public UserInfo getUserInfo() {
        List<ContentPair> contentPairs = getContentPairs();

        UserInfo userInfo = new UserInfo();
        getContentValue(contentPairs, userInfo.name);
        getContentValue(contentPairs, userInfo.age);
        getContentValue(contentPairs, userInfo.height);
        getContentValue(contentPairs, userInfo.weight);
        getContentValue(contentPairs, userInfo.bmi);

        return userInfo;
    }

}
