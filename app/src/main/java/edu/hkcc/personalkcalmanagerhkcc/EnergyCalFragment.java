package edu.hkcc.personalkcalmanagerhkcc;

import android.view.Gravity;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Random;

import edu.hkcc.myutils.MyFragment;

public class EnergyCalFragment implements MyFragment {
    public static int drawerPosition = ResLinker
            .getSectionNum(R.layout.fragment_energy_cal);
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

    /* load from database */
    public void showRecord() {
        int id = new Random().nextInt(1024);
        TableRow tableRow = new TableRow(mainActivity);
        tableRow.setLayoutParams(new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT, Gravity.CENTER_HORIZONTAL));
        tableRow.setGravity(Gravity.CENTER_HORIZONTAL);
        tableRow.setId(id);
        TextView textView1 = new TextView(mainActivity);
        TextView textView2 = new TextView(mainActivity);
        TextView textView3 = new TextView(mainActivity);
        textView1.setText(mainActivity
                .getString(R.string.energyCal_energy_type_hkcc_stair_walk));
        Calendar calendar = Calendar.getInstance();
        int date = calendar.get(Calendar.DATE);
        int month = calendar.get(Calendar.MONTH);
        textView2.setText(date + "/" + month);
        textView3.setText(getCalAccumAsString());
        tableRow.addView(textView1);
        tableRow.addView(textView2);
        tableRow.addView(textView3);
        mainActivity.energyCal_tablelayout_energy.addView(tableRow);
    }

    public void addRecord() {
        int newCal = Integer.valueOf(mainActivity
                .getString(R.string.energyCal_energy_per_walk_stair));
        accumCal(newCal);
        showRecord();
    }

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                // showRecord();
            }
        };
    }

}
