package edu.hkcc.personalkcalmanagerhkcc;

import android.webkit.WebView;
import edu.hkcc.myutils.MyFragment;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static edu.hkcc.myutils.Utils.getHtml;

/**
 * Created by beenotung on 10/17/15.
 */
public abstract class MyWebFragment implements MyFragment {


    int url_index = 0;

    abstract WebView webView();

    void setHtml(String html) {
        webView().loadData(html, "text/html", "utf-8");
    }

    protected abstract String[] urls();

    boolean hasNextUrl() {
        return url_index + 1 < urls().length;
    }

    private String getNextUrl() {
        if (url_index + 1 < urls().length)
            url_index++;
        return urls()[url_index];
    }

    private String getFirstUrl() {
        url_index = 0;
        return urls()[0];
    }

    @Override
    public Runnable getLoadContentRunnable(final MainActivity activity) {
        return new Runnable() {
            @Override
            public void run() {
                //if (shown & !isloadedUrl) {
                webView().getSettings().setBuiltInZoomControls(true);
                webView().getSettings().setDisplayZoomControls(true);
                setHtml("<center>" + activity.getString(R.string.loading) + "</center>");
                final AtomicReference<String> html = new AtomicReference<>(null);
                new Thread() {
                    @Override
                    public void run() {
                        url_index = -1;
                        do {
                            try {
                                html.set(getHtml(getNextUrl()));
                                if ((html.get().contains("404") || html.get().toLowerCase().contains("not found")) && hasNextUrl())
                                    html.set(null);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } while (html.get() == null);
                        activity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                setHtml(html.get());
                            }
                        });
                    }
                }.start();
                //  isloadedUrl = true;
                //}
            }

        };
    }
}
