package edu.hkcc.personalkcalmanagerhkcc;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.nfc.NdefMessage;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.widget.DrawerLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.database.MyDAO;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairEvent;

public class MainActivity extends Activity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /* my vars */
    public static boolean inited = false;
    public static MainActivity currentActivity = null;

    /* nfc */
    public Resources res;
    public String[] navigation_drawer_titles;
    public PlaceholderFragment[] placeholderFragments;
    /* layout fragments */
    public WelcomeFragment welcomeFragment;
    public AboutYouFragment aboutYouFragment;
    public EnergyCalFragment energyCalFragment;
    public TipsOnExFragment tipsOnExFragment;
    public TipsOnNutritionFragment tipsOnNutritionFragment;
    /* layout elements */
    // welcome
    public Button welcome_button_start;
    // about you
    public Button aboutYou_button_update;
    public TextView aboutYou_userheight_label;
    public TextView aboutYou_userweight_label;
    public EditText aboutYou_editText_username;
    public EditText aboutYou_editText_userage;
    public EditText aboutYou_editText_userheight;
    public EditText aboutYou_editText_userweight;
    public EditText aboutYou_editText_userbmi;
    // energy cal
    public ListView energyCal_listView_weekRecord;
    // tips on ex
    public WebView tipsOnEx_webView;
    // tips on nutrition
    public WebView tipsOnNutrition_webView;
    // database stuff
    public MyDAO myDAO;
    public StairEvent currentStairEvent = new StairEvent(this);

    /**
     * Fragment managing the behaviors, interactions and presentation of the
     * navigation drawer.
     */
    NavigationDrawerFragment mNavigationDrawerFragment;
    /**
     * Used to store the last screen title. For use in
     * {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;


    private void initVar() {
        if (inited)
            return;
        res = getResources();

        //database
        //stairMapItemDAO = new StairMapItemDAO(this);
        //stairMapDatabaseHelper = new StairMapDatabaseHelper(this, null);
        //stairMapDatabaseHelper.onCreate(stairMapDatabaseHelper.getWritableDatabase());
        myDAO = new MyDAO(this);
        myDAO.myInit();

        // layout
        navigation_drawer_titles = res.getStringArray(R.array.navigation_drawer_titles);
        placeholderFragments = new PlaceholderFragment[navigation_drawer_titles.length];
        for (int i = 0; i < placeholderFragments.length; i++)
            placeholderFragments[i] = PlaceholderFragment.newInstance(i);
        welcomeFragment = new WelcomeFragment(this);
        aboutYouFragment = new AboutYouFragment(this);
        energyCalFragment = new EnergyCalFragment(this);
        tipsOnExFragment = new TipsOnExFragment(this);
        tipsOnNutritionFragment = new TipsOnNutritionFragment(this);

        inited = true;
    }

    public void initDrawer() {
        mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager().findFragmentById(
                R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    public void initSections() {
        initVar();
        initSection_welcome();
        initSection_aboutYou();
        initSection_energyCal();
        initSection_tipsOnEx();
        initSection_tipsOnNutrition();
    }

    private void initSection_welcome() {
        welcome_button_start = (Button) findViewById(R.id.welcome_button_start);
    }

    private void initSection_aboutYou() {
        aboutYou_button_update = (Button) findViewById(R.id.aboutYou_button_update);
        aboutYou_editText_username = (EditText) findViewById(R.id.aboutYou_editText_username);
        aboutYou_editText_userage = (EditText) findViewById(R.id.aboutYou_editText_userage);
        aboutYou_editText_userheight = (EditText) findViewById(R.id.aboutYou_editText_userheight);
        aboutYou_editText_userweight = (EditText) findViewById(R.id.aboutYou_editText_userweight);
        aboutYou_editText_userbmi = (EditText) findViewById(R.id.aboutYou_editText_userbmi);
        aboutYou_userheight_label = (TextView) findViewById(R.id.aboutYou_userheight_label);
        aboutYou_userweight_label = (TextView) findViewById(R.id.aboutYou_userweight_label);
    }

    private void initSection_energyCal() {
        energyCal_listView_weekRecord= (ListView) findViewById(R.id.energyCal_listview_week_record);
    }

    private void initSection_tipsOnEx() {
        tipsOnEx_webView = (WebView) findViewById(R.id.tipsOnEx_webView);
    }

    private void initSection_tipsOnNutrition() {
        tipsOnNutrition_webView = (WebView) findViewById(R.id.tipsOnNutrition_webView);
    }

    private void myInit() {
        initVar();
        initSections();
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        Log.w("MainActivity", "onCreateView");
        initSections();
        return super.onCreateView(name, context, attrs);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        currentActivity = this;
        setContentView(R.layout.activity_main);
        resolveIntent(getIntent());

        initVar();
        initDrawer();
    }


    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        resolveIntent(intent);
    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        myInit();
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getFragmentManager();
        // fragmentManager.beginTransaction().replace(R.id.container,
        // PlaceholderFragment.newInstance(position)).commit();
        fragmentManager.beginTransaction().replace(R.id.container, placeholderFragments[position]).commit();
    }

    public void onSectionAttached(int sectionNum) {
        mTitle = navigation_drawer_titles[sectionNum];
    }

    public void switchSection(int sectionNum) {
        onSectionAttached(sectionNum);
        //onNavigationDrawerItemSelected(sectionNum);
        mNavigationDrawerFragment.selectItem(sectionNum);
        restoreActionBar();
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
        switch (item.getItemId()) {
            case R.id.action_settings:
                settingsOnClick();
                return true;
            case R.id.action_scan:
                scanQRCode();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void scanQRCode() {
        //TODO check week record
        Log.w("QR", "to scan");
        currentStairEvent.scanning = true;
        Intent intent = new Intent(this, ScanActivity.class);
        startActivityForResult(intent, 1);
    }

    private void resolveIntent(Intent intent) {
        String action = intent.getAction();
        if (NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_TECH_DISCOVERED.equals(action)
                || NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)) {
            Parcelable[] rawMsgs = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
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
            Utils.showToast(MainActivity.this, "nfc detected");
            switchSection(EnergyCalFragment.drawerPosition);
            energyCalFragment.addRecord();
        } else
            ;//Utils.showToast(MainActivity.this, "welcome");
    }

    public void settingsOnClick() {
        switchSection(AboutYouFragment.drawerPosition);
    }
}
