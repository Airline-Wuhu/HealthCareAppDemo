package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages = {
            {"Package 1 : medicine1", "", "", "", "999"},
            {"Package 2 : medicine2", "", "", "","299"},
            {"Package 3 : medicine3", "", "", "", "899"},
            {"Package 4 : medicine4", "", "", "","499"},
            {"Package 5 : medicine5", "", "", "","699"}
    };
    private String[] package_details = new String[packages.length];

    Map<String, String> item;
    List list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack, btnCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        for (int i =0; i < package_details.length; i++) {
            package_details[i] = "function" + String.valueOf(i);
        }
        lst = findViewById(R.id.buyMediDetails);
        btnBack = findViewById(R.id.buyMediBack);
        btnCart = findViewById(R.id.buyMediCart);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class));
            }
        });
        btnCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class));
            }
        });
        ArrayList list = new ArrayList();
        for (int i = 0; i < packages.length;i++) {
            item = new HashMap<>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " + packages[i][4]);
            list.add(item);

        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1", "line2", "line3", "line4", "line5"}, new int[] {R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        lst.setAdapter(simpleAdapter);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                it.putExtra("text1", packages[position][0]);
                it.putExtra("text2", package_details[position]);
                it.putExtra("text3", packages[position][4]);
                startActivity(it);
            }
        });
    }
}