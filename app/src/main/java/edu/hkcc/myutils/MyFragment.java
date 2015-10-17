package edu.hkcc.myutils;

import android.app.Activity;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

public interface MyFragment {
    public Runnable getLoadContentRunnable(final MainActivity activity);
}
