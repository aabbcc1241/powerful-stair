package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.database.sqlite.SQLiteException;
import android.util.Log;
import edu.hkcc.myutils.Maths;
import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairCode;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPair;
import edu.hkcc.personalkcalmanagerhkcc.database.stairpair.StairPairNotFoundException;

import java.util.List;

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
    public boolean saved = true;
    private int scanTryCount = 0;

    public StairEvent(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public synchronized void onStairCodeReceive(StairCode stairCode) {
        scanning = false;
        scanTryCount++;
        Log.w("Main", "receive Stair Code: " + stairCode.code);
        Log.w("Main", "scanTryCount: " + scanTryCount);
        try {
            if (!mainActivity.myDAO.stairPairDAOItem.isStairCodeExist(stairCode.code))
                throw new StairPairNotFoundException(stairCode);

            firstStairCode = secondStairCode;
            firstTime = secondTime;
            secondStairCode = stairCode;
            secondTime = System.currentTimeMillis();

            //check if double scan (same code)
            if (saved || firstStairCode.code.equals(secondStairCode.code)) {
                Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_first_scan_success));
                saved = false;
                return;
            }

            // save record
            StairPair stairPair = mainActivity.myDAO.stairPairDAOItem.getStairPair(firstStairCode.code, secondStairCode.code);
            StairRecord record = new StairRecord(stairPair, Maths.millisecondsToMinutes(secondTime - firstTime));
            mainActivity.myDAO.stairRecordDAOItem.insert(record);
            if (record.id < 0)
                throw new SQLiteException();
            Log.w("Debug", "new record id=" + record.id);
            Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_second_scan_success));
            saved = true;
        } catch (StairPairNotFoundException e) {
            saved = false;
            Log.w("Exception", e.getMessage());
            List<StairPair> stairPairs = mainActivity.myDAO.stairPairDAOItem.getAll();
            Log.w("Debug", "number of stair pair in db=" + stairPairs.size());
            for (StairPair stairPair : stairPairs) {
                Log.w("Debug", stairPair.toString());
            }
            Utils.showToast(mainActivity, mainActivity.getString(R.string.prompt_scan_error));
            if (scanTryCount <= MAX_TRY_COUNT) mainActivity.scanQRCode();
        } catch (SQLiteException e) {
            Log.w("Exception", "SQLiteException" + e.getMessage());
        }
        scanTryCount = 0;
    }
}
