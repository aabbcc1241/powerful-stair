package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import edu.hkcc.myutils.MyFragment;
import edu.hkcc.myutils.Utils;

public class WelcomeFragment implements MyFragment {
    public boolean shown = false;
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
    public void loadContent() {

    }

    @Override
    public boolean isShown() {
        return shown;
    }
}
