package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.MyFragment;

public class TipsOnNutritionFragment implements MyFragment {
    public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_tips_on_nutrition);
    public boolean isloadedUrl;
    private MainActivity mainActivity;
    private boolean shown = false;

    public TipsOnNutritionFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        isloadedUrl = false;
    }

    @Override
    public void loadContent() {
        //if (shown & !isloadedUrl) {
        mainActivity.tipsOnNutrition_webView.getSettings().setBuiltInZoomControls(true);
        mainActivity.tipsOnNutrition_webView.getSettings().setDisplayZoomControls(true);
        mainActivity.tipsOnNutrition_webView.loadUrl(mainActivity.getString(R.string.url_tips_on_nutrition));
        //isloadedUrl = true;
        //}
    }

    @Override
    public boolean isShown() {
        return shown;
    }
}
