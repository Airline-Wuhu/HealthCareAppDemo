package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.camera2.TotalCaptureResult;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class BuyMedicineDetailsActivity extends AppCompatActivity {
    TextView tvPackageName, tvTotalCost;
    EditText edDetails;
    Button btnBack, btnAddToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_details);
        tvPackageName = findViewById(R.id.BMTitle);
        edDetails = findViewById(R.id.BMDetails);
        edDetails.setKeyListener(null);
        tvTotalCost = findViewById(R.id.BMPrice);
        btnBack = findViewById(R.id.BMBack);
        btnAddToCart = findViewById(R.id.BMCart);

        Intent intent = getIntent();
        tvPackageName.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total Cost: " + intent.getStringExtra("text3"));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
            }
        });

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared_prefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = shared_prefs.getString("username", "").toString();
                String product = tvPackageName.getText().toString();

                String text3 = intent.getStringExtra("text3").toString();

                float price = Float.parseFloat(text3);
                Database healthcare = new Database(getApplicationContext(), "healthcare", null, 1);
                if (healthcare.checkCart(username, product)) {
                    Toast.makeText(getApplicationContext(), "Medicine already added", Toast.LENGTH_SHORT).show();
                } else {
                    healthcare.addCart(username, product, price, "medicine");
                    Toast.makeText(getApplicationContext(), "Medicine added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BuyMedicineDetailsActivity.this, BuyMedicineActivity.class));
                }
            }
        });
    }
}