package com.example.demonew;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyRecordActivity extends Activity {
	TableLayout table_layout_my_record;
	Button button_date;
	int times = 0;
	int cal = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_record);
	}

	@Override
	protected void onResume() {
		super.onResume();
		button_date = (Button) findViewById(R.id.button_date);
		button_date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyRecordActivity.this, R.string.add_record, Toast.LENGTH_SHORT).show();
				addRecord();
			}
		});
	}

	private void addRecord() {
		times++;
		cal += 15;
		TextView tv1 = (TextView) findViewById(R.id.tv_1);
		TextView tv2 = (TextView) findViewById(R.id.tv_2);
		TextView tv3 = (TextView) findViewById(R.id.tv_3);
		tv1.setText(R.string.energy_type_hkcc_stair_walk);
		tv2.setText(String.valueOf(times));
		tv3.setText(String.valueOf(cal));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_record, menu);
		return true;
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
}
