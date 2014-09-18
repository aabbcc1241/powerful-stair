package edu.hkcc.personalkcalmanagerhkcc;

import java.util.Calendar;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.TableRow;
import android.widget.TextView;
import edu.hkcc.myutils.Utils;

public class EnergyCalFragment {
	private MainActivity mainActivity;

	private int calAccum;

	private static int drawerPosition=4;

	public EnergyCalFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		calAccum = 0;
	}

	public void accumCal(int newCal) {
		calAccum += newCal;
	}

	public int getCalAccum() {
		return calAccum;
	}
	public String getCalAccumAsString() {
		return String.valueOf(calAccum);
	}
	public void addRecord() {
		mainActivity.onNavigationDrawerItemSelected(drawerPosition);
				int id = 99999999;
		TableRow tableRow = new TableRow(mainActivity);
		tableRow.setLayoutParams(new TableRow.LayoutParams(
				TableRow.LayoutParams.MATCH_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL));
		tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
		tableRow.setId(id);
		TextView textView1 = new TextView(mainActivity);
		TextView textView2 = new TextView(mainActivity);
		TextView textView3 = new TextView(mainActivity);
		textView1
				.setText(mainActivity.getString(R.string.energycal_energy_type_hkcc_stair_walk));
		Calendar calendar = Calendar.getInstance();
		int date = calendar.get(Calendar.DATE);
		int month = calendar.get(Calendar.MONTH);
		textView2.setText(date + "/" + month);
		int newCal = Integer
				.valueOf(mainActivity.getString(R.string.energycal_energy_per_walk_stair));
		accumCal(newCal);
		textView3.setText(getCalAccumAsString());
		tableRow.addView(textView1);
		tableRow.addView(textView2);
		tableRow.addView(textView3);
		mainActivity.energycal_tablelayout_energy.addView(tableRow);
	}
}
