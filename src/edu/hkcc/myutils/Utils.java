package edu.hkcc.myutils;

import android.content.Context;
import android.widget.Toast;

public class Utils {

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
