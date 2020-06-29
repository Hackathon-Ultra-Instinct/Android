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
import com.ultrainstinct.android.visapay.Models.Sale;
import com.ultrainstinct.android.visapay.R;

public class UploadSaleActivity extends AppCompatActivity {

    private EditText etProduct,etAmount;
    private Button btnSubmit;
    DatabaseReference mDatabaseRef;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_sale);

        etProduct = findViewById(R.id.et_Product);
        etAmount = findViewById(R.id.et_Amount);

        btnSubmit = findViewById(R.id.submit);

        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Sale");
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
        Sale upload = new Sale(etProduct.getText().toString(),etAmount.getText().toString(),uploadId);
        mDatabaseRef.child(uploadId).setValue(upload);
        pd.dismiss();
        Toast.makeText(getBaseContext(), "Upload successful", Toast.LENGTH_LONG).show();
    }
}
