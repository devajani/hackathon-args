package com.ingenico.hackathon.args.scannerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanBarcodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ZXingScannerView scannerView;
    static Map<String, Float> priceMap = new HashMap<>();
    static Map<String, Float> finalMap = new HashMap<>();


    static List<String> finalList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_DENIED)
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA},100);
        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
    }


    @Override
    public void handleResult(Result result) {
        priceMap.put("meal", 5.50F);
        priceMap.put("snack", 2.50F);
        priceMap.put("drink", 1.50F);

//        finalMap.put(result.getText(), priceMap.get(result.getText()));
//        MainActivity.resulTextView.setText(finalMap.toString() + "Total :"+getTotal(finalMap));
//
        String DisplayMessage = "";
        Float amount = 0F;
        finalList.add(result.getText());
        for ( String currentResult: finalList) {
            Float currentAmount = priceMap.get(currentResult);
            amount += currentAmount;
            DisplayMessage += String.format("%s:\t%s\n", currentResult, currentAmount);
        }
        DisplayMessage += "Total: " + amount;
        MainActivity.resulTextView.setText(DisplayMessage);


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
