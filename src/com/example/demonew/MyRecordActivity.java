package com.example.demonew;

import java.util.ArrayList;
import java.util.Locale;

import android.app.Activity;

import android.content.Intent;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.nfc.Tag;
import android.os.Parcelable;
import android.provider.Settings;
import android.text.AlteredCharSequence;

public class MyRecordActivity extends Activity {
	TableLayout table_layout_my_record;
	Button button_date;
	int times = 0;
	int cal = 0;

	private NfcAdapter nfcAdapter;
	private NdefMessage ndefMessage;
	private PendingIntent pendingIntent;
	private AlertDialog alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// table
		table_layout_my_record = (TableLayout) findViewById(R.id.table_layout_my_record);
		button_date = (Button) findViewById(R.id.button_date);
		button_date.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MyRecordActivity.this, R.string.add_record, Toast.LENGTH_SHORT).show();
				addRecord();
			}
		});
		// nfc
		setContentView(R.layout.activity_my_record);
		nfcAdapter = nfcAdapter.getDefaultAdapter(this);
		pendingIntent = PendingIntent.getActivity(this, 0,
				new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
		
		updateNfcStatusReport();
	}

	@Override
	protected void onResume() {
		super.onResume();
		updateNfcStatusReport();
		nfcAdapter.enableForegroundDispatch(this, pendingIntent, null, null);
	}

	@Override
	protected void onPause() {
		super.onPause();
		updateNfcStatusReport();
		nfcAdapter.disableForegroundDispatch(this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// super.onNewIntent(intent);
		setIntent(intent);
		resolveIntent(intent);
	}
	private void resolveIntent(Intent intent) {
		String action = intent.getAction();
		if (NfcAdapter.ACTION_NDEF_DISCOVERED.equals(action)
				|| NfcAdapter.ACTION_TAG_DISCOVERED.equals(action)
				|| nfcAdapter.ACTION_TECH_DISCOVERED.equals(action)) {
			addRecord();
		}
	}

	private void showToast(int msgId) {
		Toast.makeText(this, msgId, Toast.LENGTH_SHORT).show();
	}

	private void showToast(String msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	private void updateNfcStatusReport() {
		if (nfcAdapter == null) {
			showAlertDialog(R.string.nfc_not_found);
			finish();
		} else {
			if (nfcAdapter.isEnabled()) {
				showToast(R.string.nfc_enabled);
			} else {
				showAlertDialog(R.string.nfc_not_enabled);
			}
		}
	}

	private void showAlertDialog(int msgId) {
		alertDialog.setTitle(R.string.alertDialogTitle);
		alertDialog.setMessage(getText(msgId));
		alertDialog.show();
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
