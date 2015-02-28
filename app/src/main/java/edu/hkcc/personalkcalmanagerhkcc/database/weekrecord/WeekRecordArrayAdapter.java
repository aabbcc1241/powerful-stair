package edu.hkcc.personalkcalmanagerhkcc.database.weekrecord;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;

/**
 * Created by beenotung on 2/28/15.
 */
public class WeekRecordArrayAdapter extends ArrayAdapter<WeekRecord> {
    private final Context context;
    private final List<WeekRecord> values;
    public static final int RES_Id=R.layout.sample_week_record_list_item;

    public WeekRecordArrayAdapter(Context context, List<WeekRecord> values) {
        super(context, RES_Id, values);
        this.context=context;
        this.values=values;
        for (int i = 0; i < values.size(); ++i) {
            mIdMap.put(values.get(i), i);
        }
    }

    HashMap<WeekRecord, Integer> mIdMap = new HashMap<WeekRecord, Integer>();



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

        tvWeekNum.setText(values.get(position).getWeekString());

        seekBarProgress.setMinimumHeight(rowView.getdimen);
        seekBarProgress.setProgress(Utils.random.nextInt(100));
        seekBarProgress.setMax(100);
        seekBarProgress.setProgressDrawable(mainActivity.res.getDrawable(R.drawable.ic_media_ff));

        //seekBarProgress.setFocusable(false);
        //seekBarProgress.setFocusableInTouchMode(false);
        Bitmap icon = BitmapFactory.decodeResource(mainActivity.res,
                R.drawable.ic_media_ff);
        BitmapDrawable drawable=new BitmapDrawable(icon);
        drawable.setTileModeX(Shader.TileMode.REPEAT);
        drawable.setAlpha(80);
        //seekBarProgress.setProgressDrawable(mainActivity.res.getDrawable(R.drawable.ic_media_ff));
        seekBarProgress.setProgressDrawable(drawable);

        //BitmapDrawable runner=new BitmapDrawable(BitmapFactory.decodeResource(mainActivity.res,R.drawable.runner2));
        //runner.setAlpha();
        seekBarProgress.setThumb(mainActivity.res.getDrawable(R.drawable.runner3));

        seekBarProgress.setEnabled(false);



        textView.setText(values[position]);
        // change the icon for Windows and iPhone
        String s = values[position];
        if (s.startsWith("iPhone")) {
            imageView.setImageResource(R.drawable.no);
        } else {
            imageView.setImageResource(R.drawable.ok);
        }

        return rowView;


        return super.getView(position, convertView, parent);
    }
}
