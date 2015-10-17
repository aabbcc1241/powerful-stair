package edu.hkcc.personalkcalmanagerhkcc;

import android.webkit.WebView;

public class AboutFragment extends MyWebFragment {
    public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_about);
    public boolean isloadedUrl;
    private MainActivity mainActivity;
    private String[] urls;

    public AboutFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        isloadedUrl = false;
        urls = mainActivity.getResources().getStringArray(R.array.url_about);
    }

    @Override
    protected String[] urls() {
        return urls;
    }

    WebView webView() {
        return mainActivity.about_webView;
    }


}
