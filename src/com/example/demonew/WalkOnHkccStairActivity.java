package com.example.demonew;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class WalkOnHkccStairActivity extends Activity {
	Button button_my_record;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_walk_on_hkcc_stair);
	}

	@Override
	protected void onResume() {
		super.onResume();
		button_my_record = (Button) findViewById(R.id.button_my_record);
		button_my_record.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(WalkOnHkccStairActivity.this, R.string.loading, Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(WalkOnHkccStairActivity.this, MyRecordActivity.class);
				WalkOnHkccStairActivity.this.startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.walk_on_hkcc_stair, menu);
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
