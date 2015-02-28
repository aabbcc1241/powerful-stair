package edu.hkcc.personalkcalmanagerhkcc.database;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPairDAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecordDAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.userinfo.UserInfoDAOItem;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordDAOItem;

/**
 * Created by beenotung on 2/3/15.
 */
public class MyDAO extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "powerful_stair.db";
    public static int VERSION = 1;

    public MainActivity mainActivity;
    public SQLiteDatabase database = getWritableDatabase();

    //MY DAO
    public StairPairDAOItem stairPairDAOItem = new StairPairDAOItem(this);
    public UserInfoDAOItem userInfoDAOItem = new UserInfoDAOItem(this);
    public StairRecordDAOItem stairRecordDAOItem = new StairRecordDAOItem(this);
    public WeekRecordDAOItem weekRecordDAOItem=new WeekRecordDAOItem(this);

    public MyDAO(MainActivity mainActivity, SQLiteDatabase.CursorFactory factory) {
        super(mainActivity, DATABASE_NAME, factory, VERSION);
        this.mainActivity = mainActivity;
        onCreate(getWritableDatabase());
    }

    public MyDAO(MainActivity mainActivity) {
        this(mainActivity, null);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createTables(db);
    }

    private void createTables(SQLiteDatabase db) {
        db.execSQL(StairPairDAOItem.static_.getTableCreate());
        db.execSQL(UserInfoDAOItem.static_.getTableCreate());
        db.execSQL(StairRecordDAOItem.static_.getTableCreate());
        db.execSQL(WeekRecordDAOItem.static_.getTableCreate());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        dropTables(db);
        onCreate(db);
        VERSION = newVersion;
    }

    public void dropTables(SQLiteDatabase db) {
        db.execSQL(StairPairDAOItem.static_.getTableDrop());
        //db.execSQL(userInfoDAOItem.getTableDrop());
        //db.execSQL(StairRecordDAOItem.static_.getTableDrop());
    }

    public synchronized void myInit() {
        insertDefaultData();
    }

    public void insertDefaultData() {
        stairPairDAOItem.insertDefaultData();
    }
}
