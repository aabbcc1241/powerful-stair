package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairPair {

    public static final float DEFAULT_STAIR_HEIGHT = 2.2f;
    public static int lastID = 0;
    public long id;
    public String up_code, down_code;
    public float distance;

    public StairPair() {
        super();
    }

    public StairPair(long id, String up_code, String down_code, float distance) {
        this.id = id;
        this.up_code = up_code;
        this.down_code = down_code;
        this.distance = distance;
    }

    public StairPair(String up_code, String down_code, float distance) {
        this.up_code = up_code;
        this.down_code = down_code;
        this.distance = distance;
        id = new String(up_code + down_code + distance).hashCode();
    }

    public boolean isPair(String code1, String code2) {
        return
                ((code1 == up_code) || (code1 == down_code)) &&
                        ((code2 == up_code) || (code2 == down_code));
    }

    public String getWhereString() {
        return StairPairDAOItem.TABLE_COL_ID + "=" + id;
    }
}
