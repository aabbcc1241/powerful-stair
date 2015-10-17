package edu.hkcc.personalkcalmanagerhkcc;

import android.os.AsyncTask;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import edu.hkcc.myutils.MyFragment;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class AboutFragment implements MyFragment {
    public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_tips_on_ex);
    public boolean isloadedUrl;
    private MainActivity mainActivity;


    public AboutFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        isloadedUrl = false;
        urls = mainActivity.getResources().getStringArray(R.array.url_about);
    }

    String[] urls;
    int url_index = 0;

    private String getNextUrl() {
        if (url_index + 1 < urls.length)
            url_index++;
        return urls[url_index];
    }

    void checkHttpContent(final String url) {
        new Thread() {
            @Override
            public void run() {
                boolean ok = false;
                try {
                    String html = getHtml(url);
                    ok = !html.toUpperCase().contains("Not Found".toUpperCase());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (!ok)
                    mainActivity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mainActivity.about_webView.loadUrl(getNextUrl());
                        }
                    });
            }
        }.start();

    }

    private String getFirstUrl() {
        url_index = 0;
        return urls[0];
    }

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                //if (shown & !isloadedUrl) {
                mainActivity.about_webView.getSettings().setBuiltInZoomControls(true);
                mainActivity.about_webView.getSettings().setDisplayZoomControls(true);
                WebViewClient webViewClient = new WebViewClient() {
                    @Override
                    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                        if (errorCode == 404)
                            mainActivity.about_webView.loadUrl(getNextUrl());
                        else
                            super.onReceivedError(view, errorCode, description, failingUrl);
                    }

                    @Override
                    public void onPageFinished(WebView view, String url) {
                        String title = mainActivity.about_webView.getTitle();
                        if (title.contains("404"))
                            mainActivity.about_webView.loadUrl(getNextUrl());
                        else
                            checkHttpContent(url);
                        super.onPageFinished(view, url);
                    }
                };
                mainActivity.about_webView.setWebViewClient(webViewClient);
                mainActivity.about_webView.loadUrl(getFirstUrl());
                //  isloadedUrl = true;
                //}
            }

        };
    }

    String getHtml(String url) throws IOException {
        HttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url);
        HttpResponse response = client.execute(request);
        InputStream in = response.getEntity().getContent();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        StringBuilder stringBuilder = new StringBuilder();
        String line = null;
        while ((line = reader.readLine()) != null)
            stringBuilder.append(line);
        in.close();
        return stringBuilder.toString();
    }
}
