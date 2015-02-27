package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.util.Log;

import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairCode;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPair;

/**
 * Created by beenotung on 2/27/15.
 */
public class StairEvent {
    private final MainActivity mainActivity;
    private final int MAX_TRY_COUNT = 5;
    public boolean scanning = false;
    public StairCode firstStairCode = null;
    public long firstTime;
    public StairCode secondStairCode = null;
    public long secondTime;
    private int scanTryCount = 0;

    public StairEvent(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void onStairCodeReceive(StairCode stairCode) {
        scanning = false;
        scanTryCount++;
        Log.w("Main", "receive Stair Code: " + stairCode.code);
        Log.w("Main", "scanTryCount: " + scanTryCount);
        if (mainActivity.myDAO.stairPairDAOItem.isStairCodeExist(stairCode.code))
            if (firstStairCode == null) {
                firstStairCode = stairCode;
                firstTime=System.currentTimeMillis();
                Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_first_scan_success));
            } else {
                secondStairCode = stairCode;
                secondTime=System.currentTimeMillis();
                Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_second_scan_success));
                //TODO save record
                StairPair stairPair=mainActivity.myDAO.stairPairDAOItem.getAll()
                firstStairCode = null;
            }
        else {
            Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_scan_error));
            if (scanTryCount <= MAX_TRY_COUNT) mainActivity.scanQRCode();
        }
        scanTryCount = 0;
    }
}
