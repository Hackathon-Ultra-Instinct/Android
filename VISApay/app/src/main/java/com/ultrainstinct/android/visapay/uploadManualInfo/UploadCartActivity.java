package com.ultrainstinct.android.visapay.uploadManualInfo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ultrainstinct.android.visapay.Models.Cart;
import com.ultrainstinct.android.visapay.R;

public class UploadCartActivity extends AppCompatActivity {

    private EditText etProductName;
    private Button btnSubmit;
    DatabaseReference mDatabaseRef;
    private FirebaseAuth mauth;
    private ProgressDialog pd;
    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_cart);

        etProductName = findViewById(R.id.et_Name);
        btnSubmit = findViewById(R.id.submit);

        mauth = FirebaseAuth.getInstance();
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Cart");
        pd = new ProgressDialog(this);


        userId = mauth.getUid();
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
        Cart upload = new Cart(userId,uploadId,etProductName.getText().toString());
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
    }
}
