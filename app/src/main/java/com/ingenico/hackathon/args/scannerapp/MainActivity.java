package com.ingenico.hackathon.args.scannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static TextView resulTextView;
    Button scanButton;
    Button payButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resulTextView = (TextView) findViewById(R.id.scan_result);
        scanButton = (Button) findViewById(R.id.scan_button);
        payButton = (Button) findViewById(R.id.pay_button) ;

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PayActivity.class));
            }
        });

        scanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), ScanBarcodeActivity.class));
            }
        });

    }
}
