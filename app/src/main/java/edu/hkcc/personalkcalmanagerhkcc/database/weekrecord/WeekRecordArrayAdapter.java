package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.TextView;
import edu.hkcc.personalkcalmanagerhkcc.R;

import java.util.HashMap;
import java.util.List;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecordArrayAdapter extends ArrayAdapter<WeekRecord> {
    public static final int RES_Id = R.layout.sample_week_record_list_item;
    private final Context context;
    private final List<WeekRecord> weekRecords;
    HashMap<WeekRecord, Integer> mIdMap = new HashMap<WeekRecord, Integer>();

    public WeekRecordArrayAdapter(Context context, List<WeekRecord> weekRecords) {
        super(context, RES_Id, weekRecords);
        this.context = context;
        this.weekRecords = weekRecords;
        for (int i = 0; i < weekRecords.size(); ++i) {
            mIdMap.put(weekRecords.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        WeekRecord item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    /*
    * reference:
    * http://www.vogella.com/tutorials/AndroidListView/article.html
    * */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(RES_Id, parent, false);
        TextView tvWeekNum = (TextView) rowView.findViewById(R.id.sample_week_record_list_tv_week_num);
        SeekBar seekBarProgress = (SeekBar) rowView.findViewById(R.id.sample_week_record_list_seekBar);
        TextView tvProgress = (TextView) rowView.findViewById(R.id.sample_week_record_list_tv_progress);

        tvWeekNum.setText(weekRecords.get(position).getWeekString());


        //seekBarProgress.setMinimumHeight(Math.round(rowView.getResources().getDimension(R.dimen.sample_week_record_list_height)));
        seekBarProgress.setMax(100);
        seekBarProgress.setProgress(Math.round(weekRecords.get(position).getProgress() * 100));
        seekBarProgress.setEnabled(false);
        /*//seekBarProgress.setProgressDrawable(context.getResources().getDrawable(R.drawable.ic_media_ff));
        Bitmap icon = BitmapFactory.decodeResource(mainActivity.res,
                R.drawable.ic_media_ff);
        BitmapDrawable drawable=new BitmapDrawable(icon);
        drawable.setTileModeX(Shader.TileMode.REPEAT);
        drawable.setAlpha(80);
        //seekBarProgress.setProgressDrawable(mainActivity.res.getDrawable(R.drawable.ic_media_ff));
        seekBarProgress.setProgressDrawable(drawable);
        //BitmapDrawable runner=new BitmapDrawable(BitmapFactory.decodeResource(mainActivity.res,R.drawable.runner2));
        //runner.setAlpha();
        seekBarProgress.setThumb(mainActivity.res.getDrawable(R.drawable.runner3));*/

        tvProgress.setText(String.format("%d%%", Math.round(weekRecords.get(position).getProgress() * 100)));

        return rowView;
    }
}
