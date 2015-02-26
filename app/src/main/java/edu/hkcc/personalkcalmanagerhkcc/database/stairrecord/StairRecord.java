package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecord {
    public long id;
    public int stairPairId;
    public int duration;
    public int time;
    public String getWhereString() {
        return StairRecordDAOItem.TABLE_COL_ID + "=" + id;
    }
}
