package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import java.util.List;

import edu.hkcc.myutils.Maths;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecord;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecord {
    public long weekId;
    public float weekTarget;

    public WeekRecord() {
        super();
    }

    public WeekRecord(long weekId, float weekTarget) {
        this.weekId = weekId;
        this.weekTarget = weekTarget;
    }

    public float getCalSum() {
        List<StairRecord> records = MainActivity.currentActivity.myDAO.stairRecordDAOItem.getAllInSameWeek(weekId);
        float result = 0f;
        for (StairRecord record : records)
            result += record.calBurned;
        return result;
    }

    public float getProgress() {
        return getCalSum() / weekTarget;
    }

    public String getWeekString() {
        return Maths.getWeekString(weekId);
    }
}
