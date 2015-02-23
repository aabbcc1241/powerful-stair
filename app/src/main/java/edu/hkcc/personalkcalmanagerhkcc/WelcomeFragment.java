package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import edu.hkcc.myutils.Utils;

public class WelcomeFragment {
    private MainActivity mainActivity;

    public WelcomeFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public View.OnClickListener welcome_button_start_OnClickListener(
            final Context context) {
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Utils.showToast(context, R.string.lets_go, Toast.LENGTH_LONG);
                // method to open drawer
                /*String msg;
                if(mainActivity.firstStairCode!=null)
                    msg=mainActivity.firstStairCode.code;
                else
                    msg="oops";
                Utils.showToast(mainActivity,msg);*/
                //mainActivity.switchSection(EnergyCalFragment.drawerPosition);
                mainActivity.checkPersonalInfo();
            }
        };
    }
}
