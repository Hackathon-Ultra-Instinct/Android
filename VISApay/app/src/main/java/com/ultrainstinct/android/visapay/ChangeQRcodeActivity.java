package com.ultrainstinct.android.visapay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.roger.catloadinglibrary.CatLoadingView;
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleActivity;
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleChangeQRcodeActivity;
import com.ultrainstinct.android.visapay.Models.Barcode;
import com.ultrainstinct.android.visapay.Models.QRcode;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeBarcodeDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeQRcodeDataProvider;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ChangeQRcodeActivity extends AppCompatActivity {

    private DatabaseReference mRef;
    private DatabaseReference mCart;
    private CatLoadingView mView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");
        readAndSerialise();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getBaseContext(), ExpandableDraggableSwipeableExampleChangeQRcodeActivity.class));
                finish();
            }
        },3000);
    }


    private void getChangeQRcodeContents() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mCart = mRef.child("QRcode");

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                for (DataSnapshot dsCart : c) {
                    QRcode changeQRcodeContent = dsCart.getValue(QRcode.class);
                    ExampleExpandableChangeQRcodeDataProvider.changeQRcodeContents.add(changeQRcodeContent);
                    String s;
                    s = "code : ";
                    s = s + changeQRcodeContent.getCode();
                    s = s + "$";
                    ExampleExpandableChangeQRcodeDataProvider.groupItems = ExampleExpandableChangeQRcodeDataProvider.groupItems + s;
                    s = s + "key : ";
                    s = s + changeQRcodeContent.getKey();
                    s = s + "$";
                    s = s + "concern : ";
                    s = s + changeQRcodeContent.getConcern();
                    s = s + "$";
                    ExampleExpandableChangeQRcodeDataProvider.childItems = ExampleExpandableChangeQRcodeDataProvider.childItems + s;
                    Log.e(TAG, "onDataChange: " + s );
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e(TAG, "onCancelled: " + databaseError.getMessage());
            }
        });
    }


    private void readAndSerialise() {
        ExampleExpandableChangeQRcodeDataProvider.groupItems = "";
        ExampleExpandableChangeQRcodeDataProvider.childItems = "";
        getChangeQRcodeContents();
    }
}
