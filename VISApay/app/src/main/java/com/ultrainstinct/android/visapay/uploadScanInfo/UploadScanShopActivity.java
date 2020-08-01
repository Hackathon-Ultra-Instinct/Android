package com.ultrainstinct.android.visapay.uploadScanInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ultrainstinct.android.visapay.LiveBarcodeScanningActivity;
import com.ultrainstinct.android.visapay.R;

public class UploadScanShopActivity extends AppCompatActivity {

    Button btnItem, btnBarcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_scan_shop);

        btnItem = findViewById(R.id.btnItem);
        btnBarcode = findViewById(R.id.btnBarcode);

        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveBarcodeScanningActivity.source = 2;
                startActivity(new Intent(getBaseContext(),LiveBarcodeScanningActivity.class));
                finish();
            }
        });

        btnItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                LiveBarcodeScanningActivity.source = 3;
                startActivity(new Intent(getBaseContext(),LiveBarcodeScanningActivity.class));
                finish();
                return true;
            }
        });

        btnBarcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveBarcodeScanningActivity.source = 1;
                startActivity(new Intent(getBaseContext(),LiveBarcodeScanningActivity.class));
                finish();
            }
        });


    }
}
