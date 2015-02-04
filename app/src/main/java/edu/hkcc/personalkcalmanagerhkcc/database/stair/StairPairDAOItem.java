package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.database.sqlite.SQLiteDatabase;

import edu.hkcc.personalkcalmanagerhkcc.database.DAOItem;

/**
 * Created by beenotung on 2/4/15.
 */
public class StairPairDAOItem implements DAOItem<StairPair> {

    @Override
    public String getTableName() {
        return null;
    }

    @Override
    public String getTableColId() {
        return null;
    }

    @Override
    public String[] getTableColumns() {
        return new String[0];
    }

    @Override
    public String getTableCreate() {
        return null;
    }

    @Override
    public String getTableDrop() {
        return null;
    }

    @Override
    public void insert(SQLiteDatabase database, StairPair item) {

    }

    @Override
    public int getCount(SQLiteDatabase database) {
        return 0;
    }

    @Override
    public StairPair[] getAll(SQLiteDatabase database) {
        return new StairPair[0];
    }
}
