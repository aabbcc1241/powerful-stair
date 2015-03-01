package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.content.res.Resources;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecordNotFoundException extends Resources.NotFoundException {
    public static final String MESSAGE = "Week Record Not Found Exception";

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
