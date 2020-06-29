package com.ultrainstinct.android.visapay.uploadManualInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ultrainstinct.android.visapay.Models.Cart;
import com.ultrainstinct.android.visapay.Models.ParkingDetails;
import com.ultrainstinct.android.visapay.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class UploadParkingActivity extends AppCompatActivity {

    private EditText etCarNumber;
    private Button btnSubmit;
    DatabaseReference mDatabaseRef;
    private ProgressDialog pd;
    String parkingTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_parking);

        etCarNumber = findViewById(R.id.et_CarNumber);
        btnSubmit = findViewById(R.id.submit);

        SimpleDateFormat sd = new SimpleDateFormat("dd.MM.yyyy G 'at' HH:mm:ss z");
        Date date = new Date();
        sd.setTimeZone(TimeZone.getTimeZone("IST"));
        parkingTime = sd.format(date);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Parking");
        pd = new ProgressDialog(this);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pd.setMessage("Upload in progress");
                pd.show();
                uploadFile();
            }
        });
    }

    private void uploadFile() {
        String uploadId = mDatabaseRef.push().getKey();
        ParkingDetails upload = new ParkingDetails(etCarNumber.getText().toString(),uploadId,parkingTime);
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
    }
}
