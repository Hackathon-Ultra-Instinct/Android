package com.ultrainstinct.android.visapay.uploadScanInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.ultrainstinct.android.visapay.LiveBarcodeScanningActivity;
import com.ultrainstinct.android.visapay.R;

public class UploadScanShopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_scan_shop);

        LiveBarcodeScanningActivity.source = 1;

        startActivity(new Intent(this,LiveBarcodeScanningActivity.class));
        finish();
    }
}
