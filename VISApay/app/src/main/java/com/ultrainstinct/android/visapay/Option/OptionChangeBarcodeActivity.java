package com.ultrainstinct.android.visapay.Option;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ultrainstinct.android.visapay.ChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.R;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.uploadScanInfo.UploadScanChangeBarcodeActivity;

public class OptionChangeBarcodeActivity extends AppCompatActivity {

    Button btnReadData,btnManualData,btnScanData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_change_barcode);

        btnManualData = findViewById(R.id.btnManualData);
        btnReadData = findViewById(R.id.btnReadData);
        btnScanData = findViewById(R.id.btnScanData);

        btnManualData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadChangeBarcodeActivity.class));
            }
        });

        btnReadData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), ChangeBarcodeActivity.class));
            }
        });

        btnScanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), UploadScanChangeBarcodeActivity.class));
            }
        });
    }
}
