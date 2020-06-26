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
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleParkingActivity;
import com.ultrainstinct.android.visapay.Models.Barcode;
import com.ultrainstinct.android.visapay.Models.ParkingDetails;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableChangeBarcodeDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableParkingDataProvider;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ParkingActivity extends AppCompatActivity {

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
                startActivity(new Intent(getBaseContext(), ExpandableDraggableSwipeableExampleParkingActivity.class));
                finish();
            }
        },3000);
    }


    private void getParkingContents() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mCart = mRef.child("Parking");

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                for (DataSnapshot dsCart : c) {
                    ParkingDetails parkingContent = dsCart.getValue(ParkingDetails.class);
                    ExampleExpandableParkingDataProvider.parkingContents.add(parkingContent);
                    String s;
                    s = "car number : ";
                    s = s + parkingContent.getCarNumber();
                    s = s + "$";
                    ExampleExpandableParkingDataProvider.groupItems = ExampleExpandableParkingDataProvider.groupItems + s;
                    s = s + "key : ";
                    s = s + parkingContent.getKey();
                    s = s + "$";
                    s = s + "date : ";
                    s = s + parkingContent.getParkingTime();
                    s = s + "$";
                    ExampleExpandableParkingDataProvider.childItems = ExampleExpandableChangeBarcodeDataProvider.childItems + s;
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
        ExampleExpandableParkingDataProvider.groupItems = "";
        ExampleExpandableParkingDataProvider.childItems = "";
        getParkingContents();
    }
}
