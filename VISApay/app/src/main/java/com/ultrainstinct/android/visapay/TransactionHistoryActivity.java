package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class TransactionHistoryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction_history);

        startActivity(new Intent(this, ExpandableDraggableSwipeableExampleActivity.class));
        finish();
    }
}
