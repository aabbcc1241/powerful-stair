package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.Utils;
import android.content.Context;
import android.view.View;

public class WelcomeActivity {
	public static View.OnClickListener welcome_button_start_OnClickListener(
			Context context) {
		final Context myContext = context;
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Utils.showToast(myContext, R.string.lets_go);
			}
		};
	}
}
