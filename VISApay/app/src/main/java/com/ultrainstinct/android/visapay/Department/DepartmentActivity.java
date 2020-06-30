package com.ultrainstinct.android.visapay.Department;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.ultrainstinct.android.visapay.Adapters.MenuAdapter;
import com.ultrainstinct.android.visapay.Auth.LoginActivity;
import com.ultrainstinct.android.visapay.CrowdDetailsActivity;
import com.ultrainstinct.android.visapay.Models.Item;
import com.ultrainstinct.android.visapay.Option.OptionChangeBarcodeActivity;
import com.ultrainstinct.android.visapay.Option.OptionChangeQRcodeActivity;
import com.ultrainstinct.android.visapay.Option.OptionParkingActivity;
import com.ultrainstinct.android.visapay.Option.OptionSaleActivity;
import com.ultrainstinct.android.visapay.R;
import com.ultrainstinct.android.visapay.VerifyCartActivity;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DepartmentActivity extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener, ShakeDetector.OnShakeListener {

    private static final long RIPPLE_DURATION = 250;
    Button tvSwitch;
    Button tvpaytm;
    Button tvhosp;
    Button tvsignout;
    private RecyclerView recyclerView;
    private ArrayList<Item> arrayList;
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabaseRef;
    FirebaseUser firebaseUser;
    private DatabaseReference mDatabaseUser;
    private FirebaseUser mCurrentUser;
    private FirebaseAuth firebaseAuth;


    Toolbar toolbar;
    FrameLayout root;
    View contentHamburger;
    static boolean shake = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_department);


        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Login History");
        firebaseUser = firebaseAuth.getCurrentUser();
        checkReadPermissionPermission();
        checkInternetPermission();
        checkPhonePermission();
        checkVibratePermission();


//        mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("Login").child(firebaseUser.getUid());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Valid", Context.MODE_PRIVATE);
        if(sharedPreferences != null || sharedPreferences.getInt("Valid",Context.MODE_PRIVATE) == 1) {
            SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Valid",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt("Valid", 0);
            editor.commit();

            SharedPreferences sfEmail = getBaseContext().getSharedPreferences("email",Context.MODE_PRIVATE);
            String email = sfEmail.getString("email","Email Not found");

            SharedPreferences sflocation = getBaseContext().getSharedPreferences("location",Context.MODE_PRIVATE);
            String location = sflocation.getString("location","DLF Cyber Greens, DLF Cyber City, DLF Phase 2, Sector 24, Gurugram, Haryana 122002");
            uploadFile(email,currentDateTimeString,location);
        }

        mAuth = FirebaseAuth.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new Item(getString(R.string.groceries), R.drawable.groceries, "#ffffff"));
        arrayList.add(new Item(getString(R.string.electronics), R.drawable.electronics, "#ffffff"));
        arrayList.add(new Item(getString(R.string.men_clothing), R.drawable.men_clothing, "#ffffff"));
        arrayList.add(new Item(getString(R.string.women_clothing), R.drawable.women_clothing, "#ffffff"));
        arrayList.add(new Item(getString(R.string.accessories), R.drawable.accessories, "#ffffff"));

        MenuAdapter menuAdapter = new MenuAdapter(this, arrayList, this);
        recyclerView.setAdapter(menuAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("Departments");
        }


        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        contentHamburger = findViewById(R.id.content_hamburger);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine2, null);
        root.addView(guillotineMenu);


        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger2), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        View v = LayoutInflater.from(this).inflate(R.layout.guillotine2, null);

        tvSwitch = v.findViewById(R.id.tvSwitch);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);
        
        tvsignout.setText("Log Out from all sessions");

        tvSwitch.setOnClickListener(this);
        tvpaytm.setOnClickListener(this);
        tvhosp.setOnClickListener(this);
        tvsignout.setOnClickListener(this);

    }


    private void uploadFile(String email, String dateAndTime,String location) {
//        LoginHistory upload = new LoginHistory(email,dateAndTime,location);
//        String uploadId = mDatabaseRef.push().getKey();
//        mDatabaseRef.child(uploadId).setValue(upload);
    }


    public static int getString() {
//        if (str >= Strings.length) str = 0;
//        return Strings[str++];
        return 0;
    }

    public static int getImageResource() {
//        if (imageResourceIndex >= imageResources.length) imageResourceIndex = 0;
//        return imageResources[imageResourceIndex++];
        return 0;
    }

    public void start(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent(this, MainActivity.class);
//        startActivity(in);
    }

    public void stock(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, ChatActivity.class);
//        startActivity(in);
    }

    public void sales(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, Spacewar.class);
//        startActivity(in);
    }

    public void buy(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MemeActivity.class);
//        startActivity(in);
    }

    public void anonymous(int pos) {
        // Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent(this, AnonymousChat.class);
//        startActivity(in);
    }

    public void ordering(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent( this, MusicActivity.class);
//        startActivity(in);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tvhosp: {
                hosp(null);
                break;
            }
            case R.id.tvsignout: {
//                mAuth.signOut();
//                startActivity(new Intent(getBaseContext(),LoginActivity.class));
//                login(null);
                Toast.makeText(this, "You have been logged out from all sessions", Toast.LENGTH_SHORT).show();
                break;
            }
        }
    }

    public void login(View v) {
        startActivity(new Intent(getBaseContext(), LoginActivity.class));
        finish();
    }

    public void hosp(View v) {
//        startActivity(new Intent(this,NearbyHospitalsActivity.class));

        if (shake) {
            shake = false;
//            if (SignupActivity.switchNumber == 0)
//                Toast.makeText(this, "Shake to call disabled", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "अक्षम कॉल करने के लिए हिलाएँ", Toast.LENGTH_SHORT).show();
//            ShakeDetector.destroy();
//            v.setBackgroundColor(Color.rgb(255, 0, 0));

        } else {
            shake = true;
//            if (SignupActivity.switchNumber == 0)
//                Toast.makeText(this, "Shake to call enabled", Toast.LENGTH_SHORT).show();
//            else
//                Toast.makeText(this, "सक्षम कॉल करने के लिए हिला", Toast.LENGTH_SHORT).show();
//            ShakeDetector.create(this, this);
//            v.setBackgroundColor(Color.parseColor("#19783b"));

        }

    }


    @Override
    public void onItemClick(Item item) {

        if (item.getText().equals("Groceries"))
            startActivity(new Intent(getBaseContext(), GroceriesActivity.class));
        else if (item.getText().equals("Electronics"))
            startActivity(new Intent(getBaseContext(), ElectronicsActivity.class));
        else if (item.getText().equals("Men Clothing"))
            startActivity(new Intent(getBaseContext(), MenClothingActivity.class));
        else if (item.getText().equals("Women Clothing"))
            startActivity(new Intent(getBaseContext(), WomenClothingActivity.class));
        else if (item.getText().equals("Accessories"))
            startActivity(new Intent(getBaseContext(), AccessoriesActivity.class));
    }

    @Override
    public void OnShake() {
        if (checkPhonePermission()) {
            if (shake) {
//                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "8851506992"));
//                startActivity(intent);
            }

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_CONTACTS}, 123);

        }

    }


    public boolean checkPhonePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 123);
            }
            return false;

        } else
            return true;
    }

    public boolean checkReadPermissionPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 123);
            }
            return false;

        } else
            return true;
    }

    public boolean checkInternetPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.INTERNET) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.INTERNET)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET}, 124);
            }
            return false;

        } else
            return true;
    }


    public boolean checkVibratePermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.VIBRATE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.VIBRATE)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.VIBRATE}, 125);
            }
            return false;

        } else
            return true;
    }
}