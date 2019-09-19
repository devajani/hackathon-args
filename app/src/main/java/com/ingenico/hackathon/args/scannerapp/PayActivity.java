package com.ingenico.hackathon.args.scannerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;



public class PayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);


        String url = "https://secure.ogone.com/Ncol/Test/orderstandard_UTF8.asp?AMOUNT=1000&BRAND=VISA&CURRENCY=EUR&LANGUAGE=en_US&OPERATION=SAL&ORDERID=TestHackathon1&PM=Creditcard&PSPID=ADATest&SHASIGN=B817C7CB4783D4EB5B2543091023FB1DD054798A";
        WebView view = (WebView) findViewById(R.id.myWebView);
        view.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(url);
    }


}
