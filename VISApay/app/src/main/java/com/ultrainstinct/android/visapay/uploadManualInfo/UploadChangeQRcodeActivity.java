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
import com.google.zxing.qrcode.encoder.QRCode;
import com.ultrainstinct.android.visapay.Models.Barcode;
import com.ultrainstinct.android.visapay.Models.QRcode;
import com.ultrainstinct.android.visapay.R;

public class UploadChangeQRcodeActivity extends AppCompatActivity {

    private EditText etCode,etConcern;
    private Button btnSubmit;
    DatabaseReference mDatabaseRef;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_qrcode);

        etCode = findViewById(R.id.et_Code);
        etConcern = findViewById(R.id.et_Concern);

        btnSubmit = findViewById(R.id.submit);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Cart");
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
        String uploadId = mDatabaseRef.push().getKey().toString();
        String code = etCode.getText().toString();
        String concern = etConcern.getText().toString();
        QRcode upload = new QRcode(code,concern,uploadId);
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
    }
}
