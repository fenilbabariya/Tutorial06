package com.rku_18fotca11002.tutorial06;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.SharedLibraryInfo;
import android.graphics.text.LineBreaker;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {
    SharedPreferences preferences;
    Button btnLogout;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        preferences = getSharedPreferences("college", MODE_PRIVATE);
        String userPreference = preferences.getString("username","");

        if(userPreference.equals("")){
            startActivity(new Intent(Welcome.this,Login.class));
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.logout:

                AlertDialog.Builder builder = new AlertDialog.Builder(Welcome.this);
                    builder.setTitle("Confirm")
                            .setMessage("Are you sure?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            preferences = getSharedPreferences("college",MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.remove("username");
                            editor.apply();

                            startActivity(new Intent(Welcome.this,Login.class));
                            finish();
                        }
                    });
                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Welcome.this, "Canceled", Toast.LENGTH_SHORT).show();
                        }
                    });
                AlertDialog dialog = builder.create();
                dialog.show();
                return true;

            case R.id.back:
                Toast.makeText(Welcome.this, "Please Logout first", Toast.LENGTH_SHORT).show();
                return true;

            default:
                Toast.makeText(Welcome.this, "Default", Toast.LENGTH_SHORT).show();
                return true;
        }
    }

    public void logout(View view) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog);

        btnLogout = dialog.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                preferences = getSharedPreferences("college",MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.remove("username");
                editor.apply();

                startActivity(new Intent(Welcome.this,Login.class));
                finish();
                dialog.dismiss();
            }
        });

        dialog.show();
    }
}