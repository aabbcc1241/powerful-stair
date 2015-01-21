package edu.hkcc.personalkcalmanagerhkcc.database.stair;

import android.util.Log;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairMapItem {
    public static final String ID_COL = "id";
    public static final String UP_CODE_COL = "up_code";
    public static final String DOWN_CODE_COL = "down_code";
    public static final String DISTANCE_COL = "distance";
    public static int lastID = 0;
    public long id;
    public String up_code, down_code;
    public double distance;

    protected StairMapItem() {
        super();
        Log.w("StairMapItem", "new object");
    }

    public StairMapItem(long id, String up_code, String down_code, double distance) {
        this();
        this.id = id;
        this.up_code = up_code;
        this.down_code = down_code;
        this.distance = distance;
    }

    public StairMapItem(String up_code, String down_code, double distance) {
        Log.w("StairMapItem", "new object");
        //this();
        this.up_code = up_code;
        this.down_code = down_code;
        this.distance = distance;
        Log.w("StairMapItem", "get id");
        id = new String(up_code + down_code + distance).hashCode();
        Log.w("StairMapItem", String.valueOf(id));
    }


    public static int getNewId() {
        return ++lastID;
    }

    public boolean hasPair(String code1, String code2) {
        return
                ((code1 == up_code) || (code1 == down_code)) &&
                        ((code2 == up_code) || (code2 == down_code));
    }

    public String getWhereString() {
        return ID_COL + "=" + id;
    }
}
