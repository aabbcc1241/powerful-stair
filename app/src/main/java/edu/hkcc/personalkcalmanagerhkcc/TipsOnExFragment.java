package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.MyFragment;

public class TipsOnExFragment implements MyFragment {
    public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_tips_on_ex);
    public boolean isloadedUrl;
    private MainActivity mainActivity;
    private boolean shown = false;

    public TipsOnExFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        isloadedUrl = false;
    }

    @Override
    public void loadContent() {
        //if (shown & !isloadedUrl) {
        mainActivity.tipsOnEx_webView.getSettings().setBuiltInZoomControls(true);
        mainActivity.tipsOnEx_webView.getSettings().setDisplayZoomControls(true);
        mainActivity.tipsOnEx_webView.loadUrl(mainActivity.getString(R.string.url_tips_on_ex));
        //  isloadedUrl = true;
        //}
    }

    @Override
    public boolean isShown() {
        return shown;
    }
}
