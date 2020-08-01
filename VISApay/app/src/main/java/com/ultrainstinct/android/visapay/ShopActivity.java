package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ultrainstinct.android.visapay.uploadManualInfo.UploadChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanShopActivity;

public class ShopActivity extends AppCompatActivity {

    Button btnReadData,btnScanData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

//        btnManualData = findViewById(R.id.btnManualData);
        btnReadData = findViewById(R.id.btnReadData);
        btnScanData = findViewById(R.id.btnScanData);

//        btnManualData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(getBaseContext(), UploadChangeBarcodeActivity.class));
//            }
//        });

        btnReadData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ViewCartActivity.class));
            }
        });

        btnScanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadScanShopActivity.class));
            }
        });
    }
}
