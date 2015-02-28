package edu.hkcc.personalkcalmanagerhkcc;

import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import edu.hkcc.myutils.MyFragment;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.StairRecord;
import edu.hkcc.personalkcalmanagerhkcc.database.stairrecord.TableViewRow;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecord;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordArrayAdapter;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordDAOItem;
import  java.util.List;

public class EnergyCalFragment implements MyFragment {
    public static int drawerPosition = ResLinker
            .getSectionNum(R.layout.fragment_energy_cal);
    public float targetPerWeek;
    private MainActivity mainActivity;
    private int calAccumulate=0;

    public EnergyCalFragment(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void accumulateCal(int newCal) {
        calAccumulate += newCal;
    }

    public int getCalAccum() {
        return calAccumulate;
    }

    public String getCalAccumAsString() {
        return String.valueOf(calAccumulate);
    }

    /* load from database */
    public void showRecords() {
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
        TableViewRow tableViewRow=new TableViewRow(mainActivity);
        //mainActivity.energyCal_tablelayout_energy.addView(tableViewRow.tableRow);
        tableViewRow.tableRow.removeView(tableViewRow.seekBarProgress);
        ((LinearLayout)mainActivity.findViewById(R.id.LinearLayout1)).addView(tableViewRow.seekBarProgress);
    }

    /* show record (row) to layout */
    public void showRecord(TableViewRow viewRow){

    }

    public void addRecord() {
        int newCal = Integer.valueOf(mainActivity
                .getString(R.string.energyCal_energy_per_walk_stair));
        accumulateCal(newCal);
        showRecords();
    }

    public void addTableViewRecord(StairRecord record) {
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
        //tableRow.addView(textView1);
        //tableRow.addView(textView2);
        //tableRow.addView(textView3);
        TableViewRow tableViewRow=new TableViewRow(mainActivity);
        tableRow.addView(tableViewRow.tvWeekNumber);
        tableRow.addView(tableViewRow.seekBarProgress);
        tableRow.addView(tableViewRow.tvProgress);
        mainActivity.energyCal_tablelayout_energy.addView(tableRow);

        mainActivity.energyCal_tablelayout_energy.addView(tableViewRow.tableRow);
    }

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                setupListView();
                // showRecords();
                //TODO remove debug
                addRecord();
            }
        };
    }

    private void setupListView() {

         final List<WeekRecord> list = mainActivity.myDAO.weekRecordDAOItem.getAll();
         final WeekRecordArrayAdapter adapter = new WeekRecordArrayAdapter(mainActivity,                 list);
        mainActivity.energyCal_listView_weekRecord.setAdapter(adapter);

        mainActivity.energyCal_listView_weekRecord.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view,
                                    int position, long id) {
                final String item = (String) parent.getItemAtPosition(position);
                view.animate().setDuration(2000).alpha(0)
                        .withEndAction(new Runnable() {
                            @Override
                            public void run() {
                                list.remove(item);
                                adapter.notifyDataSetChanged();
                                view.setAlpha(1);
                            }
                        });
            }
        });
    }


}
