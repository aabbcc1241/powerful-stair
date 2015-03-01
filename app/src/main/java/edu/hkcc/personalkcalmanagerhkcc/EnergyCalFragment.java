package edu.hkcc.personalkcalmanagerhkcc;

import android.view.View;
import android.widget.AdapterView;

import java.util.List;

import edu.hkcc.myutils.MyFragment;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecord;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordArrayAdapter;

public class EnergyCalFragment implements MyFragment {
    public static int drawerPosition = ResLinker
            .getSectionNum(R.layout.fragment_energy_cal);
    public float targetPerWeek;
    private MainActivity mainActivity;
    private int calAccumulate = 0;

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

    @Override
    public Runnable getLoadContentRunnable() {
        return new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                setupListView();
                // showRecords();
                //TODO remove debug
                //addRecord();
            }
        };
    }

    /* load from database */
    private void setupListView() {
        final List<WeekRecord> list = mainActivity.myDAO.weekRecordDAOItem.getAll();
        final WeekRecordArrayAdapter adapter = new WeekRecordArrayAdapter(mainActivity, list);
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
