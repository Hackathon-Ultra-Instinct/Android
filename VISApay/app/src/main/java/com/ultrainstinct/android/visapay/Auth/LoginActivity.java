package com.ultrainstinct.android.visapay.Auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.ultrainstinct.android.visapay.MenuscreenActivity;
import com.ultrainstinct.android.visapay.MenuscreenActivity2;
import com.ultrainstinct.android.visapay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;


public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private FirebaseAuth mAuth;

    private SwitchCompat adminSwitch;
    public int  switchNumber = 0;

    private static final int REQUEST_READ_CONTACTS = 0;

    EditText _emailText;
    EditText _passwordText;
    Button _loginButton;
    Button _signupLink , btnOtherAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseApp.initializeApp(this);

        mAuth = FirebaseAuth.getInstance();
        adminSwitch = findViewById(R.id.adminSwitch);

        SharedPreferences sharedPreferences = getBaseContext().getSharedPreferences("Valid", Context.MODE_PRIVATE);
        SharedPreferences sharedPref = getBaseContext().getSharedPreferences("Valid",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt("Valid", 1);
        editor.commit();


        _emailText = findViewById(R.id.input_email);
        _passwordText = findViewById(R.id.input_password);
        _loginButton = findViewById(R.id.btn_login);
        _signupLink = findViewById(R.id.link_signup);
        btnOtherAuth  = findViewById(R.id.btnOtherAuth);

        mayRequestContacts();


        btnOtherAuth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(),DifferentAuth.class));
            }
        });

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });


        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });

        adminSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked)
                    switchNumber = 1;
                else
                    switchNumber = 0;
            }
        });

        int value = getIntent().getIntExtra("MenuSreen",1);

        if(value != 1){
            updateUI(mAuth.getCurrentUser());
        }

    }


    private void updateUI(FirebaseUser currentUser){
        if(switchNumber == 1)
            startActivity(new Intent(getBaseContext(),MenuscreenActivity.class));
        else
            startActivity(new Intent(getBaseContext(), MenuscreenActivity2.class));

        finish();
        finish();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        final String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();

        if(switchNumber == 0)
            password = "Admin" + password + "Key";
        else
            password = "User" + password + "Key";


        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            SharedPreferences sharedPref = getBaseContext().getSharedPreferences("email",Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPref.edit();
                            editor.putString("email", email);
                            editor.commit();
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            progressDialog.dismiss();
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(getBaseContext(), task.getException().getMessage(),
                                    Toast.LENGTH_SHORT).show();
                            _loginButton.setEnabled(true);

                        }
                    }
                });

    }

    private boolean mayRequestContacts() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }

        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED && checkSelfPermission(WRITE_CONTACTS) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        requestPermissions(new String[]{READ_CONTACTS, WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE}, REQUEST_READ_CONTACTS);
        return false;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        _loginButton.setEnabled(true);
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();


        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }
        return valid;
    }
}