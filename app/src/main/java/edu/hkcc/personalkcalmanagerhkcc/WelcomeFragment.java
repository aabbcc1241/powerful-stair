package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import edu.hkcc.myutils.MyFragment;
import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.database.userinfo.UserInfo;

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
                welcome_onclick(context);
            }
        };
    }

    private void welcome_onclick(Context context) {
        if (!mainActivity.myDAO.userInfoDAOItem.getUserInfo().isSufficient())
            mainActivity.switchSection(AboutYouFragment.drawerPosition);
        else {
            Utils.showToast(context, R.string.welcome_lets_go, Toast.LENGTH_LONG);
            mainActivity.mNavigationDrawerFragment.openDrawer();
        }
    }

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                mainActivity.welcome_button_start.setOnClickListener(
                        welcome_button_start_OnClickListener(mainActivity));
                UserInfo userInfo = mainActivity.myDAO.userInfoDAOItem.getUserInfo();
                if (userInfo.isSufficient()) {
                    Utils.showToast(mainActivity, mainActivity.getString(R.string.welcome_hi) + " " + userInfo.getName());
                } else {
                    Log.w("Warning", "user height and weight is empty");
                    try {
                        Thread.sleep(Utils.DEFAULT_TIMEOUT);
                    } catch (InterruptedException e) {
                    }
                    mainActivity.switchSection(AboutYouFragment.drawerPosition);
                }
            }
        };
    }
}
