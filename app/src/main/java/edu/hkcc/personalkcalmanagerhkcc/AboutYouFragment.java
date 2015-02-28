package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.view.View;

import edu.hkcc.myutils.Maths;
import edu.hkcc.myutils.MyFragment;
import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.database.userinfo.UserInfo;

public class AboutYouFragment implements MyFragment {
    public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_about_you);
    public String name;
    public float height, weight, bmi;
    public float heightUnit, weightUnit;
    public int age;
    private MainActivity mainActivity;

    public AboutYouFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    private void load_onclick(Context myContext) {
        mainActivity.runOnUiThread(getLoadContentRunnable());
    }

    public View.OnClickListener update_onClickListener(Context context) {
        final Context myContext = context;
        return new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                update_onclick(myContext);
            }
        };
    }

    public void update_onclick(Context myContext) {
        if (readFromLayout()) {
            calcBmi();
            saveToDb();
            Utils.showToast(myContext, R.string.aboutYou_calculating);
        } else {
            Utils.showToast(myContext,
                    R.string.aboutYou_pleaseFillAllInfo);
        }
    }

    private boolean readFromLayout() {
        String temp;
        temp = mainActivity.aboutYou_editText_userheight.getText().toString();
        if (temp.length() <= 0) return false;
        height = Float.parseFloat(temp);
        temp = mainActivity.aboutYou_editText_userweight.getText().toString();
        if (temp.length() <= 0) return false;
        weight = Float.parseFloat(temp);
        temp = mainActivity.aboutYou_editText_username.getText().toString();
        name = temp.length() > 0 ? temp : "";
        temp = mainActivity.aboutYou_editText_userage.getText().toString();
        age = temp.length() > 0 ? Integer.parseInt(temp) : 0;
        return true;
    }

    private void saveToDb() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(name);
        userInfo.setAge(age);
        userInfo.setHeight(height * heightUnit);
        userInfo.setWeight(weight * weightUnit);
        userInfo.updateBmi();
        mainActivity.myDAO.userInfoDAOItem.insert(userInfo);
    }

    private void calcBmi() {
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
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                mainActivity.aboutYou_button_update.setOnClickListener(update_onClickListener(mainActivity));
                UserInfo userInfo = mainActivity.myDAO.userInfoDAOItem.getUserInfo();
                mainActivity.aboutYou_editText_username.setText(userInfo.getName());
                mainActivity.aboutYou_editText_userage.setText(userInfo.getAge() > 0 ? String.valueOf(userInfo.getAge()) : "");
                mainActivity.aboutYou_editText_userheight.setText(userInfo.getHeight() > 0 ? String.valueOf(userInfo.getHeight()) : "");
                mainActivity.aboutYou_editText_userweight.setText(userInfo.getWeight() > 0 ? String.valueOf(userInfo.getWeight()) : "");
                mainActivity.aboutYou_editText_userbmi.setText("");
                update_onclick(mainActivity);
            }
        };
    }
}
