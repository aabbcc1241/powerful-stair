package edu.hkcc.myutils;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordNotFoundException;

/**
 * Created by beenotung on 2/23/15.
 */
public class Maths {
    public static final float METs_STAIR_CLIMB = 3.5f;
    public static final float STD_STAIR_HEIGHT = 2.0f;
    public static final float BLS_TO_KG = 0.453592f;

    public static float getBmi(float height, float weight) {
        float heightUnit = height < 100 ? 1 : 0.01f;
        float weightUnit = weight < 120 ? 1 : BLS_TO_KG;
        return (float) ((weight * weightUnit) / Math.pow(height * heightUnit, 2));
    }

    //METs x 3.5 x weight in kilograms ÷ 200 x duration in minutes
    public static float calBurned(float height, float durationInMinutes) {
        return METs_STAIR_CLIMB * 3.5f * MainActivity.currentActivity.aboutYouFragment.weight * MainActivity.currentActivity.aboutYouFragment.weightUnit / 200f * durationInMinutes * height / STD_STAIR_HEIGHT;
    }

    public static float millisecondsToMinutes(long milliseconds) {
        return milliseconds / 1000f / 60f;
    }

    public static long millisecondToWeekId(long millisecond) {
        try {
            return (millisecond - MainActivity.currentActivity.myDAO.weekRecordDAOItem.getFirstMillisecond()) / (7 * 24 * 60 * 60 * 1000) + 1;
        } catch (WeekRecordNotFoundException e) {
            return 1;
        }
    }

    public static String getWeekString(long weekId) {
        return "Week " + weekId % 100;
    }
}
