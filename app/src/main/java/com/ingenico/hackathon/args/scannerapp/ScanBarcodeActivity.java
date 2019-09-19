package com.ingenico.hackathon.args.scannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.zxing.Result;

import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    static Map<String, Float> priceMap = new HashMap<>();
    static Map<String, Float> finalMap = new HashMap<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }


    @Override
    public void handleResult(Result result) {
        priceMap.put("meal", 5.50F);
        priceMap.put("snack", 2.50F);
        priceMap.put("drink", 1.50F);

        finalMap.put(result.getText(), priceMap.get(result.getText()));

        MainActivity.resulTextView.setText(finalMap.toString() + "Total :"+getTotal(finalMap));
        onBackPressed();
    }

    private Float getTotal(Map<String, Float> finalMap) {
//        finalMap.keySet().stream().map(key -> key+)
        Float total = 0F;
        for(String key :finalMap.keySet()){

            total+=finalMap.get(key);
        }
        return total;
    }

    @Override
    protected void onPause(){
        super.onPause();
        scannerView.stopCamera();
    }

    @Override
    protected void onResume(){
        super.onResume();
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
}
