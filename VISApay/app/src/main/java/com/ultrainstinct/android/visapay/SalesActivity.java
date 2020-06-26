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
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleSalesActivity;
import com.ultrainstinct.android.visapay.AdvancedRecyclerViews.ExpandableDraggableSwipeableExampleVerifyCartActivity;
import com.ultrainstinct.android.visapay.Models.Cart;
import com.ultrainstinct.android.visapay.Models.Sale;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableSalesDataProvider;
import com.ultrainstinct.android.visapay.common.data.ExampleExpandableVerifyCartDataProvider;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class SalesActivity extends AppCompatActivity {

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
                startActivity(new Intent(getBaseContext(), ExpandableDraggableSwipeableExampleSalesActivity.class));
                finish();
            }
        },3000);
    }


    private void getSaleContents() {
        mRef = FirebaseDatabase.getInstance().getReference();
        mCart = mRef.child("Sale");

        mCart.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> c = dataSnapshot.getChildren();
                for (DataSnapshot dsCart : c) {
                    Sale saleContent = dsCart.getValue(Sale.class);
                    ExampleExpandableSalesDataProvider.salesContents.add(saleContent);
                    String s;
                    s = "sale(%) : ";
                    s = s + String.valueOf(saleContent.getAmount());
                    s = s + "$";
                    ExampleExpandableSalesDataProvider.groupItems = ExampleExpandableSalesDataProvider.groupItems + s;
                    s = s + "key : ";
                    s = s + String.valueOf(saleContent.getKey());
                    s = s + "$";
                    s = s + "product : ";
                    s = s + saleContent.getProduct();
                    s = s + "$";
                    ExampleExpandableSalesDataProvider.childItems = ExampleExpandableSalesDataProvider.childItems + s;
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
        ExampleExpandableSalesDataProvider.groupItems = "";
        ExampleExpandableSalesDataProvider.childItems = "";
        getSaleContents();
    }
}
