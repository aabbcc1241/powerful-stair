package edu.hkcc.personalkcalmanagerhkcc;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;

import java.util.List;

import edu.hkcc.myutils.Maths;
import edu.hkcc.myutils.MyFragment;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecord;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordArrayAdapter;
import edu.hkcc.personalkcalmanagerhkcc.database.weekrecord.WeekRecordNotFoundException;

public class EnergyCalFragment implements MyFragment {
    public static int drawerPosition = ResLinker
            .getSectionNum(R.layout.fragment_energy_cal);
    public long weekId;
    public float weekTarget;
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
    public Runnable getLoadContentRunnable(final MainActivity mainActivity) {
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

    private void loadVars() {
        try {
            weekId = Maths.millisecondToWeekId(System.currentTimeMillis());
            weekTarget = mainActivity.myDAO.weekRecordDAOItem.getWeekTarget(weekId);
        } catch (WeekRecordNotFoundException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
            builder.setTitle(mainActivity.getString(R.string.energyCal_energy_title_input_target));
            final EditText input = new EditText(mainActivity);
            input.setInputType(InputType.TYPE_CLASS_NUMBER);
            builder.setView(input);
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    try {
                        weekTarget = Float.parseFloat(input.getText().toString());
                        if (weekTarget <= 0)
                            weekTarget = 1;
                        mainActivity.myDAO.weekRecordDAOItem.insert(new WeekRecord(weekId,  weekTarget));
                        setupListView();
                    } catch (NumberFormatException e) {
                        // input not number
                    }
                }
            });
            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();
        }
    }

    /* load from database */
    private void setupListView() {
        final List<WeekRecord> list = mainActivity.myDAO.weekRecordDAOItem.getAll();
        if (list.size() == 0) {
            loadVars();
            return;
        }
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
