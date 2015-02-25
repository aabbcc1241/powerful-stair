package edu.hkcc.personalkcalmanagerhkcc;

import android.util.Log;

public class ResLinker {
    /*
     * get fragment layout id (xml-file) from navgation drawers section number
     */
    public static int getFragmentLayoutId(int sectionNum) {
        int resId = 0;
        switch (sectionNum) {
            case 0:
                resId = R.layout.fragment_welcome;
                break;
            case 1:
                resId = R.layout.fragment_about_you;
                break;
            case 4:
                resId = R.layout.fragment_energy_cal;
                break;
            case 5:
                resId = R.layout.fragment_tips_on_ex;
                break;
            case 6:
                resId = R.layout.fragment_tips_on_nutrition;
                break;
            default:
                resId = R.layout.fragment_error_404;
        }
        return resId;
    }

    public static int getSectionNum(int fragmentLayoutId) {
        int sectionNum = 0;
        switch (fragmentLayoutId) {
            case R.layout.fragment_welcome:
                sectionNum = 0;
                break;
            case R.layout.fragment_about_you:
                sectionNum = 1;
                break;
            case R.layout.fragment_energy_cal:
                sectionNum = 4;
                break;
            case R.layout.fragment_tips_on_ex:
                sectionNum = 5;
                break;
            case R.layout.fragment_tips_on_nutrition:
                sectionNum = 6;
                break;
            default:
                sectionNum = R.layout.fragment_error_404;
        }
        return sectionNum;
    }

    public static void loadContent(MainActivity mainActivity, int sectionNum) {
        mainActivity.initSections();
        switch (getFragmentLayoutId(sectionNum)) {
            case R.layout.fragment_about_you:
                mainActivity.aboutYouFragment.loadContent();
                break;
            case R.layout.fragment_energy_cal:
                mainActivity.energyCalFragment.loadContent();
                break;
            case R.layout.fragment_tips_on_ex:
                mainActivity.tipsOnExFragment.loadContent();
                break;
            case R.layout.fragment_tips_on_nutrition:
                mainActivity.tipsOnNutritionFragment.loadContent();
            default:
        }
    }
}
