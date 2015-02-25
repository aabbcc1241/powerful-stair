package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.view.View;

import edu.hkcc.myutils.MyFragment;

public class WelcomeFragment implements MyFragment {
    private MainActivity mainActivity;

    public WelcomeFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public View.OnClickListener welcome_button_start_OnClickListener(
            final Context context) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mainActivity.start(context);
            }
        };
    }

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                mainActivity.welcome_button_start.setOnClickListener(
                        welcome_button_start_OnClickListener(mainActivity));
            }
        };
    }
}
