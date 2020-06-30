package com.ultrainstinct.android.visapay.Department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sdsmdg.harjot.rotatingtext.RotatingTextWrapper;
import com.sdsmdg.harjot.rotatingtext.models.Rotatable;
import com.ultrainstinct.android.visapay.MainActivity;
import com.ultrainstinct.android.visapay.R;

public class GroceriesActivity extends AppCompatActivity {

    Button btnSession;
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groceries);

        tvCount = findViewById(R.id.textView2);
        btnSession = findViewById(R.id.button3);

        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Groceries", Context.MODE_PRIVATE);

                if(sharedPreferences == null || sharedPreferences.getInt("Groceries",Context.MODE_PRIVATE) == 0){
                    Toast.makeText(getBaseContext(),"Welcome! Incrementing headcount",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Groceries",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPref.edit();
                    editor.putInt("Groceries",1);
                    editor.commit();
                    tvCount.setText("Current head Count : 11\n");
                    btnSession.setText("Destroy Session");

                }
                else{
                    Toast.makeText(getBaseContext(),"Good Bye! Decrementing headcount",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Groceries",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("Groceries",0);
                    editor.commit();
                    tvCount.setText("Current head Count : 10\n");
                    btnSession.setText("Create Session");
                }
            }
        });
    }
}
