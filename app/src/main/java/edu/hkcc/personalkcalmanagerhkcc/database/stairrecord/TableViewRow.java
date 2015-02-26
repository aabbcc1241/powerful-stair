package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.widget.TableRow;
import android.widget.TextView;

import edu.hkcc.personalkcalmanagerhkcc.MainActivity;

/**
 * Created by beenotung on 2/26/15.
 */
public class TableViewRow {
    private final MainActivity mainActivity;
    public TableRow tableRow;
    TextView textView1;
    TextView textView2;
    TextView textView3;

    public TableViewRow(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        tableRow = new TableRow(mainActivity);
        TextView textView1 = new TextView(mainActivity);
        TextView textView2 = new TextView(mainActivity);
        TextView textView3 = new TextView(mainActivity);
    }
}
