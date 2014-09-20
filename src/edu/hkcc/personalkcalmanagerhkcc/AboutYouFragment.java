package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.Utils;
import android.content.Context;
import android.view.View;

public class AboutYouFragment {
	private MainActivity mainActivity;

	protected String name;
	protected float height, weight, bmi;
	protected float heightUnit;
	protected int age;

	public AboutYouFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
	}

	public View.OnClickListener aboutYou_button_calcuateBmi(Context context) {
		final Context myContext = context;
		return new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (allFilled()) {
					Utils.showToast(myContext, R.string.aboutYou_calculating);
					calcBmi();
				} else {
					Utils.showToast(myContext,
							R.string.aboutYou_pleasefillallinfo);
				}
			}
		};
	}

	protected boolean allFilled() {
		// TODO Auto-generated method stub
		boolean allFilled = true;
		String temp;
		temp = mainActivity.aboutYou_edittext_userheight.getText().toString();
		if (allFilled &= (allFilled &= temp.length() > 0))
			height = Float.valueOf(temp);
		temp = mainActivity.aboutYou_edittext_userweight.getText().toString();
		if (allFilled &= (allFilled &= temp.length() > 0))
			weight = Float.valueOf(temp);
		return allFilled;
	}

	protected void calcBmi() {
		heightUnit = height < 100 ? 1 : 0.01f;
		mainActivity.aboutYou_userheight_label
				.setText(heightUnit == 1 ? R.string.aboutYou_userheight_label_m
						: R.string.aboutYou_userheight_label_cm);
		bmi = (float) (weight / Math.pow(height * heightUnit, 2));
		mainActivity.aboutYou_edittext_userbmi.setText(String.valueOf(bmi));
	}
}
