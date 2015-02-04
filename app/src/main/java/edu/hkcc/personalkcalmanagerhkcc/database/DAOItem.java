package edu.hkcc.personalkcalmanagerhkcc.database;

import android.database.sqlite.SQLiteDatabase;

import edu.hkcc.personalkcalmanagerhkcc.database.stair.StairPair;

/**
 * Created by beenotung on 2/4/15.
 */
public interface DAOItem<ItemType> {
    public abstract String getTableName();

    public abstract String getTableColId();

    public abstract String[] getTableColumns();

    public abstract String getTableCreate();

    public abstract String getTableDrop();

    public abstract void insert(SQLiteDatabase database, ItemType item);

    public abstract int getCount(SQLiteDatabase database);

    public abstract ItemType[] getAll(SQLiteDatabase database);
}
