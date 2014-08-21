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
		default:
			resid = R.layout.fragment_error_404;
		}
		return resid;
	}
}
