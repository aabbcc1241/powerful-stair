package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Vector;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;

/**
 * Created by beenotung on 2/3/15.
 */
public class StairMapDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "powerful_stair.db";
    public static  int VERSION = 1;

    public static final String TABLE_STAIR_MAP = "stair_map";
    public static final String TABLE_DROP_STAIR_MAP = "DROP TABLE IF EXISTS " + TABLE_STAIR_MAP;
    public static final String TABLE_STAIR_MAP_COL_ID = StairMapItem.ID_COL;
    public static final String TABLE_STAIR_MAP_COL_UP_CODE =  StairMapItem.UP_CODE_COL;
    public static final String TABLE_STAIR_MAP_COL_DOWN_CODE =  StairMapItem.DOWN_CODE_COL;
    public static final String TABLE_STAIR_MAP_COL_DISTANCE =  StairMapItem.DISTANCE_COL;
    public static final String[] TABLE_STAIR_MAP_COLUMNS = {TABLE_STAIR_MAP_COL_ID, TABLE_STAIR_MAP_COL_UP_CODE, TABLE_STAIR_MAP_COL_DOWN_CODE, TABLE_STAIR_MAP_COL_DISTANCE};

    public static final String TABLE_CREATE_STAIR_MAP =
            "CREATE TABLE IF NOT EXISTS " + TABLE_STAIR_MAP + " (" +
                    TABLE_STAIR_MAP_COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    TABLE_STAIR_MAP_COL_UP_CODE + " TEXT NOT NULL, " +
                    TABLE_STAIR_MAP_COL_DOWN_CODE + " TEXT NOT NULL, " +
                    TABLE_STAIR_MAP_COL_DISTANCE + " REAL NOT NULL)";
    public MainActivity mainActivity;

    public StairMapDAO(MainActivity mainActivity, SQLiteDatabase.CursorFactory factory) {
        super(mainActivity, DATABASE_NAME, factory, VERSION);
        this.mainActivity = mainActivity;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE_STAIR_MAP);
        insertFromXml();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(TABLE_DROP_STAIR_MAP);
        onCreate(db);
        VERSION=newVersion;
    }

    public void insertFromXml() {
        String[] rawStrings = mainActivity.getResources().getStringArray(R.array.stair_map_pair_string_array);
        Vector<StairMapItem> stairMapItems = new Vector<>();
        int j; //buffer
        String upCode = "", downCode = "";
        double distance;
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
                    distance = Double.parseDouble(rawStrings[i]);
                    stairMapItems.add(new StairMapItem(upCode, downCode, distance));
                    break;
            }
        }
        StairMapItemDAO dao = new StairMapItemDAO(mainActivity);
        for (StairMapItem item : stairMapItems)
            dao.insert(item);
    }

    public void insert(StairMapItem item) {
        ContentValues contentValues = new ContentValues();

        //contentValues.put(ID_COL,item.id);
        contentValues.put(TABLE_STAIR_MAP_COL_UP_CODE, item.up_code);
        contentValues.put(TABLE_STAIR_MAP_COL_DOWN_CODE, item.down_code);
        contentValues.put(TABLE_STAIR_MAP_COL_DISTANCE, item.distance);

        //long id = database.insert(TABLE_NAME, null, contentValues);

        long id = getWritableDatabase().insertWithOnConflict(TABLE_STAIR_MAP, null, contentValues, SQLiteDatabase.CONFLICT_REPLACE);
        item.id = id;
    }

}
