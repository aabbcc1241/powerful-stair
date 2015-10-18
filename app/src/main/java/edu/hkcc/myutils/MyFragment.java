package edu.hkcc.myutils;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

public interface MyFragment {
    public Runnable getLoadContentRunnable(final MainActivity activity);
}
