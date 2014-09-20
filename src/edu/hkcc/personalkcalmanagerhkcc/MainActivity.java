package edu.hkcc.personalkcalmanagerhkcc;

import java.util.Calendar;

import edu.hkcc.myutils.Utils;

import android.app.Activity;
import android.app.ActionBar;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.support.v4.widget.DrawerLayout;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {

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

	/* nfc */

	/* my vars */
	public static boolean inited = false;
	public Resources res;
	public String[] navigation_drawer_titles;
	public PlaceholderFragment[] placeholderFragments;

	/* layout fragments */
	public WelcomeFragment welcomeFragment;
	public AboutYouFragment aboutYouFragment;
	public EnergyCalFragment energyCalFragment;

	/* layout elements */
	// welcome
	public Button welcome_button_start;
	// about you
	public Button aboutYou_button_calcuateBmi;
	public TextView aboutYou_userheight_label;
	public EditText aboutYou_edittext_username;
	public EditText aboutYou_edittext_userage;
	public EditText aboutYou_edittext_userheight;
	public EditText aboutYou_edittext_userweight;
	public EditText aboutYou_edittext_userbmi;
	// energy cal
	public TableLayout energycal_tablelayout_energy;

	private void initvar() {
		if (inited)
			return;
		res = getResources();
		navigation_drawer_titles = res
				.getStringArray(R.array.navigation_drawer_titles);
		placeholderFragments = new PlaceholderFragment[navigation_drawer_titles.length];
		for (int i = 0; i < placeholderFragments.length; i++)
			placeholderFragments[i] = PlaceholderFragment.newInstance(i);
		welcomeFragment = new WelcomeFragment(this);
		aboutYouFragment = new AboutYouFragment(this);
		energyCalFragment = new EnergyCalFragment(this);
		inited = true;
	}

	public void initDrawer() {
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
	}

	private void initListener() {
		initListener_welcome();
		initListener_aboutYou();
		initListener_energyCal();

		// myInit();
	}

	private void initListener_welcome() {
		if ((welcome_button_start = (Button) findViewById(R.id.welcome_button_start)) != null) {
			welcome_button_start.setOnClickListener(welcomeFragment
					.welcome_button_start_OnClickListener(MainActivity.this));
		}
	}

	private void initListener_aboutYou() {
		if ((aboutYou_button_calcuateBmi = (Button) findViewById(R.id.aboutYou_button_calcuateBmi)) != null) {
			aboutYou_button_calcuateBmi.setOnClickListener(aboutYouFragment
					.aboutYou_button_calcuateBmi(MainActivity.this));
		}
		if ((EditText) findViewById(R.id.aboutYou_edittext_username) != null)
			aboutYou_edittext_username = (EditText) findViewById(R.id.aboutYou_edittext_username);
		if ((EditText) findViewById(R.id.aboutYou_edittext_userage) != null)
			aboutYou_edittext_userage = (EditText) findViewById(R.id.aboutYou_edittext_userage);
		if ((EditText) findViewById(R.id.aboutYou_edittext_userheight) != null)
			aboutYou_edittext_userheight = (EditText) findViewById(R.id.aboutYou_edittext_userheight);
		if ((EditText) findViewById(R.id.aboutYou_edittext_userweight) != null)
			aboutYou_edittext_userweight = (EditText) findViewById(R.id.aboutYou_edittext_userweight);
		if ((EditText) findViewById(R.id.aboutYou_edittext_userbmi) != null)
			aboutYou_edittext_userbmi = (EditText) findViewById(R.id.aboutYou_edittext_userbmi);
		if (findViewById(R.id.aboutYou_userheight_label) != null)
			aboutYou_userheight_label = (TextView) findViewById(R.id.aboutYou_userheight_label);
	}

	private void initListener_energyCal() {
		if ((TableLayout) findViewById(R.id.energycal_tablelayout_energy) != null) {
			energycal_tablelayout_energy = (TableLayout) findViewById(R.id.energycal_tablelayout_energy);
		}
	}

	private void myInit() {
		initvar();
		initListener();
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
		resolveIntent(getIntent());

		initvar();
		initDrawer();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		setIntent(intent);
		resolveIntent(intent);
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// initvar();
		myInit();
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		// fragmentManager.beginTransaction().replace(R.id.container,
		// PlaceholderFragment.newInstance(position)).commit();
		fragmentManager.beginTransaction()
				.replace(R.id.container, placeholderFragments[position])
				.commit();
	}

	public void onSectionAttached(int sectinoNum) {
		mTitle = navigation_drawer_titles[sectinoNum];
	}

	public void switchSection(int sectionNum) {
		// onSectionAttached(sectionNum);
		// onNavigationDrawerItemSelected(sectionNum);
		mNavigationDrawerFragment.selectItem(sectionNum);
		restoreActionBar();
		ResLinker.loadContent(this, sectionNum);
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

	private void resolveIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
				|| NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
				|| NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
			Parcelable[] rawMsgs = intent
					.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
			NdefMessage[] msgs;
			if (rawMsgs != null) {
				// known tag type
				msgs = new NdefMessage[rawMsgs.length];
				for (int i = 0; i < rawMsgs.length; i++)
					msgs[i] = (NdefMessage) rawMsgs[i];
			} else {
				// unknown tag type
				msgs = new NdefMessage[1];
			}
			Utils.showToast(MainActivity.this, "by nfc");
			switchSection(EnergyCalFragment.drawerPosition);
			energyCalFragment.addRecord();
		} else
			Utils.showToast(MainActivity.this, "not by nfc");
	}

}
