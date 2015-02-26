package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecord {
    public long id;
    public int stairPairId;
    public float calBurned;
    public int time;

    public StairRecord(int stairPairId, float durationInMinutes) {
        this.stairPairId = stairPairId;
        calBurned = Maths.calBurned(MainActivity.currentActivity.myDAO.stairPairDAOItem.getDistance(stairPairId), durationInMinutes);
    }

    public StairRecord(long id, int stairPairId, float calBurned, int time) {
        this.id = id;
        this.stairPairId = stairPairId;
        this.calBurned = calBurned;
        this.time = time;
    }

    public StairRecord() {
        super();
    }

    public String getWhereString() {
        return StairRecordDAOItem.TABLE_COL_ID + "=" + id;
    }
}
