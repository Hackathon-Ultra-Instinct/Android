package com.ultrainstinct.android.visapay.Option;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ultrainstinct.android.visapay.ChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.ParkingActivity;
import com.ultrainstinct.android.visapay.R;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadParkingActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanParkingActivity;

public class OptionParkingActivity extends AppCompatActivity {

    Button btnReadData,btnManualData,btnScanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_parking);

        btnManualData = findViewById(R.id.btnManualData);
        btnReadData = findViewById(R.id.btnReadData);
        btnScanData = findViewById(R.id.btnScanData);

        btnManualData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadParkingActivity.class));
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ParkingActivity.class));
            }
        });

        btnScanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadScanParkingActivity.class));
            }
        });
    }
}
