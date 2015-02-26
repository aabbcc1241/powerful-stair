package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

/**
 * Created by beenotung on 2/26/15.
 */
public class StairRecord {
    public static final float METs_STAIR_CLIMB = 3.5f;
    public static final float STD_STAIR_HEIGHT = 2.0f;
    public long id;
    public int stairPairId;
    public float calBurned;
    public int time;

    public StairRecord(int stairPairId, float durationInMinutes) {
        this.stairPairId = stairPairId;
        calBurned = calBurned(MainActivity.currentActivity.myDAO.stairPairDAOItem.getDistance(stairPairId), durationInMinutes);
    }

    public String getWhereString() {
        return StairRecordDAOItem.TABLE_COL_ID + "=" + id;
    }

    //METs x 3.5 x weight in kilograms ÷ 200 x duration in minutes
    public float calBurned(float height, float durationInMinutes) {
        return METs_STAIR_CLIMB * 3.5f * MainActivity.currentActivity.aboutYouFragment.weight * MainActivity.currentActivity.aboutYouFragment.weightUnit / 200f * durationInMinutes * height / STD_STAIR_HEIGHT;
    }
}
