package com.example.gamudaland.Activity.Tin_Tuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.gamudaland.R;

public class WebviewActivity extends AppCompatActivity {
    private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        webView=findViewById(R.id.wvTinTuc);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String tt = bundle.getString("title");
            String lk = bundle.getString("link");

            webView.loadUrl(lk);
            webView.setWebViewClient(new WebViewClient());
        }
    }
}
