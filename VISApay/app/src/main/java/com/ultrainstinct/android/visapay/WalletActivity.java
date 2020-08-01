package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WalletActivity extends AppCompatActivity {
    private WebView chatWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet);
        chatWindow = findViewById(R.id.chatWindow);

        WebSettings webSettings = chatWindow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(WalletActivity.this);
        chatWindow.setWebViewClient(webViewClient);
        chatWindow.loadUrl("https://pay.google.com/gp/w/u/0/home/paymentmethods");
    }
    public class WebViewClientImpl extends WebViewClient {
        private Activity activity = null;

        public WebViewClientImpl(WalletActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.indexOf("https://pay.google.com/gp/w/u/0/home/paymentmethods") > -1) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
