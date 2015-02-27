package edu.hkcc.personalkcalmanagerhkcc.database.stairpair;

/**
 * Created by beenotung on 1/17/15.
 */
public class StairPair {

    public static final float DEFAULT_STAIR_HEIGHT = 2.2f;
    public static int lastID = 0;
    public long id;
    public String up_code, down_code;
    public float height;

    @Deprecated
    public StairPair() {
        super();
    }

    public StairPair(long id, String up_code, String down_code, float height) {
        this.id = id;
        this.up_code = up_code;
        this.down_code = down_code;
        this.height = height;
    }

    public StairPair(String up_code, String down_code, float height) {
        this.up_code = up_code;
        this.down_code = down_code;
        this.height = height;
        id = new String(up_code + down_code + height).hashCode();
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
