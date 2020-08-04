package com.ultrainstinct.android.visapay;

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

import com.ultrainstinct.android.visapay.Adapters.MenuAdapter;
import com.ultrainstinct.android.visapay.Auth.LoginActivity;
import com.ultrainstinct.android.visapay.Department.DepartmentActivity;
import com.ultrainstinct.android.visapay.Models.Item;
import com.ultrainstinct.android.visapay.Products.ProductsActivity;
import com.ultrainstinct.android.visapay.uploadManualInfo.UploadInfoActivity;
import com.github.tbouron.shakedetector.library.ShakeDetector;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.yalantis.guillotine.animation.GuillotineAnimation;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MenuscreenActivity extends AppCompatActivity implements View.OnClickListener, MenuAdapter.ItemListener, ShakeDetector.OnShakeListener {

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
        setContentView(R.layout.activity_menuscreen);

        mAuth = FirebaseAuth.getInstance();
        mCurrentUser = mAuth.getCurrentUser();
        firebaseAuth = FirebaseAuth.getInstance();

        Toast.makeText(this, "Welcome User", Toast.LENGTH_SHORT).show();

//        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Login History");
        firebaseUser = firebaseAuth.getCurrentUser();
        checkReadPermissionPermission();
        checkInternetPermission();
        checkPhonePermission();
        checkCameraPermssion();
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

//        arrayList.add(new Item(getString(R.string.start), R.drawable.start, "#ffffff"));
        arrayList.add(new Item(getString(R.string.enableSession), R.drawable.qr_code, "#ffffff"));
        arrayList.add(new Item(getString(R.string.parking), R.drawable.parking, "#ffffff"));
        arrayList.add(new Item(getString(R.string.wallet), R.drawable.wallet, "#ffffff"));
        arrayList.add(new Item(getString(R.string.merchants), R.drawable.nearby_merchants, "#ffffff"));
        arrayList.add(new Item(getString(R.string.input), R.drawable.form, "#ffffff"));
        arrayList.add(new Item(getString(R.string.shop),R.drawable.shopping,"#ffffff"));
        arrayList.add(new Item(getString(R.string.transaction),R.drawable.transaction_history,"#ffffff"));
        arrayList.add(new Item(getString(R.string.products),R.drawable.product,"#ffffff"));
        arrayList.add(new Item(getString(R.string.departments),R.drawable.deparment,"#ffffff"));



        MenuAdapter menuAdapter = new MenuAdapter(this, arrayList, this);
        recyclerView.setAdapter(menuAdapter);
        GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setTitle("VISA Pay");
        }


        root = findViewById(R.id.root);
        toolbar = findViewById(R.id.toolbar);
        contentHamburger = findViewById(R.id.content_hamburger);

        View guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);


        new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();

        View v = LayoutInflater.from(this).inflate(R.layout.guillotine, null);

        tvSwitch = v.findViewById(R.id.tvSwitch);
        tvpaytm = v.findViewById(R.id.tvpaytm);
        tvhosp = v.findViewById(R.id.tvhosp);
        tvsignout = v.findViewById(R.id.tvsignout);

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

    @Override
    public void onBackPressed() {
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    public static int getString() {
//        if (str >= Strings.length) str = 0;
//        return Strings[str++];
        return 0;
    }

    public void start(int pos) {
        //Toast.makeText(this, Integer.toString(pos), Toast.LENGTH_LONG).show();
//        Intent in = new Intent(this, MainActivity.class);
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
                login(null);
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

        if (item.getText().equals("Enable / Disable Session"))
            startActivity(new Intent(getBaseContext(), QRCodeActivity.class));
        else if (item.getText().equals("Parking Details"))
            startActivity(new Intent(getBaseContext(), ParkingActivity.class));
        else if (item.getText().equals("Your Wallet"))
            startActivity(new Intent(getBaseContext(), WalletActivity.class));
        else if (item.getText().equals("Nearby Merchants"))
            startActivity(new Intent(getBaseContext(), NearbyMerchantsActivity.class));
        else if (item.getText().equals("Fill Your Info"))
            startActivity(new Intent(getBaseContext(), UploadInfoActivity.class));
//        else if (item.getText().equals("Scan Number Plate"))
//            startActivity(new Intent(getBaseContext(), NumberPlateActivity.class));
        else if (item.getText().equals("Shop"))
            startActivity(new Intent(getBaseContext(), ShopActivity.class));
        else if (item.getText().equals("Transaction History"))
            startActivity(new Intent(getBaseContext(), TransactionHistoryActivity.class));
        else if(item.getText().equals("Products"))
            startActivity(new Intent(getBaseContext(), ProductsActivity.class));
        else if(item.getText().equals("Departments"))
            startActivity(new Intent(getBaseContext(), DepartmentActivity.class));

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

    public boolean checkCameraPermssion() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 125);
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