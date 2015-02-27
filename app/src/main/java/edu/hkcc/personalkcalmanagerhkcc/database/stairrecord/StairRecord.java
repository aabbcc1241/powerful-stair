package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPair;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecord {
    public long id;
    public long stairPairId;
    public float calBurned;
    public long time;

    public StairRecord(StairPair stairPair, float durationInMinutes) {
        this.stairPairId = stairPair.id;
        calBurned = Maths.calBurned(stairPair.height, durationInMinutes);
        time = System.currentTimeMillis();
    }

    public StairRecord(int stairPairId, float durationInMinutes) {
        this(MainActivity.currentActivity.myDAO.stairPairDAOItem.getStairPair(stairPairId), durationInMinutes);
    }

    public StairRecord(long id, int stairPairId, float calBurned, long time) {
        this.id = id;
        this.stairPairId = stairPairId;
        this.calBurned = calBurned;
        this.time = time;
    }

    @Deprecated
    public StairRecord() {
        super();
    }

    public String getWhereString() {
        return StairRecordDAOItem.TABLE_COL_ID + "=" + id;
    }
}
