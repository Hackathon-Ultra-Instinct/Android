package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NearbyMerchantsActivity extends AppCompatActivity {

    private WebView chatWindow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearby_merchants);
        chatWindow = findViewById(R.id.chatWindow);

        WebSettings webSettings = chatWindow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(NearbyMerchantsActivity.this);
        chatWindow.setWebViewClient(webViewClient);
        chatWindow.loadUrl("https://www.google.com/maps/search/nearby+merchants+accepting+visa+card/@28.6540595,77.1873551,12z  ");

    }
    public class WebViewClientImpl extends WebViewClient {
        private Activity activity = null;

        public WebViewClientImpl(NearbyMerchantsActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.indexOf("https://www.google.com/maps/search/nearby+merchants+accepting+visa+card/@28.6540595,77.1873551,12z") > -1) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
