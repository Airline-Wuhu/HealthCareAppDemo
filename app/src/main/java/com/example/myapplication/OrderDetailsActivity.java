package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.contentcapture.DataShareRequest;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class OrderDetailsActivity extends AppCompatActivity {

    Map<String, String> item;
    ArrayList list;
    ListView lst;
    Button btnBack;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        btnBack = findViewById(R.id.ODBack);
        lst = findViewById(R.id.OrderDetails);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderDetailsActivity.this, HomeActivity.class));
            }
        });

        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "").toString();
        ArrayList dbData = db.getOrderData(username);

        String[][] orders = new String[dbData.size()][];
        for (int i = 0; i < orders.length; i++) {
            orders[i] = new String[5];
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            orders[i][0] = strData[0];
            orders[i][1] = strData[1];
            if (strData[7].compareTo("medicine") == 0) {
                orders[i][3] = strData[4];
            } else {
                orders[i][3] = strData[4] + " " + strData[5];
            }
            orders[i][2] = "Rs." + strData[6];
            orders[i][4] = strData[7];
        }

        list = new ArrayList();
        for (int i = 0; i < orders.length; i++) {
            item = new HashMap<String, String>();
            for (int j = 0; j < 5; j++) {
                item.put("line" + String.valueOf(j + 1), orders[i][j]);
            }
            list.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1", "line2", "line3", "line4", "line5"}, new int[] {R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        lst.setAdapter(simpleAdapter);
    }
}