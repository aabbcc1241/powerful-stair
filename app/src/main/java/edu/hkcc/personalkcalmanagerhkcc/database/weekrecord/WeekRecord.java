package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.util.Log;
import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecord;

import java.util.List;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecord {
    public long weekId;
    public long createTime;
    public float weekTarget;

    public WeekRecord() {
        super();
    }

    public WeekRecord(long weekId, long createTime, float weekTarget) {
        this.weekId = weekId;
        this.createTime = createTime;
        this.weekTarget = weekTarget;
    }

    public WeekRecord(long weekId, float weekTarget) {
        this.weekId = weekId;
        this.createTime = System.currentTimeMillis();
        this.weekTarget = weekTarget;
    }

    public float getCalSum() {
        List<StairRecord> records = MainActivity.currentActivity.myDAO.stairRecordDAOItem.getAllInSameWeek(weekId);
        float result = 0f;
        for (StairRecord record : records) {
            result += record.calBurned;
            Log.w("cal", record.calBurned + "");
        }
        Log.w("cal sum of " + getWeekString() + "(" + records.size() + ")", result + "");
        return result;
    }

    public float getProgress() {
        return getCalSum() / weekTarget;
    }

    public String getWeekString() {
        return Maths.getWeekString(weekId);
    }
}
