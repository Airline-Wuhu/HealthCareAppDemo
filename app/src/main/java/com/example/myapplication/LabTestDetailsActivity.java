package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {
    TextView tvPackage, tvTotalCost;
    EditText edDetails;
    Button back, cart;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        tvPackage = findViewById(R.id.LTDPackage);
        tvTotalCost = findViewById(R.id.LTDPrice);
        edDetails = findViewById(R.id.LabTestDetailsLines);


        edDetails.setKeyListener(null);
        Intent intent = getIntent();
        tvPackage.setText(intent.getStringExtra("text1"));
        edDetails.setText(intent.getStringExtra("text2"));
        tvTotalCost.setText("Total cost: " + intent.getStringExtra("text3"));

        cart = findViewById(R.id.buttonLTDCart);
        back = findViewById(R.id.btnLTDBack);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared_prefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = shared_prefs.getString("username", "").toString();
                String product = tvPackage.getText().toString();

                String text3 = intent.getStringExtra("text3").toString();

                float price = Float.parseFloat(text3.substring(1, text3.length()));
                Database healthcare = new Database(getApplicationContext(), "healthcare", null, 1);
                if (healthcare.checkCart(username, product)) {
                    Toast.makeText(getApplicationContext(), "Product already added", Toast.LENGTH_SHORT).show();
                } else {
                    healthcare.addCart(username, product, price, "lab");
                    Toast.makeText(getApplicationContext(), "Product added", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
                }
            }
        });
    }


}