package edu.hkcc.personalkcalmanagerhkcc;

public class ResLinker {
	/*
	 * get fragment layout id (xml-file) from navgation drawers section number
	 */
	public static int getFragmentLayoutId(int sectionNum) {
		int resid = 0;
		switch (sectionNum) {
		case 0:
			resid = R.layout.fragment_welcome;
			break;
		case 1:
			resid = R.layout.fragment_about_you;
			break;
		case 4:
			resid = R.layout.fragment_energy_cal;
			break;
		default:
			resid = R.layout.fragment_error_404;
		}
		return resid;
	}

	public static void loadContent(MainActivity mainActivity, int sectionNum) {
		switch (sectionNum) {
		case 4:
			mainActivity.energyCalFragment.loadContent();
			break;
		default:
		}
	}
}
