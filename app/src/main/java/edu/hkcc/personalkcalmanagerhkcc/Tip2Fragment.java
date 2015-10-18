package edu.hkcc.personalkcalmanagerhkcc;

import android.webkit.WebView;

/**
 * Created by beenotung on 10/18/15.
 */
public class Tip2Fragment extends MyWebFragment {
    private final MainActivity mainActivity;
    private final String[] urls;

    public Tip2Fragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        urls=new String[]{mainActivity.getString(R.string.url_tip1)};
    }

    @Override
    WebView webView() {
        return mainActivity.tip2_webView;
    }

    @Override
    protected String[] urls() {
        return urls;
    }
}
