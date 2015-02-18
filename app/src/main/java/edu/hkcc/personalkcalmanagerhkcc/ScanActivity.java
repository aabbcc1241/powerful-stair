package edu.hkcc.personalkcalmanagerhkcc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;

import edu.hkcc.myutils.Utils;
import edu.hkcc.personalkcalmanagerhkcc.database.stair.StairCode;


public class ScanActivity extends Activity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan);
        initVar();
        scanQR();
    }

    private void initVar() {
        button = (Button) findViewById(R.id.bntscan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScanActivity.this.onClick();
            }
        });
    }

    private void onClick() {
        scanQR();
    }

    public void scanQR() {
        if (!MainActivity.currentActivity.scanning) return;
        IntentIntegrator intentIntegrator = new IntentIntegrator(this);
        intentIntegrator.initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.w("QR", "RESULT");
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            String contents = data.getStringExtra("SCAN_RESULT");
            String format = data.getStringExtra("SCAN_RESULT_FORMAT");
            Log.w("QR", "content: "+contents);
            Log.w("QR", "format: "+format);
            // Handle successful scan
            StairCode stairCode = new StairCode(contents, format);
            MainActivity.currentActivity.receiveStairCode(stairCode);
        } else if (resultCode == RESULT_CANCELED) {
            // Handle cancel
        }
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
