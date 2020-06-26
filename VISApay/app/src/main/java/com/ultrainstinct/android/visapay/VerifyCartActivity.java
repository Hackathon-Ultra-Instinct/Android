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
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleVerifyCartActivity;
import com.ultrainstinct.android.visapay.Models.Cart;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableVerifyCartDataProvider;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class VerifyCartActivity extends AppCompatActivity {

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
                startActivity(new Intent(getBaseContext(), ExpandableDraggableSwipeableExampleVerifyCartActivity.class));
                finish();
            }
        },3000);
    }


    private void getCartContents() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mCart = mRef.child("Cart");

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                for (DataSnapshot dsCart : c) {
                    Cart cartContent = dsCart.getValue(Cart.class);
                    ExampleExpandableVerifyCartDataProvider.cartContents.add(cartContent);
                    String s;
                    s = "user : ";
                    s = s + cartContent.getUserId();
                    s = s + "$";
                    ExampleExpandableVerifyCartDataProvider.groupItems = ExampleExpandableVerifyCartDataProvider.groupItems + s;
                    s = s + "status : ";
                    s = s + String.valueOf(cartContent.getKey());
                    s = s + "$";
                    s = s + "product : ";
                    s = s + cartContent.getProduct();
                    s = s + "$";
                    ExampleExpandableVerifyCartDataProvider.childItems = ExampleExpandableVerifyCartDataProvider.childItems + s;
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
        ExampleExpandableVerifyCartDataProvider.groupItems = "";
        ExampleExpandableVerifyCartDataProvider.childItems = "";
        getCartContents();
    }
}
