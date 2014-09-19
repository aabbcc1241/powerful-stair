package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.Utils;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

public class WelcomeFragment {
	private MainActivity mainActivity;

	public WelcomeFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public View.OnClickListener welcome_button_start_OnClickListener(
			final Context context) {
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Utils.showToast(context, R.string.lets_go, Toast.LENGTH_LONG);
				// method to open drawer
				mainActivity.switchSection(EnergyCalFragment.drawerPosition);
			}
		};
	}
}
