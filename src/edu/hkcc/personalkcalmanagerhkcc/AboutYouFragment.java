package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.Utils;
import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AboutYouFragment {
	private MainActivity mainActivity;

	public AboutYouFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public View.OnClickListener aboutYou_button_calcuateBmi(Context context) {
		final Context myContext = context;
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Utils.showToast(myContext, R.string.aboutyou_calculating);
				calcBmi();
			}
		};
	}

	protected void calcBmi() {
		double height=Double.valueOf(mainActivity.aboutyou_edittext_userheight.getText().toString());
		mainActivity.aboutyou_edittext_userbmi.setText(String.valueOf(height));
	}
}
