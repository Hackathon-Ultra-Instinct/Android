package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PayActivity extends AppCompatActivity {

    private WebView chatWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        chatWindow = findViewById(R.id.chatWindow);

        WebSettings webSettings = chatWindow.getSettings();
        webSettings.setJavaScriptEnabled(true);
        WebViewClientImpl webViewClient = new WebViewClientImpl(PayActivity.this);
        chatWindow.setWebViewClient(webViewClient);
        chatWindow.loadUrl("https://visapay.herokuapp.com/pay/-MAb7bUTF5gTxzpalF31");
    }
    public class WebViewClientImpl extends WebViewClient {
        private Activity activity = null;

        public WebViewClientImpl(PayActivity activity) {
            this.activity = activity;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView webView, String url) {
            if (url.indexOf("https://visapay.herokuapp.com/pay/-MAb7bUTF5gTxzpalF31") > -1) return false;

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            activity.startActivity(intent);
            return true;
        }
    }
}
