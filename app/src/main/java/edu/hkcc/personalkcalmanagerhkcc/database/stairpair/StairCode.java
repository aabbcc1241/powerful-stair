package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

/**
 * Created by beenotung on 1/10/15.
 */
public class StairCode {
    public String code;
    public String format;

    public StairCode(String code) {
        this.code = code;
    }

    public StairCode(String code, String format) {
        this.code = code;
        this.format = format;
    }
}
