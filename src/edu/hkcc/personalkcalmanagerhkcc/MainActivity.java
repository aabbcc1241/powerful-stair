package edu.hkcc.personalkcalmanagerhkcc;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	// my vars
	public static boolean inited = false;
	public Resources res;
	public String[] navigation_drawer_titles;
	public PlaceholderFragment[] placeholderFragments;
	public WelcomeFragment welcomeFragment;

	private void initvar() {
		if (inited)
			return;
		res = getResources();
		navigation_drawer_titles = res.getStringArray(R.array.navigation_drawer_titles);
		placeholderFragments = new PlaceholderFragment[navigation_drawer_titles.length];
		for (int i = 0; i < placeholderFragments.length; i++)
			placeholderFragments[i] = PlaceholderFragment.newInstance(i);
		welcomeFragment = new WelcomeFragment(this);
		inited = true;
	}

	// layout elements
	public Button welcome_button_start;

	private void initListener() {
		initListener_welcome();

		myInit();
	}

	private void initListener_welcome() {
		if ((welcome_button_start = (Button) findViewById(R.id.welcome_button_start)) != null) {
			welcome_button_start.setOnClickListener(welcomeFragment
					.welcome_button_start_OnClickListener(MainActivity.this));
		}
	}

	private void myInit() {
	}

	@Override
	public View onCreateView(String name, Context context, AttributeSet attrs) {
		initListener();
		return super.onCreateView(name, context, attrs);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		initvar();
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(
				R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		initvar();
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager.beginTransaction().replace(R.id.container,
		// PlaceholderFragment.newInstance(position)).commit();
		fragmentManager.beginTransaction().replace(R.id.container, placeholderFragments[position]).commit();
	}

	public void onSectionAttached(int number) {

		/*
		 * switch (number) { case 1: mTitle = title_section_1; break; case 2:
		 * mTitle = title_section_2; break; case 3: mTitle = title_section_3;
		 * break; }
		 */

		mTitle = navigation_drawer_titles[number];
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
