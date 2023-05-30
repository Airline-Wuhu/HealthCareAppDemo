package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Package 1 : Full Body Checkup", "", "", "", "$999"},
            {"Package 2 : Blood Glucose Fasting", "", "", "","$299"},
            {"Package 3 : Covid-19 Antibody", "", "", "", "$899"},
            {"Package 4 : Thyroid Check", "", "", "","$499"},
            {"Package 5 : Immunity Check", "", "", "","$699"}
    };
    private String[] package_details = {
            "Blood Glucose Fasting\n" +
                    "HbA1c\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "LDH Lactate Dehydrogenase, Serum\n" +
                    "Lipid Profile\n" +
                    "Liver Function Test",
            "Blood Glucose Fasting",
            "COVID-19 Antibody - IgG",
            "Thyroids Profile-Total (T3, T4 & TSH Ultra-sensitive",
            "Complete Hemogram\n" +
                    "CRP (C Reactive Protein) Quantitative, Serum\n" +
                    " Iron Studies\n" +
                    "Kidney Function Test\n" +
                    "Liver Function Test\n" +
                    "Lipid Profile"
    };

    Map<String,String> item;
    List list;
    SimpleAdapter sa;
    ListView lv;
    Button back, cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        cart = findViewById(R.id.buttonLTCart);
        back = findViewById(R.id.buttonLTBack);
        lv = findViewById(R.id.ListViewLT);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, HomeActivity.class));
            }
        });

        list = new LinkedList();
        for (int i = 0; i < packages.length; i++ ) {
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost: " +packages[i][4] + "/-");
            list.add(item);
        }
        sa = new SimpleAdapter(this, list, R.layout.multi_lines, new String[] {"line1", "line2", "line3", "line4", "line5"}, new int[] {R.id.line1, R.id.line2, R.id.line3, R.id.line4, R.id.line5});
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                intent.putExtra("text1", packages[position][0]);
                intent.putExtra("text2", package_details[position]);
                intent.putExtra("text3", packages[position][4]);
                startActivity(intent);
            }
        });

        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LabTestActivity.this, CartLabActivity.class));
            }
        });
    }
}