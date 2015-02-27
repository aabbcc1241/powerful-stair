package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

import java.util.Vector;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairPair {

    public static final float DEFAULT_STAIR_HEIGHT = 2.2f;
    public String up_code, down_code;
    public float height;

    @Deprecated
    public StairPair() {
        super();
    }

    public StairPair(String up_code, String down_code, float height) {
        this.up_code = up_code;
        this.down_code = down_code;
        this.height = height;
    }

    public boolean isPair(String code1, String code2) {
        return
                ((code1 == up_code) || (code1 == down_code)) &&
                        ((code2 == up_code) || (code2 == down_code));
    }

    @Override
    public String toString() {
        Vector result=new Vector();
        result.add(up_code);
        result.add(down_code);
        result.add(height);
        return result.toString();
    }
}
