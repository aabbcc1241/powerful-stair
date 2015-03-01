package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

import android.content.res.Resources;

/**
 * Created by beenotung on 2/27/15.
 */
public class StairPairNotFoundException extends Resources.NotFoundException {
    public static final String MESSAGE = "Stair Pair Not Found Exception";

    public String message;

    public StairPairNotFoundException(StairCode stairCode) {
        message = MESSAGE + " " + stairCode.toString();
    }

    public StairPairNotFoundException(String source, String destination) {
        message = MESSAGE + " source:" + source + ", destination" + destination;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
