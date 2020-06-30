package com.ultrainstinct.android.visapay.Department;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ultrainstinct.android.visapay.R;

public class WomenClothingActivity extends AppCompatActivity {

    Button btnSession;
    TextView tvCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_women_clothing);

        tvCount = findViewById(R.id.textView2);
        btnSession = findViewById(R.id.button3);

        btnSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Women Clothing", Context.MODE_PRIVATE);

                if(sharedPreferences == null || sharedPreferences.getInt("Women Clothing",Context.MODE_PRIVATE) == 0){
                    Toast.makeText(getBaseContext(),"Welcome! Incrementing headcount",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Women Clothing",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor =sharedPref.edit();
                    editor.putInt("Women Clothing",1);
                    editor.commit();
                    tvCount.setText("Current head Count : 41\n");
                    btnSession.setText("Destroy Session");

                }
                else{
                    Toast.makeText(getBaseContext(),"Good Bye! Decrementing headcount",Toast.LENGTH_SHORT).show();
                    SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Women Clothing",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPref.edit();
                    editor.putInt("Women Clothing",0);
                    editor.commit();
                    tvCount.setText("Current head Count : 40\n");
                    btnSession.setText("Create Session");
                }
            }
        });
    }
}
