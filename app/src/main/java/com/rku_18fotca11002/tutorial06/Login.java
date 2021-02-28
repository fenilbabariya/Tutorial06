package com.rku_18fotca11002.tutorial06;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText textView,edtUsername,edtPassword;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.txtuname);
        edtPassword =   findViewById(R.id.txtPsw);
    }

    public void login(View view) {
        String uName = edtUsername.getText().toString();
        String psw = edtPassword.getText().toString();

        if(uName.equals("")) {
            Toast.makeText(Login.this, "Username can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(psw.equals("")) {
            Toast.makeText(Login.this, "Password can not be empty", Toast.LENGTH_SHORT).show();
            return;
        }

        if(psw.length() < 6){
            Toast.makeText(Login.this, "Password must not be less than 6 character", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(uName).matches()){
            Toast.makeText(Login.this, "Username must be proper Email Address!", Toast.LENGTH_SHORT).show();
            return;
        }

        if(uName.equals("admin@gmail.com") && psw.equals("123456")){
            preferences = getSharedPreferences("college", MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("username",uName);
            editor.apply();

            startActivity(new Intent(Login.this,Welcome.class));
            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
            finish();
        }else {
            Toast.makeText(Login.this, "Login Denied", Toast.LENGTH_SHORT).show();
        }
    }
}