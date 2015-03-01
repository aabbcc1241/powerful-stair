package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPair;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecord {
    public long id;
    public String up_code;
    public String down_code;
    public float calBurned;
    public long millisecond;
    public long weekId;

    public StairRecord(StairPair stairPair, float durationInMinutes) {
        this.up_code = stairPair.up_code;
        this.down_code = stairPair.down_code;
        calBurned = Maths.calBurned(stairPair.height, durationInMinutes);
        millisecond = System.currentTimeMillis();
        weekId = Maths.millisecondToWeekId(millisecond);
    }


    @Deprecated
    public StairRecord() {
        super();
    }


    public String getWhereString() {
        return StairRecordDAOItem.TABLE_COL_ID + "=" + id;
    }
}
