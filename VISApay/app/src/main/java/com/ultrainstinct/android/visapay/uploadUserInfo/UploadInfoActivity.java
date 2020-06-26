package com.ultrainstinct.android.visapay.uploadUserInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ultrainstinct.android.visapay.MenuscreenActivity;
import com.ultrainstinct.android.visapay.R;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UploadInfoActivity extends AppCompatActivity {

    private EditText etName, etPincode, etCity, etPhone, etCard;
    private Button btnSubmit;

    DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private FirebaseAuth firebaseAuth;

    private FirebaseAuth mauth;
    private FirebaseUser mCurrentUser;
    private DatabaseReference mDatabaseUser;
    private ProgressDialog pd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_info);

        etName = findViewById(R.id.et_Name);
        etCity = findViewById(R.id.et_city_name);
        etPincode = findViewById(R.id.et_pin_code);
        etCard = findViewById(R.id.et_card);
        etPhone = findViewById(R.id.et_phone);
        btnSubmit = findViewById(R.id.submit);

        FirebaseApp.initializeApp(this);
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("General");;

        mauth = FirebaseAuth.getInstance();
        mCurrentUser = mauth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();


//        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("General").child(firebaseUser.getUid());
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
        UploadInfo upload = new UploadInfo(etName.getText().toString(),Long.parseLong(etPhone.getText().toString()),Long.parseLong(etCard.getText().toString()),Integer.parseInt(etPincode.getText().toString()), etCity.getText().toString());
        String uploadId = mDatabaseRef.push().getKey();
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
        finish();
        startActivity(new Intent(getBaseContext(), MenuscreenActivity.class));

    }
}
