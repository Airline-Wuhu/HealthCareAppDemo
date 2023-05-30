package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {
    EditText edename, edaddress,edcontact, edpincode;
    Button btnBooking;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edename = findViewById(R.id.checkOutNameMedi);
        edaddress = findViewById(R.id.checkOutAddressMedi);
        edcontact = findViewById(R.id.checkOutPhoneMedi);
        edpincode = findViewById(R.id.checkOutPasswordMedi);
        btnBooking = findViewById(R.id.checkOutButtonMedi);

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
                db.addOrder(username, edename.getText().toString(), edaddress.getText().toString(), edcontact.getText().toString(),edpincode.toString(),date.toString(), time.toString(), Float.parseFloat(price[1].toString()), "medicine");
                db.removeCart(username, "medicine");
                Toast.makeText(getApplicationContext(), "Your order has been submitted!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
            }
        });
    }
}