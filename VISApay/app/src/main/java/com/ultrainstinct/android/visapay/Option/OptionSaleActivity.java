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
import com.ultrainstinct.android.visapay.SalesActivity;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadParkingActivity;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadSaleActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanParkingActivity;

public class OptionSaleActivity extends AppCompatActivity {

    Button btnReadData,btnManualData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_sale);

        btnManualData = findViewById(R.id.btnManualData);
        btnReadData = findViewById(R.id.btnReadData);

        btnManualData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadSaleActivity.class));
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), SalesActivity.class));
            }
        });
    }
}
