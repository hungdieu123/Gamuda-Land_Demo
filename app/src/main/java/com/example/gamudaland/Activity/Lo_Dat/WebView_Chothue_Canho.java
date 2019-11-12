package com.example.gamudaland.Activity.Lo_Dat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.gamudaland.R;

public class WebView_Chothue_Canho extends AppCompatActivity {
WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view__chothue__canho);
        webView=findViewById(R.id.wvTinTuc);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String lk = bundle.getString("link");

            webView.loadUrl(lk);
            webView.setWebViewClient(new WebViewClient());
        }
    }
}
