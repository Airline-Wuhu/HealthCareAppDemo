package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Arrays;
import java.util.regex.Pattern;

public class LabTestBookActivity extends AppCompatActivity {
    EditText edename, edaddress,edcontact, edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_book);

        edename = findViewById(R.id.checkOutName);
        edaddress = findViewById(R.id.checkOutAddress);
        edcontact = findViewById(R.id.checkOutPhone);
        edpincode = findViewById(R.id.checkOutPassword);
        btnBooking = findViewById(R.id.checkOutButton);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote("$"));
        String date = intent.getStringExtra("date");
        String time = intent.getStringExtra("time");
        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "");

                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.addOrder(username, edename.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),edpincode.toString(),date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "lab");
                db.removeCart(username, "lab");
                Toast.makeText(getApplicationContext(), "Your order has been submitted!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LabTestBookActivity.this, HomeActivity.class));
            }
        });
    }
}