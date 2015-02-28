package edu.hkcc.personalkcalmanagerhkcc.database.stairrecord;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TableRow;
import android.widget.TextView;

import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.MainActivity;
import edu.hkcc.personalkcalmanagerhkcc.R;

/**
 * Created by beenotung on 2/26/15.
 */
public class TableViewRow {
    private final MainActivity mainActivity;
    public TableRow tableRow;
    public     TextView tvWeekNumber;
    public SeekBar seekBarProgress;
    public TextView tvProgress;

    public TableViewRow(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
        tableRow = new TableRow(mainActivity);
        tvWeekNumber=new TextView(mainActivity);
        seekBarProgress=new SeekBar(mainActivity);
        tvProgress=new TextView(mainActivity);
        init();
    }

    /*
    * android:layout_width="match_parent"
        android:layout_height="80dp"
        android:id="@+id/seekBar_xml"
        android:progress="78"
        android:max="100"
        android:progressDrawable="@android:drawable/ic_media_ff"
        android:layout_gravity="center"
        android:thumb="@drawable/runner2"
    * */
    private void init() {

        seekBarProgress.setMinimumHeight(80);
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

        //seekBarProgress.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));

        //seekBarProgress.setMax(100);
        //seekBarProgress.setProgress(Utils.random.nextInt(100));
        //seekBarProgress.setMinimumHeight(80);
        //seekBarProgress.setThumb(mainActivity.res.getDrawable(R.drawable.runner2));
        tvProgress.setText(String.valueOf(seekBarProgress.getProgress()));

         //tableRow.setLayoutParams(new TableRow.LayoutParams(
//                TableRow.LayoutParams.MATCH_PARENT,
//                TableRow.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
        tableRow.setLayoutParams(new TableRow.LayoutParams());
        tableRow.setGravity(Gravity.CENTER);
        tableRow.setId(Utils.random.nextInt(1024));

        tableRow.addView(tvWeekNumber);
        tableRow.addView(seekBarProgress);
        tableRow.addView(tvProgress);
    }
}
