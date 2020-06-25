package com.example.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ChangeBarcodeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_barcode);
        startActivity(new Intent(this,ExpandableDraggableSwipeableExampleActivity.class));
        finish();
    }
}
