package edu.hkcc.personalkcalmanagerhkcc;

import android.content.Context;
import android.view.View;
import edu.hkcc.myutils.Utils;

public class EnergyCalFragment {
	private MainActivity mainActivity;

	private int calAccum;

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
}
