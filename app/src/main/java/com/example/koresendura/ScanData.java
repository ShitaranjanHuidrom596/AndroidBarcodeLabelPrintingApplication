package com.example.koresendura;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ScanData extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_data);
        Bundle bundle = getIntent().getExtras();
        String message = bundle.getString("message");

        TextView scandata=(TextView)findViewById(R.id.scandata);
        scandata.setText(message);
    }
    public void backtoprintpage(View view){
        Intent myIntent = new Intent(ScanData.this, selectsize.class);
        ScanData.this.startActivity(myIntent);
    }
}
