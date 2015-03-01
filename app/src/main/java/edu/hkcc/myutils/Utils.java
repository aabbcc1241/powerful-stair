package edu.hkcc.myutils;

import android.content.Context;
import android.widget.Toast;

import java.util.Random;

public class Utils {
    public static final int DEFAULT_TIMEOUT = 2000;
    public static Random random = new Random(System.currentTimeMillis());

    /*
     * showToast
     */
    public static void showToast(Context context, CharSequence text, int duration) {
        Toast.makeText(context, text, duration).show();
    }

    public static void showToast(Context context, CharSequence text) {
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, int resId, int duration) {
        Toast.makeText(context, resId, duration).show();
    }

    public static void showToast(Context context, int resId) {
        Toast.makeText(context, resId, Toast.LENGTH_SHORT).show();
    }
}
