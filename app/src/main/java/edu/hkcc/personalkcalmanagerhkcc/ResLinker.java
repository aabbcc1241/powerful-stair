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
		case 5:
			resid = R.layout.fragment_tips_on_ex;
			break;
		case 6:
			resid = R.layout.fragment_tips_on_nutrition;
			break;
		default:
			resid = R.layout.fragment_error_404;
		}
		return resid;
	}

	public static int getSectionNum(int fragmentLayoutId) {
		int sectionNum = 0;
		switch (fragmentLayoutId) {
		case R.layout.fragment_welcome:
			sectionNum = 0;
			break;
		case R.layout.fragment_about_you:
			sectionNum = 1;
			break;
		case R.layout.fragment_energy_cal:
			sectionNum = 4;
			break;
		case R.layout.fragment_tips_on_ex:
			sectionNum = 5;
			break;
		case R.layout.fragment_tips_on_nutrition:
			sectionNum = 6;
			break;
		default:
			sectionNum = R.layout.fragment_error_404;
		}
		return sectionNum;
	}

	public static void loadContent(MainActivity mainActivity, int sectionNum) {
		switch (sectionNum) {
		case 4:
			mainActivity.energyCalFragment.loadContent();
			break;
		case 5:
			mainActivity.tipsOnExFragment.loadContent();
			break;
		case 6 :
			mainActivity.tipsOnNutritionFragment.loadContent();
		default:
		}
	}
}
