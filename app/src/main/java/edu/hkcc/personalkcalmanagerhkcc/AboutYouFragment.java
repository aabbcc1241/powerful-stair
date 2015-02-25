package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.util.Log;
import android.view.View;

import edu.hkcc.myutils.Maths;
import edu.hkcc.myutils.MyFragment;
import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.database.userinfo.UserInfo;
import edu.hkcc.personalkcalmanagerhkcc.database.userinfo.UserInfoDAOItem;

public class AboutYouFragment implements MyFragment {
    public static int drawerPosition = ResLinker
            .getSectionNum(R.layout.fragment_about_you);
    private final UserInfoDAOItem userInfoDAOItem;
    public boolean shown = false;
    protected String name;
    protected float height, weight, bmi;
    protected float heightUnit, weightUnit;
    protected int age;
    private MainActivity mainActivity;

    public AboutYouFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        userInfoDAOItem = mainActivity.myDAO.userInfoDAOItem;
    }

    public View.OnClickListener aboutYou_button_load_onClickListener(Context context) {
        final Context myContext = context;
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (allFilled()) {
                    Utils.showToast(myContext, R.string.aboutYou_calculating);
                    calcBmi();
                } else {
                    Utils.showToast(myContext,
                            R.string.aboutYou_pleaseFillAllInfo);
                }
            }
        };
    }

    public View.OnClickListener aboutYou_button_update_onClickListener(Context context) {
        final Context myContext = context;
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (allFilled()) {
                    Utils.showToast(myContext, R.string.aboutYou_calculating);
                    calcBmi();
                } else {
                    Utils.showToast(myContext,
                            R.string.aboutYou_pleaseFillAllInfo);
                }
            }
        };
    }

    protected boolean allFilled() {
        boolean allFilled = true;
        String temp;
        temp = mainActivity.aboutYou_editText_userheight.getText().toString();
        if (allFilled &= temp.length() > 0)
            height = Float.parseFloat(temp);
        else
            height = 0f;
        temp = mainActivity.aboutYou_editText_userweight.getText().toString();
        if (allFilled &= temp.length() > 0)
            weight = Float.parseFloat(temp);
        else
            weight = 0f;
        return allFilled;
    }

    protected void calcBmi() {
        heightUnit = height < 100 ? 1 : 0.01f;
        mainActivity.aboutYou_userheight_label
                .setText(heightUnit == 1 ? R.string.aboutYou_userHeight_label_m
                        : R.string.aboutYou_userHeight_label_cm);
        weightUnit = weight < 120 ? 1 : 0.453592f;
        mainActivity.aboutYou_userweight_label
                .setText(weightUnit == 1 ? R.string.aboutYou_userWeight_label_kg
                        : R.string.aboutYou_userWeight_label_lbs);
        bmi = Maths.getBmi(height, weight);
        mainActivity.aboutYou_editText_userbmi.setText(String.valueOf(bmi));
    }

    @Override
    public void loadContent() {
        //TODO
            Log.w("debug", mainActivity.findViewById(R.id.aboutYou_editText_userheight).toString());
        UserInfo userInfo = userInfoDAOItem.getUserInfo();
    }

    @Override
    public boolean isShown() {
        return shown;
    }

    public boolean isPersonalInfoFilled() {
        //TODO if no weight, ask from BMI page
        return true;//false;
    }
}
