package edu.hkcc.personalkcalmanagerhkcc;

import android.webkit.WebView;

/**
 * Created by beenotung on 10/18/15.
 */
public class Tip1Fragment extends MyWebFragment {
    private final MainActivity mainActivity;
    private final String[] urls;

    public Tip1Fragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        urls=new String[]{mainActivity.getString(R.string.url_tip2)};
    }

    @Override
    WebView webView() {
        return mainActivity.tip1_webView;
    }

    @Override
    protected String[] urls() {
        return urls;
    }
}
