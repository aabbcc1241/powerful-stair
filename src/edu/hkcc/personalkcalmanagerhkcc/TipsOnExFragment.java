package edu.hkcc.personalkcalmanagerhkcc;

import edu.hkcc.myutils.MyFragment;

public class TipsOnExFragment implements MyFragment {
	private MainActivity mainActivity;
	public static int drawerPosition = ResLinker.getSectionNum(R.layout.fragment_tips_on_ex);

	public boolean isloadedUrl;

	public TipsOnExFragment(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		isloadedUrl = false;
	}

	@Override
	public void loadContent() {
		// if (!isloadedUrl) {
		mainActivity.tipsOnEx_webView.getSettings().setBuiltInZoomControls(true);
		mainActivity.tipsOnEx_webView.getSettings().setDisplayZoomControls(true);
		mainActivity.tipsOnEx_webView.loadUrl(mainActivity.getString(R.string.url_tips_on_ex));
		isloadedUrl = true;
		// }
	}
}
