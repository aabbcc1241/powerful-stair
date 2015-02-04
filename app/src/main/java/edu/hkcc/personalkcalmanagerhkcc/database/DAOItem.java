package edu.hkcc.personalkcalmanagerhkcc.database;

import android.database.Cursor;

import java.util.List;

/**
 * Created by beenotung on 2/4/15.
 */
public interface DAOItem<ItemType> {

    public abstract String getTableName();

    public abstract String getTableColId();

    public abstract String[] getTableColumns();

    public abstract String getTableCreate();

    public abstract String getTableDrop();

    public abstract void insert(ItemType item);

    public abstract void insertFromXml();

    public abstract int getCount();

    public abstract List<ItemType> getAll();

    public abstract ItemType getItemRecord(Cursor cursor);

}
